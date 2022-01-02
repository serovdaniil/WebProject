package com.epam.jwd.finalProject.command.common.function.sectionConferenc;

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

/**
 * This command is used to search for the conference section by the conference id
 *
 * @author Daniil Serov
 */
public class FindSectionConferencesInConferencByIdCommand implements Command {
    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String FIND_PARAM_ID = "id";
    private static final String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private static final String FIND_SECTION_CONFERENCES_BY_NAME_PAGE = "page.findSectionConferencesInConferencById";
    private static final Logger LOG = LogManager.getLogger(FindSectionConferencesInConferencByIdCommand.class);

    FindSectionConferencesInConferencByIdCommand(SectionConferencService service, RequestFactory requestFactory,
                                                 PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(FIND_PARAM_ID));
        final List<SectionConferenc> conferencesAll;
        try {
            conferencesAll = service.findSectionConferencesInConferencById(id);
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(FIND_SECTION_CONFERENCES_BY_NAME_PAGE));
    }

    public static FindSectionConferencesInConferencByIdCommand getInstance() {
        return FindSectionConferencesInConferencByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindSectionConferencesInConferencByIdCommand INSTANCE =
                new FindSectionConferencesInConferencByIdCommand(ServiceFactory.simple().sectionConferencService(),
                        RequestFactory.getInstance(), PropertyContext.instance());
    }
}
