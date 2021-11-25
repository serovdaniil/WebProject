package com.epam.jwd.finalProject.command.admin.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

public class RemoveSectionConferencByIdCommand implements Command {
    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String SECTION_CONFERENCES_ATTRIBUTE_NAME = "result";
    private static final String REMOVE_SECTION_CONFERENCES_BY_ID_PAGE = "page.removeSectionConferencesById";

    RemoveSectionConferencByIdCommand(SectionConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final boolean result = service.remove(id);
        request.addAttributeToJsp(SECTION_CONFERENCES_ATTRIBUTE_NAME, result);
        return requestFactory.createForwardResponse(propertyContext.get(REMOVE_SECTION_CONFERENCES_BY_ID_PAGE));
    }

    public static RemoveSectionConferencByIdCommand getInstance() {
        return RemoveSectionConferencByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final RemoveSectionConferencByIdCommand INSTANCE =
                new RemoveSectionConferencByIdCommand(ServiceFactory.simple().sectionConferencService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
