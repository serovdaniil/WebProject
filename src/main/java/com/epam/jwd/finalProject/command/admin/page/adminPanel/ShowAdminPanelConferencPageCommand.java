package com.epam.jwd.finalProject.command.admin.page.adminPanel;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

/**
 * This command displays the admin panel for managing conferences
 *
 * @author Daniil Serov
 */
public class ShowAdminPanelConferencPageCommand implements Command {
    private static final String SHOW_READ_USER_BY_ID_PAGE = "page.adminPanelConferenc";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowAdminPanelConferencPageCommand() {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_READ_USER_BY_ID_PAGE));
    }

    public static ShowAdminPanelConferencPageCommand getInstance() {
        return ShowAdminPanelConferencPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowAdminPanelConferencPageCommand INSTANCE =
                new ShowAdminPanelConferencPageCommand();
    }
}
