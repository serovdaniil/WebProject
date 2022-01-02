package com.epam.jwd.finalProject.command.admin.page.application;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.service.api.EntityService;
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
    private static final String APPLICATIONS_ATTRIBUTE_NAME = "applications";
    private static final String APPLICATIONS_PAGE = "page.applications";
    private static final Logger LOG = LogManager.getLogger(ShowApplicationPageCommand.class);

    private final EntityService<Application> service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowApplicationPageCommand(EntityService<Application> service, RequestFactory requestFactory,
                               PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().serviceFor(Application.class);
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final List<Application> applicationList = service.findAll();
            request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME, applicationList);
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
                new ShowApplicationPageCommand(ServiceFactory.simple().serviceFor(Application.class),
                        RequestFactory.getInstance(), PropertyContext.instance());
    }
}
