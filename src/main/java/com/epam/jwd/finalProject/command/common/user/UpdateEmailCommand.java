package com.epam.jwd.finalProject.command.common.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.UserService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UpdateEmailCommand implements Command {
    private final UserService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String FIND_PARAM_EMAIL = "email";
    private static final String USERS_ATTRIBUTE_NAME = "user";
    private static final String URL_ACCOUNT_PAGE = "/controller?command=show_personal_infomation";
    private static final String SUCCESSFUL_RESULT_UPDATE_INFORMATION = "Successful updating of personal information";
    private static final String UNSUCCESSFUL_RESULT_UPDATE_INFORMATION = "Unsuccessful updating of personal information";
    private static final Logger LOG = LogManager.getLogger(UpdateEmailCommand.class);
    UpdateEmailCommand(UserService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Optional<User> userOptional = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        final Long id = userOptional.get().getId();
        final String email = request.getParameter(FIND_PARAM_EMAIL);
        final Optional<User> user;
        try {
            user = service.updateEmail(id, email);
            if (email.equals(user.get().getEmail())) {
                request.addAttributeToJsp(USERS_ATTRIBUTE_NAME, SUCCESSFUL_RESULT_UPDATE_INFORMATION);
            } else {
                request.addAttributeToJsp(USERS_ATTRIBUTE_NAME, UNSUCCESSFUL_RESULT_UPDATE_INFORMATION);
            }
            request.clearSession();
            request.createSession();
            request.addToSession(USER_SESSION_ATTRIBUTE_NAME, user.get());
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }

        return requestFactory.createRedirectResponse(URL_ACCOUNT_PAGE);
    }

    public static UpdateEmailCommand getInstance() {
        return UpdateEmailCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdateEmailCommand INSTANCE =
                new UpdateEmailCommand(ServiceFactory.simple().userService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
