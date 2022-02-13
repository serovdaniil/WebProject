package com.epam.jwd.finalProject.command.common.page.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

public class ShowRecoveryPasswordPageCommand implements Command {
    private static final String PERSONAL_ACCOUNT = "page.recoveryPassword";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowRecoveryPasswordPageCommand() {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(PERSONAL_ACCOUNT));
    }

    public static ShowRecoveryPasswordPageCommand getInstance() {
        return ShowRecoveryPasswordPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowRecoveryPasswordPageCommand INSTANCE =
                new ShowRecoveryPasswordPageCommand();
    }
}
