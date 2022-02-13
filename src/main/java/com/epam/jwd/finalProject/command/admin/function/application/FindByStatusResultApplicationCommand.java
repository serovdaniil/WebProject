package com.epam.jwd.finalProject.command.admin.function.application;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.service.api.ApplicationService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This command is for searching by the status of the application for training
 *
 * @author Daniil Serov
 */
public class FindByStatusResultApplicationCommand implements Command {
    private static final String PARAM_NAME_RESULT = "resultNew";
    private static final String APPLICATIONS_ATTRIBUTE_NAME = "applications";
    private static final String APPLICATIONS_PAGE = "page.applicationsByStatusResult";

    private static final Logger LOG = LogManager.getLogger(FindByStatusResultApplicationCommand.class);

    private final ApplicationService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    FindByStatusResultApplicationCommand() {
        this.service = ServiceFactory.simple().applicationService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String nameResult = request.getParameter(PARAM_NAME_RESULT);
        final List<Application> applicationList;
        try {
            applicationList = service.findByStatusResult(nameResult);
            request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME, applicationList);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(APPLICATIONS_PAGE));
    }

    public static FindByStatusResultApplicationCommand getInstance() {
        return FindByStatusResultApplicationCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindByStatusResultApplicationCommand INSTANCE =
                new FindByStatusResultApplicationCommand();
    }
}
