package com.epam.jwd.finalProject.command.admin.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;

public class CreateSectionConferencCommand implements Command {
    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_ID_CONFERENC = "idConferenc";
    private static final String SECTIOM_CONFERENCES_ATTRIBUTE_NAME = "result";
    private static final String SECTION_CONFERENCES_ATTRIBUTE_NAME_ALL = "sectionConferences";
    private static final String SECTION_CONFERENCES_PAGE = "page.sectionConferences";

    CreateSectionConferencCommand(SectionConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String name = request.getParameter(PARAM_NAME);
        final String description = request.getParameter(PARAM_DESCRIPTION);
        final Long id = Long.parseLong(request.getParameter(PARAM_ID_CONFERENC));
        final boolean resultCreate = service.create(name, description, id);
        final List<SectionConferenc> sectionConferencesAll = service.findAll();
        String result;
        if (!resultCreate) {
            result = "Unsuccessful create";
        } else {
            result = "Successful create";
        }
        request.addAttributeToJsp(SECTION_CONFERENCES_ATTRIBUTE_NAME_ALL, sectionConferencesAll);
        request.addAttributeToJsp(SECTIOM_CONFERENCES_ATTRIBUTE_NAME, result);
        return requestFactory.createForwardResponse(propertyContext.get(SECTION_CONFERENCES_PAGE));
    }

    public static CreateSectionConferencCommand getInstance() {
        return CreateSectionConferencCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreateSectionConferencCommand INSTANCE =
                new CreateSectionConferencCommand(ServiceFactory.simple().sectionConferencService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
