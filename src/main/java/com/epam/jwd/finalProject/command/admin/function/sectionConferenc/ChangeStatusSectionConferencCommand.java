package com.epam.jwd.finalProject.command.admin.function.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This command is for updating the status of the conference section
 *
 * @author Daniil Serov
 */
public class ChangeStatusSectionConferencCommand implements Command {
    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    private static final String PARAM_ID = "id";
    private static final String PARAM_STATUS = "newStatus";
    private static final String CONFERENCES_ATTRIBUTE_NAME_RESULT_REMOVE = "result";
    private static final String OPERATION_WAS_UNSUCCSESFUL = "The operation was unsuccsesful";
    private static final String SECTION_CONFERENCES_ATTRIBUTE_NAME = "sectionConferenc";
    private static final String READ_SECTION_CONFERENCES_BY_ID_PAGE = "page.readSectionConferencById";
    private static final String SECTION_CONFERENCES_PAGE = "/controller?command=show_section_conferences";
    private static final Logger LOG = LogManager.getLogger(FindSectionConferencByIdCommand.class);

    ChangeStatusSectionConferencCommand(SectionConferencService service, RequestFactory requestFactory,
                                        PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final String status = request.getParameter(PARAM_STATUS);
        boolean resultChange=false;
        try {
            resultChange = service.changeStatus(id,status);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        if (!resultChange) {
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME_RESULT_REMOVE, OPERATION_WAS_UNSUCCSESFUL);
            return requestFactory.createForwardResponse(propertyContext.get(READ_SECTION_CONFERENCES_BY_ID_PAGE));
        } else {
            return requestFactory.createRedirectResponse(SECTION_CONFERENCES_PAGE);
        }
    }

    public static ChangeStatusSectionConferencCommand getInstance() {
        return ChangeStatusSectionConferencCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ChangeStatusSectionConferencCommand INSTANCE =
                new ChangeStatusSectionConferencCommand(ServiceFactory.simple().sectionConferencService(),
                        RequestFactory.getInstance(), PropertyContext.instance());
    }
}
