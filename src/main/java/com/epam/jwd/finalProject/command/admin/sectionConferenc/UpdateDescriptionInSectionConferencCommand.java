package com.epam.jwd.finalProject.command.admin.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

public class UpdateDescriptionInSectionConferencCommand implements Command {
    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String PARAM_DESCTIPTION = "description";
    private static final String RESULT_ATTRIBUTE_NAME = "result";
    private static final String UPDATE_DESCRIPTION_IN_SECTION_CONFERENC_PAGE = "page.updateDescriptionInSectionConferenc";

    UpdateDescriptionInSectionConferencCommand(SectionConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final String description = request.getParameter(PARAM_DESCTIPTION);
        final boolean result = service.updateDescription(id,description);
        request.addAttributeToJsp(RESULT_ATTRIBUTE_NAME, result);
        return requestFactory.createForwardResponse(propertyContext.get(UPDATE_DESCRIPTION_IN_SECTION_CONFERENC_PAGE));
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
