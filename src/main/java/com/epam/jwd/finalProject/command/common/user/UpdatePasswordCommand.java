package com.epam.jwd.finalProject.command.common.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.UserService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.Optional;

public class UpdatePasswordCommand implements Command {
    private final UserService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String FIND_PARAM_PASSWORD = "password";
    private static final String USERS_ATTRIBUTE_NAME = "user";
    private static final String UPDATE_PASSWORD_USER_PAGE = "page.personalInformation";
    private static final String SUCCESSFUL_RESULT_UPDATE_INFORMATION = "Successful updating of personal information";
    private static final String UNSUCCESSFUL_UPDATE_PASSWORD_USER_PAGE = "Unsuccessful updating of personal information";

    UpdatePasswordCommand(UserService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Optional<User> userOptional = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        final String login = userOptional.get().getLogin();
        final String password = request.getParameter(FIND_PARAM_PASSWORD);
        final Optional<User> user = service.updatePasswordByLogin(login, password);
        if (password.equals(user.get().getPassword())) {
            request.addAttributeToJsp(USERS_ATTRIBUTE_NAME, SUCCESSFUL_RESULT_UPDATE_INFORMATION);
        } else {
            request.addAttributeToJsp(USERS_ATTRIBUTE_NAME, UNSUCCESSFUL_UPDATE_PASSWORD_USER_PAGE);
        }
        request.clearSession();
        request.createSession();
        request.addToSession(USER_SESSION_ATTRIBUTE_NAME, user.get());
        return requestFactory.createForwardResponse(propertyContext.get(UPDATE_PASSWORD_USER_PAGE));
    }

    public static UpdatePasswordCommand getInstance() {
        return UpdatePasswordCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdatePasswordCommand INSTANCE =
                new UpdatePasswordCommand(ServiceFactory.simple().userService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
