package com.epam.jwd.finalProject.command.admin.function.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This command is for updating the description of the conference
 *
 * @author Daniil Serov
 */
public class UpdateDescriptionInConferencCommand implements Command {
    private final ConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String PARAM_DESCTIPTION = "description";
    private static final String RESULT_ATTRIBUTE_NAME = "result";
    private static final String OPERATION_WAS_UNSUCCSESFUL = "The operation was unsuccsesful";
    private static final String CONFERENC_ADMIN_PANEL_PAGE = "page.adminPanelConferenc";
    private static final String CONFERENCES_PAGE = "/controller?command=show_conferences";
    private static final Logger LOG = LogManager.getLogger(FindConferencByIdCommand.class);

    UpdateDescriptionInConferencCommand(ConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().conferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final String description = request.getParameter(PARAM_DESCTIPTION);
        boolean resultUpdate = false;
        try {
            resultUpdate = service.updateDescription(id, description);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        if (!resultUpdate) {
            request.addAttributeToJsp(RESULT_ATTRIBUTE_NAME, OPERATION_WAS_UNSUCCSESFUL);
            return requestFactory.createForwardResponse(propertyContext.get(CONFERENC_ADMIN_PANEL_PAGE));
        } else {
            return requestFactory.createRedirectResponse(CONFERENCES_PAGE);
        }
    }

    public static UpdateDescriptionInConferencCommand getInstance() {
        return UpdateDescriptionInConferencCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdateDescriptionInConferencCommand INSTANCE =
                new UpdateDescriptionInConferencCommand(ServiceFactory.simple().conferencService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
