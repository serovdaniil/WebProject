package com.epam.jwd.finalProject.command.error;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

/**
 * The error page
 *
 * @author Daniil Serov
 */
public class ShowErrorPageCommand implements Command {

    private static final String ERROR_PAGE = "page.error";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowErrorPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(ERROR_PAGE));
    }

    public static ShowErrorPageCommand getInstance() {
        return ShowErrorPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowErrorPageCommand INSTANCE =
                new ShowErrorPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
