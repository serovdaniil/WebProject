package com.epam.jwd.finalProject.command.common.function.user.recoveryPassword;

import com.epam.jwd.finalProject.command.common.function.user.UpdatePasswordCommand;
import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.UserService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecoveryPasswordStageAfterCodeCommand implements Command {
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_RESULT = "result";
    private static final String PASSWORD_REQUEST_PARAM_NAME = "password";
    private static final String PASSWORD_REPEAT_REQUEST_PARAM_NAME = "passwordRepeat";
    private static final String ERROR_PASSWORD_PASS_MESSAGE = "Passwords do not match, repeat the input!";
    private static final String ERROR_PASSWORDS_PASS_ATTRIBUTE = "errorPasswordsPassMessage";
    private static final String RECOVERY_PASSWORD_PAGE = "page.recoveryPassword";
    private static final String INDEX_PAGE = "page.index";


    private static final Logger LOG = LogManager.getLogger(UpdatePasswordCommand.class);

    private final UserService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    RecoveryPasswordStageAfterCodeCommand(UserService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String email = request.getParameter(PARAM_EMAIL);
        final String password = request.getParameter(PASSWORD_REQUEST_PARAM_NAME);
        final String passwordRepeat = request.getParameter(PASSWORD_REPEAT_REQUEST_PARAM_NAME);
        try {
            if (!passwordRepeat.equals(password)) {
                request.addAttributeToJsp(ERROR_PASSWORDS_PASS_ATTRIBUTE, ERROR_PASSWORD_PASS_MESSAGE);
                return requestFactory.createForwardResponse(propertyContext.get(RECOVERY_PASSWORD_PAGE));
            }
            service.updatePasswordByLogin(email, password);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createRedirectResponse(propertyContext.get(INDEX_PAGE));
    }

    public static RecoveryPasswordStageAfterCodeCommand getInstance() {
        return RecoveryPasswordStageAfterCodeCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final RecoveryPasswordStageAfterCodeCommand INSTANCE =
                new RecoveryPasswordStageAfterCodeCommand(ServiceFactory.simple().userService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
