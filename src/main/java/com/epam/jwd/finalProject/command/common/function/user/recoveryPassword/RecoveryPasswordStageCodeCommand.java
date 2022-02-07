package com.epam.jwd.finalProject.command.common.function.user.recoveryPassword;

import com.epam.jwd.finalProject.command.common.function.user.UpdatePasswordCommand;
import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.UserService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecoveryPasswordStageCodeCommand implements Command {
    private static final String CODE_SESSION_ATTRIBUTE_NAME = "code";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_RESULT = "result";
    private static final String ERROR_FIND_USER_PASS_MESSAGE = "The entered code does not match!";
    private static final String ERROR_FIND_USER_PASS_ATTRIBUTE = "errorCodePassMessage";
    private static final String RECOVERY_PASSWORD_PAGE = "page.recoveryPassword";


    private static final Logger LOG = LogManager.getLogger(UpdatePasswordCommand.class);

    private final UserService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    RecoveryPasswordStageCodeCommand(UserService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String email = request.getParameter(PARAM_EMAIL);
        final Long code = Long.parseLong(request.getParameter(CODE_SESSION_ATTRIBUTE_NAME));
        final Long codeSession=request.retrieveFromSessionLong(CODE_SESSION_ATTRIBUTE_NAME);
            if (code.equals(codeSession)) {
                request.addAttributeToJsp(PARAM_EMAIL,email);
                request.addAttributeToJsp(PARAM_RESULT,true);
                request.clearSession();
                return requestFactory.createForwardResponse(propertyContext.get(RECOVERY_PASSWORD_PAGE));
            }else {
                request.addAttributeToJsp(ERROR_FIND_USER_PASS_ATTRIBUTE, ERROR_FIND_USER_PASS_MESSAGE);
                return requestFactory.createForwardResponse(propertyContext.get(RECOVERY_PASSWORD_PAGE));
            }
    }

    public static RecoveryPasswordStageCodeCommand getInstance() {
        return RecoveryPasswordStageCodeCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final RecoveryPasswordStageCodeCommand INSTANCE =
                new RecoveryPasswordStageCodeCommand(ServiceFactory.simple().userService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
