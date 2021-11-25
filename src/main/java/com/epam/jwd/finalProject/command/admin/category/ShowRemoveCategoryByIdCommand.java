package com.epam.jwd.finalProject.command.admin.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowRemoveCategoryByIdCommand implements Command {
    private static final String SHOW_REMOVE_CATEGORY_BY_ID_CATEGORY_PAGE = "page.removeCategoryById";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowRemoveCategoryByIdCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_REMOVE_CATEGORY_BY_ID_CATEGORY_PAGE));
    }

    public static ShowRemoveCategoryByIdCommand getInstance() {
        return ShowRemoveCategoryByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowRemoveCategoryByIdCommand INSTANCE =
                new ShowRemoveCategoryByIdCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
