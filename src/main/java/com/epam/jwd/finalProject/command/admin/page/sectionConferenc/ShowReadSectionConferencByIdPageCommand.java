package com.epam.jwd.finalProject.command.admin.page.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowReadSectionConferencByIdPageCommand implements Command {
    private static final String SHOW_READ_USER_BY_ID_PAGE = "page.readSectionConferencById";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowReadSectionConferencByIdPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_READ_USER_BY_ID_PAGE));
    }

    public static ShowReadSectionConferencByIdPageCommand getInstance() {
        return ShowReadSectionConferencByIdPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowReadSectionConferencByIdPageCommand INSTANCE =
                new ShowReadSectionConferencByIdPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
