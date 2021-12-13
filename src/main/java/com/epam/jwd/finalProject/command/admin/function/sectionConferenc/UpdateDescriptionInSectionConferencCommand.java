package com.epam.jwd.finalProject.command.admin.function.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateDescriptionInSectionConferencCommand implements Command {
    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    private static final String PARAM_ID = "id";
    private static final String PARAM_DESCTIPTION = "description";
    private static final String RESULT_ATTRIBUTE_NAME = "result";
    private static final String OPERATION_WAS_UNSUCCSESFUL = "The operation was unsuccsesful";
    private static final String SECTION_ADMIN_PANEL_PAGE = "page.adminPanelSectionConferenc";
    private static final String SECTION_CONFERENCES_PAGE = "/controller?command=show_section_conferences";
    private static final Logger LOG = LogManager.getLogger(UpdateDescriptionInSectionConferencCommand.class);

    UpdateDescriptionInSectionConferencCommand(SectionConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
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
            return requestFactory.createForwardResponse(propertyContext.get(SECTION_ADMIN_PANEL_PAGE));
        } else {
            return requestFactory.createRedirectResponse(SECTION_CONFERENCES_PAGE);
        }
    }

    public static UpdateDescriptionInSectionConferencCommand getInstance() {
        return UpdateDescriptionInSectionConferencCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdateDescriptionInSectionConferencCommand INSTANCE =
                new UpdateDescriptionInSectionConferencCommand(ServiceFactory.simple().sectionConferencService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
