package com.epam.jwd.finalProject.command.common.function.application;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.service.api.ApplicationService;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * This command is for detailed display of information about the application
 *
 * @author Daniil Serov
 */
public class FindApplicationByIdCommand implements Command {
    private static final String APPLICATION_ATTRIBUTE_NAME = "application";
    private static final String PARAM_NAME = "id";
    private static final String FIND_APPLICATION_BY_ID = "page.applicationById";

    private static final Logger LOG = LogManager.getLogger(FindApplicationByIdCommand.class);

    private final ApplicationService applicationService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    FindApplicationByIdCommand(EntityService<Application> service, RequestFactory requestFactory,
                               PropertyContext propertyContext) {
        this.applicationService = ServiceFactory.simple().applicationService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_NAME));
        final Optional<Application> applicationByID;
        final Application application;
        try {
            applicationByID = applicationService.findId(id);
            application=applicationByID.get();
            request.addAttributeToJsp(APPLICATION_ATTRIBUTE_NAME, application);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(FIND_APPLICATION_BY_ID));
    }

    public static FindApplicationByIdCommand getInstance() {
        return FindApplicationByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindApplicationByIdCommand INSTANCE =
                new FindApplicationByIdCommand(ServiceFactory.simple().serviceFor(Application.class),
                        RequestFactory.getInstance(), PropertyContext.instance());
    }
}
