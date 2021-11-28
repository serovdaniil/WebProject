package com.epam.jwd.finalProject.command.admin.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;

public class UpdateDescriptionInSectionConferencCommand implements Command {
    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String PARAM_DESCTIPTION = "description";
    private static final String RESULT_ATTRIBUTE_NAME = "result";
    private static final String SECTION_CONFERENCES_ATTRIBUTE_NAME_ALL = "sectionConferences";
    private static final String SECTION_CONFERENCES_PAGE = "page.sectionConferences";
    UpdateDescriptionInSectionConferencCommand(SectionConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final String description = request.getParameter(PARAM_DESCTIPTION);
        final boolean resultUpdate = service.updateDescription(id,description);
        final List<SectionConferenc> sectionConferencesAll = service.findAll();
        String result;
        if (!resultUpdate) {
            result = "Unsuccessful update";
        } else {
            result = "Successful update";
        }
        request.addAttributeToJsp(SECTION_CONFERENCES_ATTRIBUTE_NAME_ALL, sectionConferencesAll);

        request.addAttributeToJsp(RESULT_ATTRIBUTE_NAME, result);
        return requestFactory.createForwardResponse(propertyContext.get(SECTION_CONFERENCES_PAGE));
    }

    public static UpdateDescriptionInSectionConferencCommand getInstance() {
        return UpdateDescriptionInSectionConferencCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdateDescriptionInSectionConferencCommand INSTANCE =
                new UpdateDescriptionInSectionConferencCommand(ServiceFactory.simple().sectionConferencService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
