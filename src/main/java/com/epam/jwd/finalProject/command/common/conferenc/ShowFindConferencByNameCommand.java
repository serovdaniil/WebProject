package com.epam.jwd.finalProject.command.common.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowFindConferencByNameCommand implements Command {
    private static final String FIND_CONFERENCES_BY_NAME_PAGE = "page.findConferencesByName";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowFindConferencByNameCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(FIND_CONFERENCES_BY_NAME_PAGE));
    }

    public static ShowFindConferencByNameCommand getInstance() {
        return ShowFindConferencByNameCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowFindConferencByNameCommand INSTANCE =
                new ShowFindConferencByNameCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
