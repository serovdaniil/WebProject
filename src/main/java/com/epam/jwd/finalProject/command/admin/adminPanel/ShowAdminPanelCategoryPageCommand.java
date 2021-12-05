package com.epam.jwd.finalProject.command.admin.adminPanel;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowAdminPanelCategoryPageCommand implements Command {
    private static final String SHOW_READ_USER_BY_ID_PAGE = "page.adminPanelCategory";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowAdminPanelCategoryPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_READ_USER_BY_ID_PAGE));
    }

    public static ShowAdminPanelCategoryPageCommand getInstance() {
        return ShowAdminPanelCategoryPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowAdminPanelCategoryPageCommand INSTANCE =
                new ShowAdminPanelCategoryPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
