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

public class UpdateDescriptionInConferencCommand implements Command {
    private final ConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String PARAM_DESCTIPTION = "description";
    private static final String RESULT_ATTRIBUTE_NAME = "result";
    private static final String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private static final String CONFERENCES_PAGE = "page.conferences";

    UpdateDescriptionInConferencCommand(ConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().conferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final String description = request.getParameter(PARAM_DESCTIPTION);
        final boolean resultUpdate = service.updateDescription(id, description);
        final List<Conferenc> conferencesAll = service.findAll();
        String result;
        if (!resultUpdate) {
            result = "Unsuccessful update";
        } else {
            result = "Successful update";
        }
        request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
        request.addAttributeToJsp(RESULT_ATTRIBUTE_NAME, result);
        return requestFactory.createForwardResponse(propertyContext.get(CONFERENCES_PAGE));
    }

    public static UpdateDescriptionInConferencCommand getInstance() {
        return UpdateDescriptionInConferencCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdateDescriptionInConferencCommand INSTANCE =
                new UpdateDescriptionInConferencCommand(ServiceFactory.simple().conferencService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
