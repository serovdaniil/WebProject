package com.epam.jwd.finalProject.command.admin.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowCreateCategoryCommand implements Command {
    private static final String SHOW_CREATE_CATEGORY_BY_ID_PAGE = "page.createCategory";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowCreateCategoryCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_CREATE_CATEGORY_BY_ID_PAGE));
    }

    public static ShowCreateCategoryCommand getInstance() {
        return ShowCreateCategoryCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowCreateCategoryCommand INSTANCE =
                new ShowCreateCategoryCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
