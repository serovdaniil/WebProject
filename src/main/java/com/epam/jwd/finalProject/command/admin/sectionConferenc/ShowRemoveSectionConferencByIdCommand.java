package com.epam.jwd.finalProject.command.admin.sectionConferenc;

import com.epam.jwd.finalProject.command.admin.conferenc.ShowRemoveConferencByIdCommand;
import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowRemoveSectionConferencByIdCommand implements Command {
    private static final String SHOW_REMOVE_SECTION_CONFERENCES_BY_ID_PAGE = "page.removeSectionConferencesById";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowRemoveSectionConferencByIdCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_REMOVE_SECTION_CONFERENCES_BY_ID_PAGE));
    }

    public static ShowRemoveSectionConferencByIdCommand getInstance() {
        return ShowRemoveSectionConferencByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowRemoveSectionConferencByIdCommand INSTANCE =
                new ShowRemoveSectionConferencByIdCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
