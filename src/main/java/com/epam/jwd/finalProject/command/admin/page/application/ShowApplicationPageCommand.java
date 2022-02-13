package com.epam.jwd.finalProject.command.admin.page.application;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.service.api.ApplicationService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This command displays all applications for training
 *
 * @author Daniil Serov
 */
public class ShowApplicationPageCommand implements Command {
    private static final String FIND_PARAM_PAGE = "page";
    private static final String PAGES_ATTRIBUTE_NAME = "maxPagesCount";
    private static final String APPLICATIONS_ATTRIBUTE_NAME = "applications";
    private static final String APPLICATIONS_PAGE = "page.applications";

    private static final Logger LOG = LogManager.getLogger(ShowApplicationPageCommand.class);

    private final ApplicationService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowApplicationPageCommand() {
        this.service = ServiceFactory.simple().applicationService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final Long pageNum = Long.valueOf(request.getParameter(FIND_PARAM_PAGE));
            final Long count = service.findCountAllApplication();
            final List<Application> applicationList = service.findAll(pageNum);
            request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME, applicationList);
            request.addAttributeToJsp(PAGES_ATTRIBUTE_NAME, count);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(APPLICATIONS_PAGE));
    }

    public static ShowApplicationPageCommand getInstance() {
        return ShowApplicationPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowApplicationPageCommand INSTANCE =
                new ShowApplicationPageCommand();
    }
}
