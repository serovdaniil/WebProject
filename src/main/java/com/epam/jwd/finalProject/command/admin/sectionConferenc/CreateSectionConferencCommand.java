package com.epam.jwd.finalProject.command.admin.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

public class CreateSectionConferencCommand implements Command {
    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_ID_CATEGORY = "idConferenc";
    private static final String SECTIOM_CONFERENCES_ATTRIBUTE_NAME = "result";
    private static final String CREATE_SECTION_CONFERENCES_BY_ID_PAGE = "page.createSectionConferenc";

    CreateSectionConferencCommand(SectionConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String name = request.getParameter(PARAM_NAME);
        final String description = request.getParameter(PARAM_DESCRIPTION);
        final Long id = Long.parseLong(request.getParameter(PARAM_ID_CATEGORY));
        final boolean result = service.create(name,description,id);
        request.addAttributeToJsp(SECTIOM_CONFERENCES_ATTRIBUTE_NAME, result);
        return requestFactory.createForwardResponse(propertyContext.get(CREATE_SECTION_CONFERENCES_BY_ID_PAGE));
    }

    public static CreateSectionConferencCommand getInstance() {
        return CreateSectionConferencCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreateSectionConferencCommand INSTANCE =
                new CreateSectionConferencCommand(ServiceFactory.simple().sectionConferencService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
