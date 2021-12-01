package com.epam.jwd.finalProject.command.admin.sectionConferenc;

import com.epam.jwd.finalProject.command.admin.user.ReadUserByIdCommand;
import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class FindSectionConferencByIdCommand implements Command {
    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String SECTION_CONFERENCES_ATTRIBUTE_NAME = "sectionConferenc";
    private static final String SECTION_CONFERENCES_ATTRIBUTE_NAME_ALL = "sectionConferences";
    private static final String SECTION_CONFERENCES_PAGE = "page.sectionConferences";
    private static final Logger LOG = LogManager.getLogger(FindSectionConferencByIdCommand.class);

    FindSectionConferencByIdCommand(SectionConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final Optional<SectionConferenc> sectionConferenc;
        final List<SectionConferenc> sectionConferencesAll;
        try {
            sectionConferencesAll = service.findAll();
            sectionConferenc = service.findId(id);
            request.addAttributeToJsp(SECTION_CONFERENCES_ATTRIBUTE_NAME_ALL, sectionConferencesAll);
            request.addAttributeToJsp(SECTION_CONFERENCES_ATTRIBUTE_NAME, sectionConferenc);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(SECTION_CONFERENCES_PAGE));
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
