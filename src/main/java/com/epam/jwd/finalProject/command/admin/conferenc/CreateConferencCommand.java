package com.epam.jwd.finalProject.command.admin.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class CreateConferencCommand implements Command {
    private final ConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_ID_CATEGORY = "idCategory";
    private static final String CONFERENCES_ATTRIBUTE_NAME_RESULT_CREATE = "result";
    private static final String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private static final String CONFERENCES_PAGE = "page.conferences";
    private static final Logger LOG = LogManager.getLogger(FindConferencByIdCommand.class);

    CreateConferencCommand(ConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().conferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String name = request.getParameter(PARAM_NAME);
        final String description = request.getParameter(PARAM_DESCRIPTION);
        final Long id = Long.parseLong(request.getParameter(PARAM_ID_CATEGORY));
        final boolean resultCreate;
        try {
            resultCreate = service.create(name,description,id);
            final List<Conferenc> conferencesAll = service.findAll();
            String result;
            if (!resultCreate) {
                result = "Unsuccessful create";
            } else {
                result = "Successful create";
            }
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME_RESULT_CREATE, result);
        }catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(CONFERENCES_PAGE));
    }

    public static CreateConferencCommand getInstance() {
        return CreateConferencCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreateConferencCommand INSTANCE =
                new CreateConferencCommand(ServiceFactory.simple().conferencService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
