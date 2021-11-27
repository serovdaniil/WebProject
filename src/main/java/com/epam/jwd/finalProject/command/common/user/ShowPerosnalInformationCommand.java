package com.epam.jwd.finalProject.command.common.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowPerosnalInformationCommand implements Command {
    private static final String CREATE_AN_ACCOUNT_PAGE = "page.personalInformation";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowPerosnalInformationCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(CREATE_AN_ACCOUNT_PAGE));
    }

    public static ShowPerosnalInformationCommand getInstance() {
        return ShowPerosnalInformationCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowPerosnalInformationCommand INSTANCE =
                new ShowPerosnalInformationCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
