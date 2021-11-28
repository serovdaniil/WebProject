package com.epam.jwd.finalProject.command.admin.application;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.service.api.ApplicationService;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

public class DeleteApplicationByIdCommand implements Command {
    private static final String PARAM_ID = "id";
    private static final String APPLICATIONS_ATTRIBUTE_NAME = "result";
    private static final String APPLICATIONS_PAGE = "page.deleteApplication";

    private final ApplicationService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    DeleteApplicationByIdCommand(EntityService<Application> service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().applicationService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id =Long.parseLong(request.getParameter(PARAM_ID));
        final boolean result = service.remove(id);
        request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME, result);
        return requestFactory.createForwardResponse(propertyContext.get(APPLICATIONS_PAGE));
    }

    public static DeleteApplicationByIdCommand getInstance() {
        return DeleteApplicationByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final DeleteApplicationByIdCommand INSTANCE =
                new DeleteApplicationByIdCommand(ServiceFactory.simple().serviceFor(Application.class), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
