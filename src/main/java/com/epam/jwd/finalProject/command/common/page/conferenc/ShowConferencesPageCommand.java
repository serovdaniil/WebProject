package com.epam.jwd.finalProject.command.common.page.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;

public class ShowConferencesPageCommand implements Command {

    private static final String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private static final String CONFERENCES_PAGE = "page.conferences";

    private final EntityService<Conferenc> service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowConferencesPageCommand(EntityService<Conferenc> service,RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().serviceFor(Conferenc.class);
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<Conferenc> conferencesAll = service.findAll();
        request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
        return requestFactory.createForwardResponse(propertyContext.get(CONFERENCES_PAGE));
    }
    public static ShowConferencesPageCommand getInstance() {
        return ShowConferencesPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowConferencesPageCommand INSTANCE =
                new ShowConferencesPageCommand(ServiceFactory.simple().serviceFor(Conferenc.class),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
