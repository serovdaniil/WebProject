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
 * This command is for updating the password
 *
 * @author Daniil Serov
 */
public class UpdatePasswordCommand implements Command {
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String FIND_PARAM_PASSWORD = "password";
    private static final String RESULT_ATTRIBUTE_NAME = "result";
    private static final String URL_ACCOUNT_PAGE = "/controller?command=show_personal_infomation";
    private static final String ERROR_UPDATE_PASS_ATTRIBUTE = "errorUpdatePassMessage";
    private static final String ACCOUNT_PAGE = "page.personalInformation";
    private static final String PASSWORD_REPEAT_REQUEST_PARAM_NAME = "passwordRepeat";
    private static final String UNSUCCESSFUL_RESULT_UPDATE_INFORMATION = "Unsuccessful " +
            "updating of personal information";
    private static final String ERROR_PASSWORD_PASS_MESSAGE = "Passwords do not match, repeat the input!";

    private static final Logger LOG = LogManager.getLogger(UpdatePasswordCommand.class);

    private final UserService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    UpdatePasswordCommand() {
        this.service = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Optional<User> userOptional = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        String login = "";
        String newPassword = "";
        if (userOptional.isPresent()) {
            login = userOptional.get().getLogin();
        }
        final String password = request.getParameter(FIND_PARAM_PASSWORD);
        final String passwordRepeat = request.getParameter(PASSWORD_REPEAT_REQUEST_PARAM_NAME);
        Optional<User> user = Optional.empty();
        if (!passwordRepeat.equals(password)) {
            request.addAttributeToJsp(ERROR_UPDATE_PASS_ATTRIBUTE, ERROR_PASSWORD_PASS_MESSAGE);
            return requestFactory.createForwardResponse(propertyContext.get(ACCOUNT_PAGE));
        }
        try {
            user = service.updatePasswordByLogin(login, password);
            if (user.isPresent()) {
                newPassword = user.get().getPassword();
            }
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        if (!password.equals(newPassword) && (user.isPresent())) {
            request.clearSession();
            request.createSession();
            request.addToSession(USER_SESSION_ATTRIBUTE_NAME, user.get());
            return requestFactory.createRedirectResponse(URL_ACCOUNT_PAGE);
        } else {
            request.addAttributeToJsp(RESULT_ATTRIBUTE_NAME, UNSUCCESSFUL_RESULT_UPDATE_INFORMATION);
            return requestFactory.createForwardResponse(propertyContext.get(ACCOUNT_PAGE));
        }
    }

    public static UpdatePasswordCommand getInstance() {
        return UpdatePasswordCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdatePasswordCommand INSTANCE =
                new UpdatePasswordCommand();
    }
}