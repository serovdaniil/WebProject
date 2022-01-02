package com.epam.jwd.finalProject.command.common.page.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
/**
 * The find section conferenc by name page
 *
 * @author Daniil Serov
 */
public class ShowFindSectionConferencByNamePageCommand implements Command {
    private static final String FIND_SECTION_CONFERENCES_BY_NAME_PAGE = "page.findSectionConferencesByName";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowFindSectionConferencByNamePageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(FIND_SECTION_CONFERENCES_BY_NAME_PAGE));
    }

    public static ShowFindSectionConferencByNamePageCommand getInstance() {
        return ShowFindSectionConferencByNamePageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowFindSectionConferencByNamePageCommand INSTANCE =
                new ShowFindSectionConferencByNamePageCommand(RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
