package com.epam.jwd.finalProject.command.common.page;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
/**
 * The about page
 *
 * @author Daniil Serov
 */
public class ShowAboutPageCommand implements Command {
    private static final String FIND_SECTION_CONFERENCES_BY_NAME_PAGE = "page.about";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowAboutPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(FIND_SECTION_CONFERENCES_BY_NAME_PAGE));
    }

    public static ShowAboutPageCommand getInstance() {
        return ShowAboutPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowAboutPageCommand INSTANCE =
                new ShowAboutPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
