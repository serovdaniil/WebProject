package com.epam.jwd.finalProject.command.admin.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;
import java.util.Optional;

public class FindConferencByIdCommand implements Command {
    private final ConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private static final String CONFERENCES_ATTRIBUTE_NAME_FIND = "conferenc";
    private static final String CONFERENCES_PAGE = "page.conferences";

    FindConferencByIdCommand(ConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().conferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final Optional<Conferenc> conferenc = service.findId(id);
        final List<Conferenc> conferencesAll = service.findAll();
        request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
        request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME_FIND, conferenc);
        return requestFactory.createForwardResponse(propertyContext.get(CONFERENCES_PAGE));
    }

    public static FindConferencByIdCommand getInstance() {
        return FindConferencByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindConferencByIdCommand INSTANCE =
                new FindConferencByIdCommand(ServiceFactory.simple().conferencService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
