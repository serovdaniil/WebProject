package com.epam.jwd.finalProject.command.admin.application;

import com.epam.jwd.finalProject.command.admin.sectionConferenc.CreateSectionConferencCommand;
import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.service.api.ApplicationService;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class RemoveApplicationByIdCommand implements Command {
    private static final String PARAM_ID = "id";
    private static final String APPLICATIONS_ATTRIBUTE_NAME_RESULT = "result";
    private static final String APPLICATIONS_ATTRIBUTE_NAME = "applications";
    private static final String APPLICATIONS_PAGE = "page.applications";
    private static final Logger LOG = LogManager.getLogger(RemoveApplicationByIdCommand.class);

    private final ApplicationService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    RemoveApplicationByIdCommand(EntityService<Application> service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().applicationService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id =Long.parseLong(request.getParameter(PARAM_ID));
        final boolean resultRemove;
        try {
            resultRemove = service.remove(id);
            final List<Application> applicationList = service.findAll();
            String result;
            if (!resultRemove) {
                result = "Unsuccessful remove";
            } else {
                result = "Successful remove";
            }
            request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME, applicationList);
            request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME_RESULT, result);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(APPLICATIONS_PAGE));
    }

    public static RemoveApplicationByIdCommand getInstance() {
        return RemoveApplicationByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final RemoveApplicationByIdCommand INSTANCE =
                new RemoveApplicationByIdCommand(ServiceFactory.simple().serviceFor(Application.class), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
