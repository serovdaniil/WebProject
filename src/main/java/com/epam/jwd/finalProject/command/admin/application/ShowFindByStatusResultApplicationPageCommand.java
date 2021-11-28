package com.epam.jwd.finalProject.command.admin.application;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowFindByStatusResultApplicationPageCommand implements Command {
    private static final String APPLICATIONS_PAGE = "page.applicationsByStatusResult";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowFindByStatusResultApplicationPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(APPLICATIONS_PAGE));
    }

    public static ShowFindByStatusResultApplicationPageCommand getInstance() {
        return ShowFindByStatusResultApplicationPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowFindByStatusResultApplicationPageCommand INSTANCE =
                new ShowFindByStatusResultApplicationPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
