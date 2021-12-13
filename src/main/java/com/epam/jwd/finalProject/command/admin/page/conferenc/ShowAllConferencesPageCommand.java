package com.epam.jwd.finalProject.command.admin.page.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;

/**
 * This command displays all conferences
 *
 * @author Daniil Serov
 */
public class ShowAllConferencesPageCommand implements Command {
    private static final String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private static final String CONFERENCES_PAGE = "page.allConferences";

    private final ConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowAllConferencesPageCommand(ConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().conferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<Conferenc> conferencesAll = service.findAllStatus();
        request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
        return requestFactory.createForwardResponse(propertyContext.get(CONFERENCES_PAGE));
    }
    public static ShowAllConferencesPageCommand getInstance() {
        return ShowAllConferencesPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowAllConferencesPageCommand INSTANCE =
                new ShowAllConferencesPageCommand(ServiceFactory.simple().conferencService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
