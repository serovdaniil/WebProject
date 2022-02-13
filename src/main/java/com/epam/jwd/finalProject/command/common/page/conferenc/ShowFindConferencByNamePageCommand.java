package com.epam.jwd.finalProject.command.common.page.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
/**
 * The find conferenc by name page
 *
 * @author Daniil Serov
 */
public class ShowFindConferencByNamePageCommand implements Command {
    private static final String FIND_CONFERENCES_BY_NAME_PAGE = "page.findConferencesByName";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowFindConferencByNamePageCommand() {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(FIND_CONFERENCES_BY_NAME_PAGE));
    }

    public static ShowFindConferencByNamePageCommand getInstance() {
        return ShowFindConferencByNamePageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowFindConferencByNamePageCommand INSTANCE =
                new ShowFindConferencByNamePageCommand();
    }
}
