package com.epam.jwd.finalProject.command.common.function.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.UserService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * This command is for creating a personal account
 *
 * @author Daniil Serov
 */
public class CreateAnAccountCommand implements Command {
    private static final String INDEX_PAGE = "page.index";
    private static final String REGISTRATION_PAGE = "page.createAnAccount";
    private static final String ERROR_LOGIN_PASS_ATTRIBUTE = "errorRegistrationPassMessage";
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String LOGIN_REQUEST_PARAM_NAME = "email";
    private static final String PASSWORD_REQUEST_PARAM_NAME = "password";
    private static final String PASSWORD_REPEAT_REQUEST_PARAM_NAME = "passwordRepeat";
    private static final String ERROR_REGISTRATION_PASS_MESSAGE = "The user with this email already exists!";
    private static final String ERROR_PASSWORD_PASS_MESSAGE = "Passwords do not match, repeat the input!";
    private static final Logger LOG = LogManager.getLogger(CreateAnAccountCommand.class);
    // TODO: create locale message
    private final UserService userService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    CreateAnAccountCommand(UserService userService, RequestFactory requestFactory,
                           PropertyContext propertyContext) {
        this.userService = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        if (request.sessionExists() && request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME).isPresent()) {
            throw new UnsupportedOperationException("user already exists");
        }
        final String email = request.getParameter(LOGIN_REQUEST_PARAM_NAME);
        final String password = request.getParameter(PASSWORD_REQUEST_PARAM_NAME);
        final String passwordRepeat = request.getParameter(PASSWORD_REPEAT_REQUEST_PARAM_NAME);
        final Optional<User> user;
        try {
            if (!passwordRepeat.equals(password)) {
                request.addAttributeToJsp(ERROR_LOGIN_PASS_ATTRIBUTE, ERROR_PASSWORD_PASS_MESSAGE);
                return requestFactory.createForwardResponse(propertyContext.get(REGISTRATION_PAGE));
            }
            user = userService.registration(email, password);
            if (!user.isPresent()) {
                request.addAttributeToJsp(ERROR_LOGIN_PASS_ATTRIBUTE, ERROR_REGISTRATION_PASS_MESSAGE);
                return requestFactory.createForwardResponse(propertyContext.get(REGISTRATION_PAGE));
            }
            request.clearSession();
            request.createSession();
            request.addToSession(USER_SESSION_ATTRIBUTE_NAME, user.get());
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createRedirectResponse(propertyContext.get(INDEX_PAGE));
    }

    public static CreateAnAccountCommand getInstance() {
        return CreateAnAccountCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreateAnAccountCommand INSTANCE =
                new CreateAnAccountCommand(ServiceFactory.simple().userService(),
                        RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
