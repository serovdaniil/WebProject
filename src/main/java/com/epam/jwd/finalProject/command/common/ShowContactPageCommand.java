package com.epam.jwd.finalProject.command.common;

import com.epam.jwd.finalProject.command.common.sectionConferenc.ShowFindSectionConferencByNameCommand;
import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowContactPageCommand implements Command {
    private static final String FIND_SECTION_CONFERENCES_BY_NAME_PAGE = "page.contact";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowContactPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(FIND_SECTION_CONFERENCES_BY_NAME_PAGE));
    }

    public static ShowContactPageCommand getInstance() {
        return ShowContactPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowContactPageCommand INSTANCE =
                new ShowContactPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
