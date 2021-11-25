package com.epam.jwd.finalProject.command.common.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowFindSectionConferencInCategoryByIdCommand implements Command {
    private static final String SHOW_ALL_SECTION_CONFERENC_IN_CATEGORY_PAGE = "page.allSectionConferencesInCategory";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowFindSectionConferencInCategoryByIdCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(SHOW_ALL_SECTION_CONFERENC_IN_CATEGORY_PAGE));
    }

    public static ShowFindSectionConferencInCategoryByIdCommand getInstance() {
        return ShowFindSectionConferencInCategoryByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowFindSectionConferencInCategoryByIdCommand INSTANCE =
                new ShowFindSectionConferencInCategoryByIdCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
