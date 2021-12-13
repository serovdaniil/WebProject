package com.epam.jwd.finalProject.command.common.page.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

/**
 * This page displays section conferences in category
 *
 * @author Daniil Serov
 */
public class ShowFindSectionConferencInCategoryByIdPageCommand implements Command {
    private static final String SHOW_ALL_SECTION_CONFERENC_IN_CATEGORY_PAGE = "page.allSectionConferencesInCategory";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowFindSectionConferencInCategoryByIdPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_ALL_SECTION_CONFERENC_IN_CATEGORY_PAGE));
    }

    public static ShowFindSectionConferencInCategoryByIdPageCommand getInstance() {
        return ShowFindSectionConferencInCategoryByIdPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowFindSectionConferencInCategoryByIdPageCommand INSTANCE =
                new ShowFindSectionConferencInCategoryByIdPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
