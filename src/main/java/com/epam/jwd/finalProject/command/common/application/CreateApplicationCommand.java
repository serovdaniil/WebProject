package com.epam.jwd.finalProject.command.common.application;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.ApplicationService;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;
import java.util.Optional;

public class CreateApplicationCommand implements Command {
    private static final String PARAM_ID = "id";
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String APPLICATIONS_ATTRIBUTE_NAME_SECTION_CONFERENC = "sectionConferences";
    private static final String APPLICATIONS_ATTRIBUTE_NAME = "result";
    private static final String APPLICATIONS_PAGE = "page.sectionConferences";

    private final ApplicationService applicationServiceservice;
    private final SectionConferencService sectionConferencServiceservice;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    CreateApplicationCommand(EntityService<Application> service, SectionConferencService sectionConferencServiceservice, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.sectionConferencServiceservice = ServiceFactory.simple().sectionConferencService();
        this.applicationServiceservice = ServiceFactory.simple().applicationService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Optional<User> userOptional = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        final Long idAccount = userOptional.get().getId();
        final Long idSectionConferenc = Long.parseLong(request.getParameter(PARAM_ID));
        final boolean result = applicationServiceservice.create(idAccount, idSectionConferenc, (long) 1);
        final List<SectionConferenc> sectionConferencesAll = sectionConferencServiceservice.findAll();
        request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME_SECTION_CONFERENC, sectionConferencesAll);
        request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME, result);
        return requestFactory.createForwardResponse(propertyContext.get(APPLICATIONS_PAGE));
    }

    public static CreateApplicationCommand getInstance() {
        return CreateApplicationCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreateApplicationCommand INSTANCE =
                new CreateApplicationCommand(ServiceFactory.simple().serviceFor(Application.class), ServiceFactory.simple().sectionConferencService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}