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

public class UpdateLastNameCommand implements Command {
    private final UserService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String FIND_PARAM_FIRTS_NAME = "lastName";
    private static final String USERS_ATTRIBUTE_NAME = "user";
    private static final String UPDATE_LAST_NAME_USER_PAGE = "page.personalInformation";
    private static final String SUCCESSFUL_RESULT_UPDATE_INFORMATION = "Successful updating of personal information";
    private static final String UNSUCCESSFUL_RESULT_UPDATE_INFORMATION = "Unsuccessful updating of personal information";

    UpdateLastNameCommand(UserService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Optional<User> userOptional = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        final Long id = userOptional.get().getId();
        final String lastName = request.getParameter(FIND_PARAM_FIRTS_NAME);
        final Optional<User> user = service.updateLastName(id, lastName);
        if (lastName.equals(user.get().getLastName())) {
            request.addAttributeToJsp(USERS_ATTRIBUTE_NAME, SUCCESSFUL_RESULT_UPDATE_INFORMATION);
        } else {
            request.addAttributeToJsp(USERS_ATTRIBUTE_NAME, UNSUCCESSFUL_RESULT_UPDATE_INFORMATION);
        }
        request.clearSession();
        request.createSession();
        request.addToSession(USER_SESSION_ATTRIBUTE_NAME, user.get());
        return requestFactory.createForwardResponse(propertyContext.get(UPDATE_LAST_NAME_USER_PAGE));
    }

    public static UpdateLastNameCommand getInstance() {
        return UpdateLastNameCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdateLastNameCommand INSTANCE =
                new UpdateLastNameCommand(ServiceFactory.simple().userService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}