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

/**
 * This command is for deleting the conference section
 *
 * @author Daniil Serov
 */
public class RemoveSectionConferencByIdCommand implements Command {
    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    private static final String PARAM_ID = "id";
    private static final String SECTION_CONFERENCES_ATTRIBUTE_NAME_RESULT = "result";
    private static final String OPERATION_WAS_UNSUCCSESFUL = "The operation was unsuccsesful";
    private static final String SECTION_ADMIN_PANEL_PAGE = "page.adminPanelSectionConferenc";
    private static final String SECTION_CONFERENCES_PAGE = "/controller?command=show_section_conferences";
    private static final Logger LOG = LogManager.getLogger(RemoveSectionConferencByIdCommand.class);

    RemoveSectionConferencByIdCommand(SectionConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
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
        }
        if (!resultRemove) {
            request.addAttributeToJsp(SECTION_CONFERENCES_ATTRIBUTE_NAME_RESULT, OPERATION_WAS_UNSUCCSESFUL);
            return requestFactory.createForwardResponse(propertyContext.get(SECTION_ADMIN_PANEL_PAGE));
        } else {
            return requestFactory.createRedirectResponse(SECTION_CONFERENCES_PAGE);
        }
    }

    public static RemoveSectionConferencByIdCommand getInstance() {
        return RemoveSectionConferencByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final RemoveSectionConferencByIdCommand INSTANCE =
                new RemoveSectionConferencByIdCommand(ServiceFactory.simple().sectionConferencService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
