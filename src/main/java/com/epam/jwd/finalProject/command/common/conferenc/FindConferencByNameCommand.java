package com.epam.jwd.finalProject.command.common.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;

public class FindConferencByNameCommand implements Command {
    private final ConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String FIND_PARAM_NAME = "name";
    private static final String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private static final String FIND_CONFERENCES_BY_NAME_PAGE = "page.findConferencesByName";

    FindConferencByNameCommand(ConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().conferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String login = request.getParameter(FIND_PARAM_NAME);
        final List<Conferenc> conferencesAll = service.findByName(login);
        request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
        return requestFactory.createForwardResponse(propertyContext.get(FIND_CONFERENCES_BY_NAME_PAGE));
    }

    public static FindConferencByNameCommand getInstance() {
        return FindConferencByNameCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindConferencByNameCommand INSTANCE =
                new FindConferencByNameCommand(ServiceFactory.simple().conferencService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
