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
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CreateApplicationCommand implements Command {
    private static final String PARAM_ID = "id";
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String APPLICATIONS_ATTRIBUTE_NAME_SECTION_CONFERENC = "applications";
    private static final String APPLICATIONS_ATTRIBUTE_NAME = "result";
    private static final String APPLICATIONS_PAGE = "page.applicationsByAccount";
    private static final Logger LOG = LogManager.getLogger(CreateApplicationCommand.class);

    private final ApplicationService applicationService;
    private final SectionConferencService sectionConferencServiceservice;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    CreateApplicationCommand(EntityService<Application> service, SectionConferencService sectionConferencServiceservice, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.sectionConferencServiceservice = ServiceFactory.simple().sectionConferencService();
        this.applicationService = ServiceFactory.simple().applicationService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Optional<User> userOptional = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        final Long idAccount = userOptional.get().getId();
        final Long idSectionConferenc = Long.parseLong(request.getParameter(PARAM_ID));
        final boolean result;
        final List<Application> applicationList;
        try {
            result = applicationService.create(idAccount, idSectionConferenc, (long) 1);
            applicationList = applicationService.findAccountIdByApplication(idAccount);
            request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME_SECTION_CONFERENC, applicationList);
            request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME, result);
        }  catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
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
