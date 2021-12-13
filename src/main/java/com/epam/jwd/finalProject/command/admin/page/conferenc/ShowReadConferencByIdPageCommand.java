package com.epam.jwd.finalProject.command.admin.page.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

/**
 * This command displays a detailed description of the selected conference
 *
 * @author Daniil Serov
 */
public class ShowReadConferencByIdPageCommand implements Command {
    private static final String SHOW_READ_USER_BY_ID_PAGE = "page.readConferencById";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowReadConferencByIdPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_READ_USER_BY_ID_PAGE));
    }

    public static ShowReadConferencByIdPageCommand getInstance() {
        return ShowReadConferencByIdPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowReadConferencByIdPageCommand INSTANCE =
                new ShowReadConferencByIdPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
