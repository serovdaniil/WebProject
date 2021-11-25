package com.epam.jwd.finalProject.command.admin.sectionConferenc;

import com.epam.jwd.finalProject.command.admin.conferenc.ShowFindConferencByIdCommand;
import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowFindSectionConferencByIdCommand implements Command {
    private static final String SHOW_UPDATE_DECRIPTION_IN_CONFERENC_PAGE = "page.findSectionConferencesById";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowFindSectionConferencByIdCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_UPDATE_DECRIPTION_IN_CONFERENC_PAGE));
    }

    public static ShowFindSectionConferencByIdCommand getInstance() {
        return ShowFindSectionConferencByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowFindSectionConferencByIdCommand INSTANCE =
                new ShowFindSectionConferencByIdCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
