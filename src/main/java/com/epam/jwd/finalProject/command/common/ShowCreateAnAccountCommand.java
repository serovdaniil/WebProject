package com.epam.jwd.finalProject.command.common;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowCreateAnAccountCommand implements Command {
    private static final String CREATE_AN_ACCOUNT_PAGE = "page.createAnAccount";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowCreateAnAccountCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(CREATE_AN_ACCOUNT_PAGE));
    }

    public static ShowCreateAnAccountCommand getInstance() {
        return ShowCreateAnAccountCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowCreateAnAccountCommand INSTANCE =
                new ShowCreateAnAccountCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
