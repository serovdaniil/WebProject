package com.epam.jwd.finalProject.command.admin.function.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This command is for updating the status of the conference
 *
 * @author Daniil Serov
 */
public class ChangeStatusConferencCommand implements Command {
    private final ConferencService service;
    private final SectionConferencService sectionConferencService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String PARAM_STATUS = "newStatus";
    private static final String CONFERENCES_ATTRIBUTE_NAME_RESULT_REMOVE = "result";
    private static final String OPERATION_WAS_UNSUCCSESFUL = "The operation was unsuccsesful";
    private static final String CONFERENC_READ_CONFERENC_BY_ID_PAGE = "page.readConferencById";
    private static final String CONFERENCES_PAGE = "/controller?command=show_all_conferences";
    private static final Logger LOG = LogManager.getLogger(RemoveConferencByIdCommand.class);

    ChangeStatusConferencCommand(ConferencService service, SectionConferencService sectionConferencService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().conferencService();
        this.sectionConferencService = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final String status = request.getParameter(PARAM_STATUS);
        boolean resultChange = false;
        try {
            resultChange = service.changeStatus(id,status);
            if ((status.equals("DELETE")) || (status.equals("Удаленная")) || (status.equals("Distant")) || (status.equals("Выдалены"))){
            resultChange=sectionConferencService.changeStatusAfterUpdateConferenc(id);}
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        if (!resultChange) {
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME_RESULT_REMOVE, OPERATION_WAS_UNSUCCSESFUL);
            return requestFactory.createForwardResponse(propertyContext.get(CONFERENC_READ_CONFERENC_BY_ID_PAGE));
        } else {
            return requestFactory.createRedirectResponse(CONFERENCES_PAGE);
        }
    }

    public static ChangeStatusConferencCommand getInstance() {
        return ChangeStatusConferencCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ChangeStatusConferencCommand INSTANCE =
                new ChangeStatusConferencCommand(ServiceFactory.simple().conferencService(), ServiceFactory.simple().sectionConferencService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
