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
 * This command is for creating a conference section
 *
 * @author Daniil Serov
 */
public class CreateSectionConferencCommand implements Command {
    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    private static final String PARAM_NAME = "name";
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_ID_CONFERENC = "idConferenc";
    private static final String SECTIOM_CONFERENCES_ATTRIBUTE_NAME = "result";
    private static final String OPERATION_WAS_UNSUCCSESFUL="The operation was unsuccsesful";
    private static final String SECTION_ADMIN_PANEL_PAGE = "page.adminPanelSectionConferenc";
    private static final String SECTION_CONFERENCES_PAGE = "/controller?command=show_section_conferences";
    private static final Logger LOG = LogManager.getLogger(CreateSectionConferencCommand.class);

    CreateSectionConferencCommand(SectionConferencService service, RequestFactory requestFactory,
                                  PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String name = request.getParameter(PARAM_NAME);
        final String description = request.getParameter(PARAM_DESCRIPTION);
        final Long id = Long.parseLong(request.getParameter(PARAM_ID_CONFERENC));
        boolean resultCreate=false;
        try {
            resultCreate = service.create(name, description, id);
         } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        if (!resultCreate) {
            request.addAttributeToJsp(SECTIOM_CONFERENCES_ATTRIBUTE_NAME, OPERATION_WAS_UNSUCCSESFUL);
            return requestFactory.createForwardResponse(propertyContext.get(SECTION_ADMIN_PANEL_PAGE));
        }else {
        return requestFactory.createRedirectResponse(SECTION_CONFERENCES_PAGE);}
    }

    public static CreateSectionConferencCommand getInstance() {
        return CreateSectionConferencCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreateSectionConferencCommand INSTANCE =
                new CreateSectionConferencCommand(ServiceFactory.simple().sectionConferencService(),
                        RequestFactory.getInstance(), PropertyContext.instance());
    }
}
