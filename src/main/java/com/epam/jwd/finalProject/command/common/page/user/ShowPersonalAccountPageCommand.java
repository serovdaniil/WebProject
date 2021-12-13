package com.epam.jwd.finalProject.command.common.page.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
/**
 * The personal account page
 *
 * @author Daniil Serov
 */
public class ShowPersonalAccountPageCommand implements Command {
    private static final String PERSONAL_ACCOUNT = "page.personalAccount";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowPersonalAccountPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(PERSONAL_ACCOUNT));
    }

    public static ShowPersonalAccountPageCommand getInstance() {
        return ShowPersonalAccountPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowPersonalAccountPageCommand INSTANCE =
                new ShowPersonalAccountPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
