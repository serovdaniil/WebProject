package com.epam.jwd.finalProject.command.admin.page.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This command displays all conferences
 *
 * @author Daniil Serov
 */
public class ShowAllConferencesPageCommand implements Command {
    private static final String FIND_PARAM_PAGE = "page";
    private static final String PAGES_ATTRIBUTE_NAME = "maxPagesCount";
    private static final String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private static final String CONFERENCES_PAGE = "page.allConferences";

    private static final Logger LOG = LogManager.getLogger(ShowAllConferencesPageCommand.class);

    private final ConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowAllConferencesPageCommand() {
        this.service = ServiceFactory.simple().conferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final Long pageNum= Long.valueOf(request.getParameter(FIND_PARAM_PAGE));
            final Long count = service.findCountAllConferenc();
            final List<Conferenc> conferencesAll = service.findAllStatus(pageNum);
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
            request.addAttributeToJsp(PAGES_ATTRIBUTE_NAME, count);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(CONFERENCES_PAGE));
    }

    public static ShowAllConferencesPageCommand getInstance() {
        return ShowAllConferencesPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowAllConferencesPageCommand INSTANCE =
                new ShowAllConferencesPageCommand();
    }
}
