package com.epam.jwd.finalProject.command.common.function.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;

/**
 * This command is for exiting your personal account
 *
 * @author Daniil Serov
 */
public class LogoutCommand implements Command {

    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String INDEX_PAGE = "page.index";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    LogoutCommand() {
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        if (noLoggedInUserPresent(request)) {
            return requestFactory.createRedirectResponse(propertyContext.get(INDEX_PAGE));
        }
        request.clearSession();
        return requestFactory.createRedirectResponse(propertyContext.get(INDEX_PAGE));
    }

    private boolean noLoggedInUserPresent(CommandRequest request) {
        return !request.sessionExists() || (
                request.sessionExists()
                        && !request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME)
                        .isPresent()
        );
    }
    public static LogoutCommand getInstance() {
        return LogoutCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final LogoutCommand INSTANCE =
                new LogoutCommand();
    }
}
