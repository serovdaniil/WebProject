package com.epam.jwd.finalProject.command.admin.application;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowDeleteApplicationByIdPageCommand implements Command {
    private static final String APPLICATIONS_PAGE = "page.deleteApplication";
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowDeleteApplicationByIdPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(APPLICATIONS_PAGE));
    }

    public static ShowDeleteApplicationByIdPageCommand getInstance() {
        return ShowDeleteApplicationByIdPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowDeleteApplicationByIdPageCommand INSTANCE =
                new ShowDeleteApplicationByIdPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
