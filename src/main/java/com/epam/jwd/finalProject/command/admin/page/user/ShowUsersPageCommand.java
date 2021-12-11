package com.epam.jwd.finalProject.command.admin.page.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;

public class ShowUsersPageCommand implements Command {
    private static final String USERS_ATTRIBUTE_NAME = "users";
    private static final String USERS_PAGE = "page.users";

    private final EntityService<User> service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowUsersPageCommand(EntityService<User> service,RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().serviceFor(User.class);
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<User> usersAll = service.findAll();
        request.addAttributeToJsp(USERS_ATTRIBUTE_NAME, usersAll);
        return requestFactory.createForwardResponse(propertyContext.get(USERS_PAGE));
    }
    public static ShowUsersPageCommand getInstance() {
        return ShowUsersPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowUsersPageCommand INSTANCE =
                new ShowUsersPageCommand(ServiceFactory.simple().serviceFor(User.class),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
