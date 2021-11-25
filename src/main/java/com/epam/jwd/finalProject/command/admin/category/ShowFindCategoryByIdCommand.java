package com.epam.jwd.finalProject.command.admin.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowFindCategoryByIdCommand implements Command {
    private static final String SHOW_CATEGORY_BY_ID_CATEGORY_PAGE = "page.idCategory";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowFindCategoryByIdCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_CATEGORY_BY_ID_CATEGORY_PAGE));
    }

    public static ShowFindCategoryByIdCommand getInstance() {
        return ShowFindCategoryByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowFindCategoryByIdCommand INSTANCE =
                new ShowFindCategoryByIdCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
