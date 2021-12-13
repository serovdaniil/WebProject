package com.epam.jwd.finalProject.command.admin.page.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowReadUserByIdPageCommand implements Command {
    private static final String SHOW_READ_USER_BY_ID_PAGE = "page.readUserById";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowReadUserByIdPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_READ_USER_BY_ID_PAGE));
    }

    public static ShowReadUserByIdPageCommand getInstance() {
        return ShowReadUserByIdPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowReadUserByIdPageCommand INSTANCE =
                new ShowReadUserByIdPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}