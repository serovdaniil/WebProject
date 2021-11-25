package com.epam.jwd.finalProject.command.admin.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowCreateSectionConferencCommand implements Command {
    private static final String SHOW_CREATE_SECTION_CONFERENCES_BY_ID_PAGE = "page.createSectionConferenc";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowCreateSectionConferencCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_CREATE_SECTION_CONFERENCES_BY_ID_PAGE));
    }

    public static ShowCreateSectionConferencCommand getInstance() {
        return ShowCreateSectionConferencCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowCreateSectionConferencCommand INSTANCE =
                new ShowCreateSectionConferencCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
