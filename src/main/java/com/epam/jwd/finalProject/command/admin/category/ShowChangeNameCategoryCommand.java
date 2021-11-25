package com.epam.jwd.finalProject.command.admin.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowChangeNameCategoryCommand implements Command {
    private static final String SHOW_UPDATE_NAME_CATEGORY_BY_ID_PAGE = "page.changeNameCategory";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowChangeNameCategoryCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_UPDATE_NAME_CATEGORY_BY_ID_PAGE));
    }

    public static ShowChangeNameCategoryCommand getInstance() {
        return ShowChangeNameCategoryCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowChangeNameCategoryCommand INSTANCE =
                new ShowChangeNameCategoryCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
