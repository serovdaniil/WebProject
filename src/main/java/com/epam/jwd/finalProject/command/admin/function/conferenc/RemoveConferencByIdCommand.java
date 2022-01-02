package com.epam.jwd.finalProject.command.admin.function.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This command is for deleting the conference
 *
 * @author Daniil Serov
 */
public class RemoveConferencByIdCommand implements Command {
    private final ConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String CONFERENCES_ATTRIBUTE_NAME_RESULT_REMOVE = "result";
    private static final String OPERATION_WAS_UNSUCCSESFUL = "The operation was unsuccsesful";
    private static final String CONFERENC_ADMIN_PANEL_PAGE = "page.adminPanelConferenc";
    private static final String CONFERENCES_PAGE = "/controller?command=show_conferences";
    private static final Logger LOG = LogManager.getLogger(RemoveConferencByIdCommand.class);

    RemoveConferencByIdCommand(ConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().conferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        boolean resultRemove = false;
        try {
            resultRemove = service.remove(id);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        if (!resultRemove) {
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME_RESULT_REMOVE, OPERATION_WAS_UNSUCCSESFUL);
            return requestFactory.createForwardResponse(propertyContext.get(CONFERENC_ADMIN_PANEL_PAGE));
        } else {
            return requestFactory.createRedirectResponse(CONFERENCES_PAGE);
        }
    }

    public static RemoveConferencByIdCommand getInstance() {
        return RemoveConferencByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final RemoveConferencByIdCommand INSTANCE =
                new RemoveConferencByIdCommand(ServiceFactory.simple().conferencService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
