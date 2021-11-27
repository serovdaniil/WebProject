package com.epam.jwd.finalProject.command.admin.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.UserService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.Optional;

public class ReadUserByIdCommand implements Command {
    private final UserService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String FIND_PARAM_ID_ACCOUNT = "id";
    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String READ_USER_BY_ID_PAGE = "page.readUserById";

    ReadUserByIdCommand(UserService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Optional<User> userOptional = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        final Long idAccount = Long.parseLong(request.getParameter(FIND_PARAM_ID_ACCOUNT));
        final Optional<User> user = service.findId(idAccount);
        request.addAttributeToJsp(USER_ATTRIBUTE_NAME, user);
        return requestFactory.createForwardResponse(propertyContext.get(READ_USER_BY_ID_PAGE));
    }

    public static ReadUserByIdCommand getInstance() {
        return ReadUserByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ReadUserByIdCommand INSTANCE =
                new ReadUserByIdCommand(ServiceFactory.simple().userService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
