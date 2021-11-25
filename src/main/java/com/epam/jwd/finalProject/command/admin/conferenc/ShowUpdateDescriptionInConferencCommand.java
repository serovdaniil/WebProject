package com.epam.jwd.finalProject.command.admin.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowUpdateDescriptionInConferencCommand implements Command {
    private static final String SHOW_UPDATE_DECRIPTION_IN_CONFERENC_PAGE = "page.updateDescriptionInConferenc";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowUpdateDescriptionInConferencCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_UPDATE_DECRIPTION_IN_CONFERENC_PAGE));
    }

    public static ShowUpdateDescriptionInConferencCommand getInstance() {
        return ShowUpdateDescriptionInConferencCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowUpdateDescriptionInConferencCommand INSTANCE =
                new ShowUpdateDescriptionInConferencCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
