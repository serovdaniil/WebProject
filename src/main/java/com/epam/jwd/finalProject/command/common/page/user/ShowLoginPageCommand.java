package com.epam.jwd.finalProject.command.common.page.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
/**
 * The login page
 *
 * @author Daniil Serov
 */
public class ShowLoginPageCommand implements Command {
    private static final String LOGIN_PAGE = "page.login";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowLoginPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(LOGIN_PAGE));
    }

    public static ShowLoginPageCommand getInstance() {
        return ShowLoginPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowLoginPageCommand INSTANCE =
                new ShowLoginPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
