package com.epam.jwd.finalProject.command.admin.page.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This command displays all sections of the conference
 *
 * @author Daniil Serov
 */
public class ShowSectionConferencesPageCommand implements Command {
    private static final String CONFERENCES_ATTRIBUTE_NAME = "sectionConferences";
    private static final String PAGES_ATTRIBUTE_NAME = "maxPagesCount";
    private static final String FIND_PARAM_PAGE = "page";
    private static final String SECTION_CONFERENCES_PAGE = "page.sectionConferences";

    private static final Logger LOG = LogManager.getLogger(ShowSectionConferencesPageCommand.class);

    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowSectionConferencesPageCommand() {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final Long pageNum = Long.valueOf(request.getParameter(FIND_PARAM_PAGE));
            final Long count = service.findCountAllSectionConferenc();
            final List<SectionConferenc> sectionConferencesAll = service.findAll(pageNum);
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, sectionConferencesAll);
            request.addAttributeToJsp(PAGES_ATTRIBUTE_NAME, count);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(SECTION_CONFERENCES_PAGE));
    }

    public static ShowSectionConferencesPageCommand getInstance() {
        return ShowSectionConferencesPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowSectionConferencesPageCommand INSTANCE =
                new ShowSectionConferencesPageCommand();
    }
}
