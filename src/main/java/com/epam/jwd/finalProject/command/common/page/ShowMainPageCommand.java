package com.epam.jwd.finalProject.command.common.page;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowMainPageCommand implements Command {
    private static final String MAIN_PAGE = "page.main";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowMainPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
          this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(MAIN_PAGE));
    }
    public static ShowMainPageCommand getInstance() {
        return ShowMainPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowMainPageCommand INSTANCE =
                new ShowMainPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
