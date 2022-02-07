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

public class RecoveryPasswordStageBeforeCodeCommand implements Command {
    private static final String CODE_SESSION_ATTRIBUTE_NAME = "code";
    private static final String PARAM_EMAIL = "email";
    private static final String ERROR_FIND_USER_PASS_MESSAGE = "A user with this name was not found!";
    private static final String ERROR_FIND_USER_PASS_ATTRIBUTE = "errorFoundPassMessage";
    private static final String RECOVERY_PASSWORD_PAGE = "page.recoveryPassword";


    private static final Logger LOG = LogManager.getLogger(UpdatePasswordCommand.class);

    private final UserService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    RecoveryPasswordStageBeforeCodeCommand(UserService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String email = request.getParameter(PARAM_EMAIL);
        final Long code = generatorCode();
        try {
            boolean resultFound = service.findByEmail(email);
            if (resultFound == false) {
                request.addAttributeToJsp(ERROR_FIND_USER_PASS_ATTRIBUTE, ERROR_FIND_USER_PASS_MESSAGE);
                return requestFactory.createForwardResponse(propertyContext.get(RECOVERY_PASSWORD_PAGE));
            }
            service.recoveryPassword(email, code);
            request.addAttributeToJsp(PARAM_EMAIL,email);
            request.addToSession(CODE_SESSION_ATTRIBUTE_NAME, code);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(RECOVERY_PASSWORD_PAGE));
    }

    private Long generatorCode() {
        Long code = (long) (Math.random() * ((899999) + 1)) + 100000;
        return code;
    }

    public static RecoveryPasswordStageBeforeCodeCommand getInstance() {
        return RecoveryPasswordStageBeforeCodeCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final RecoveryPasswordStageBeforeCodeCommand INSTANCE =
                new RecoveryPasswordStageBeforeCodeCommand(ServiceFactory.simple().userService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
