package com.epam.jwd.finalProject.command.common.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;

public class FindSectionConferencesInConferencByIdCommand implements Command {private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String FIND_PARAM_ID = "id";
    private static final String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private static final String FIND_SECTION_CONFERENCES_BY_NAME_PAGE = "page.findSectionConferencesInConferencById";

    FindSectionConferencesInConferencByIdCommand(SectionConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(FIND_PARAM_ID));
        final List<SectionConferenc> conferencesAll = service.findSectionConferencesInConferencById(id);
        request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
        return requestFactory.createForwardResponse(propertyContext.get(FIND_SECTION_CONFERENCES_BY_NAME_PAGE));
    }

    public static FindSectionConferencesInConferencByIdCommand getInstance() {
        return FindSectionConferencesInConferencByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindSectionConferencesInConferencByIdCommand INSTANCE =
                new FindSectionConferencesInConferencByIdCommand(ServiceFactory.simple().sectionConferencService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
