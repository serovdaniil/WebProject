package com.epam.jwd.finalProject.command.common.function.application;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.ApplicationService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * This command is used to create an application
 *
 * @author Daniil Serov
 */
public class CreateApplicationCommand implements Command {
    private static final String PAGES_ATTRIBUTE_NAME = "maxPagesCount";
    private static final String PARAM_ID = "id";
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String APPLICATIONS_ACCOUNT_PAGE = "page.applicationsByAccount";
    private static final String APPLICATIONS_ATTRIBUTE_NAME_SECTION_CONFERENC = "applications";
    private static final String ERROR_DUPLICATE_PASS_ATTRIBUTE = "errorDuplicatePassMessage";
    private static final String APPLICATIONS_ATTRIBUTE_NAME = "result";
    private static final String APPLICATIONS_PAGE = "/controller?command=show_applications_by_account&page=1";
    private static final String ERROR_DUPLICATE_PASS_MESSAGE = "You already have an application for training, " +
            "contact the administrator for details!";

    private static final Logger LOG = LogManager.getLogger(CreateApplicationCommand.class);

    private final ApplicationService applicationService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    CreateApplicationCommand() {
        this.applicationService = ServiceFactory.simple().applicationService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Optional<User> userOptional = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        Long idAccount = (long) 0;
        if (userOptional.isPresent()) {
            idAccount = userOptional.get().getId();
        }
        final Long idSectionConferenc = Long.parseLong(request.getParameter(PARAM_ID));
        boolean result;
        final List<Application> applicationList;
        try {
            final Long count = applicationService.findCountAllApplicationByUser(idAccount);
            for (long i = 1; i < 3; i++) {
                result = applicationService.findForDuplicateApplication(idAccount, idSectionConferenc, i);
                if (result) {
                    request.addAttributeToJsp(ERROR_DUPLICATE_PASS_ATTRIBUTE, ERROR_DUPLICATE_PASS_MESSAGE);
                    applicationList = applicationService.findAccountIdByApplication(idAccount, (long) 1);
                    request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME_SECTION_CONFERENC, applicationList);
                    request.addAttributeToJsp(PAGES_ATTRIBUTE_NAME, count);
                    return requestFactory.createForwardResponse(propertyContext.get(APPLICATIONS_ACCOUNT_PAGE));
                }
            }
            result = applicationService.create(idAccount, idSectionConferenc, (long) 1);
            applicationList = applicationService.findAccountIdByApplication(idAccount, (long) 1);
            request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME_SECTION_CONFERENC, applicationList);
            request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME, result);
            request.addAttributeToJsp(PAGES_ATTRIBUTE_NAME, count);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createRedirectResponse(APPLICATIONS_PAGE);
    }

    public static CreateApplicationCommand getInstance() {
        return CreateApplicationCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreateApplicationCommand INSTANCE =
                new CreateApplicationCommand();
    }
}
