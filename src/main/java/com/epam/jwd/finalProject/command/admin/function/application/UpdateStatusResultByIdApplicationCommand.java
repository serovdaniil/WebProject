package com.epam.jwd.finalProject.command.admin.function.application;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.service.api.ApplicationService;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateStatusResultByIdApplicationCommand implements Command {
    private static final String PARAM_ID = "id";
    private static final String PARAM_RESULT = "result";
    private static final String APPLICATIONS_ATTRIBUTE_NAME_RESULT = "result";
    private static final String URL_APPLICATIONS_PAGE = "/controller?command=show_applications";
    private static final Logger LOG = LogManager.getLogger(UpdateStatusResultByIdApplicationCommand.class);

    private final ApplicationService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    UpdateStatusResultByIdApplicationCommand(EntityService<Application> service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().applicationService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id =Long.parseLong(request.getParameter(PARAM_ID));
        final String nameResult =request.getParameter(PARAM_RESULT);
        final boolean resultUpdate;
        try {
            resultUpdate = service.updateIdStatusApplication(id,nameResult);
            String result;
            if (!resultUpdate) {
                result = "Unsuccessful update";
            } else {
                result = "Successful update";
            }
            request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME_RESULT, result);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        return requestFactory.createRedirectResponse(URL_APPLICATIONS_PAGE);
    }

    public static UpdateStatusResultByIdApplicationCommand getInstance() {
        return UpdateStatusResultByIdApplicationCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdateStatusResultByIdApplicationCommand INSTANCE =
                new UpdateStatusResultByIdApplicationCommand(ServiceFactory.simple().serviceFor(Application.class), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
