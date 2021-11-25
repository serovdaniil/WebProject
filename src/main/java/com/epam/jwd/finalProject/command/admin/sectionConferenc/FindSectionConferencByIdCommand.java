package com.epam.jwd.finalProject.command.admin.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.Optional;

public class FindSectionConferencByIdCommand implements Command {
    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String CONFERENCES_ATTRIBUTE_NAME = "sectionConferences";
    private static final String FIND_CONFERENCES_BY_NAME_PAGE = "page.findSectionConferencesById";

    FindSectionConferencByIdCommand(SectionConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final Optional<SectionConferenc> sectionConferenc = service.findId(id);
        request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, sectionConferenc);
        return requestFactory.createForwardResponse(propertyContext.get(FIND_CONFERENCES_BY_NAME_PAGE));
    }

    public static FindSectionConferencByIdCommand getInstance() {
        return FindSectionConferencByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindSectionConferencByIdCommand INSTANCE =
                new FindSectionConferencByIdCommand(ServiceFactory.simple().sectionConferencService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
