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
 * This command is used to log in to your personal account
 *
 * @author Daniil Serov
 */
public class LoginCommand implements Command {
    private static final String INDEX_PAGE = "page.index";
    private static final String LOGIN_PAGE = "page.login";
    private static final String ERROR_LOGIN_PASS_ATTRIBUTE = "errorLoginPassMessage";
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String LOGIN_REQUEST_PARAM_NAME = "login";
    private static final String PASSWORD_REQUEST_PARAM_NAME = "password";
    private static final String ERROR_LOGIN_PASS_MESSAGE = "Invalid login or password";

    private static final Logger LOG = LogManager.getLogger(LoginCommand.class);

    private final UserService userService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    LoginCommand(UserService userService, RequestFactory requestFactory,
                 PropertyContext propertyContext) {
        this.userService = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request)  {
        if (request.sessionExists() && request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME).isPresent()) {
            throw new UnsupportedOperationException("user already exists");
        }
        final String login = request.getParameter(LOGIN_REQUEST_PARAM_NAME);
        final String password = request.getParameter(PASSWORD_REQUEST_PARAM_NAME);
        final Optional<User> user;
        try {
            user = userService.authenticate(login, password);
            if (!user.isPresent()) {
                request.addAttributeToJsp(ERROR_LOGIN_PASS_ATTRIBUTE, ERROR_LOGIN_PASS_MESSAGE);
                return requestFactory.createForwardResponse(propertyContext.get(LOGIN_PAGE));
            }
            request.clearSession();
            request.createSession();
            request.addToSession(USER_SESSION_ATTRIBUTE_NAME, user.get());
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createRedirectResponse(propertyContext.get(INDEX_PAGE));
    }

    public static LoginCommand getInstance() {
        return LoginCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final LoginCommand INSTANCE =
                new LoginCommand(ServiceFactory.simple().userService(),
                        RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}