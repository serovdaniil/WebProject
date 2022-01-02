package com.epam.jwd.finalProject.command.common.function.application;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.ApplicationService;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * This command is used to display all user requests
 *
 * @author Daniil Serov
 */
public class FindAccountIdByApplicationCommand implements Command {
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String APPLICATIONS_ATTRIBUTE_NAME = "applications";
    private static final String APPLICATIONS_PAGE = "page.applicationsByAccount";
    private static final Logger LOG = LogManager.getLogger(FindAccountIdByApplicationCommand.class);

    private final ApplicationService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    FindAccountIdByApplicationCommand(EntityService<Application> service, RequestFactory requestFactory,
                                      PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().applicationService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Optional<User> userOptional = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        final Long id = userOptional.get().getId();
        final List<Application> applicationList;
        try {
            applicationList = service.findAccountIdByApplication(id);
            request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME, applicationList);
        }  catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(APPLICATIONS_PAGE));
    }

    public static FindAccountIdByApplicationCommand getInstance() {
        return FindAccountIdByApplicationCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindAccountIdByApplicationCommand INSTANCE =
                new FindAccountIdByApplicationCommand(ServiceFactory.simple().serviceFor(Application.class),
                        RequestFactory.getInstance(), PropertyContext.instance());
    }
}
