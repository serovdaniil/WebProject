package com.epam.jwd.finalProject.command.common.page.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

/**
 * This page displays conferences in category
 *
 * @author Daniil Serov
 */
public class ShowFindConferencInIdCategoryPageCommand implements Command {
    private static final String SHOW_ALL_CONFERENC_IN_CATEGORY_PAGE = "page.allConferencesInCategory";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowFindConferencInIdCategoryPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_ALL_CONFERENC_IN_CATEGORY_PAGE));
    }

    public static ShowFindConferencInIdCategoryPageCommand getInstance() {
        return ShowFindConferencInIdCategoryPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowFindConferencInIdCategoryPageCommand INSTANCE =
                new ShowFindConferencInIdCategoryPageCommand(RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
