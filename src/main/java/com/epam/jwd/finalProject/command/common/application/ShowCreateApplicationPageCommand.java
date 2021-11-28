package com.epam.jwd.finalProject.command.common.application;

import com.epam.jwd.finalProject.command.admin.application.ShowDeleteApplicationByIdPageCommand;
import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowCreateApplicationPageCommand implements Command {
    private static final String APPLICATIONS_PAGE = "page.createApplication";
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowCreateApplicationPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(APPLICATIONS_PAGE));
    }

    public static ShowCreateApplicationPageCommand getInstance() {
        return ShowCreateApplicationPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowCreateApplicationPageCommand INSTANCE =
                new ShowCreateApplicationPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
