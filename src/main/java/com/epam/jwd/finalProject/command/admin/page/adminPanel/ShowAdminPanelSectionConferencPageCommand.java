package com.epam.jwd.finalProject.command.admin.page.adminPanel;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

/**
 * This command displays the admin panel for managing conference sections
 *
 * @author Daniil Serov
 */
public class ShowAdminPanelSectionConferencPageCommand implements Command {
    private static final String SHOW_READ_USER_BY_ID_PAGE = "page.adminPanelSectionConferenc";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowAdminPanelSectionConferencPageCommand() {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_READ_USER_BY_ID_PAGE));
    }

    public static ShowAdminPanelSectionConferencPageCommand getInstance() {
        return ShowAdminPanelSectionConferencPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowAdminPanelSectionConferencPageCommand INSTANCE =
                new ShowAdminPanelSectionConferencPageCommand();
    }
}
