package com.epam.jwd.finalProject.command.common.function.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This command is used to search for name conferenc
 *
 * @author Daniil Serov
 */
public class FindSectionConferencByNameCommand implements Command {
    private static final String FIND_PARAM_NAME = "name";
    private static final String SECTION_CONFERENCES_ATTRIBUTE_NAME = "sectionConferenc";
    private static final String SECTION_CONFERENCES_PAGE = "page.adminPanelSectionConferenc";

    private static final Logger LOG = LogManager.getLogger(FindSectionConferencByNameCommand.class);

    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    FindSectionConferencByNameCommand(SectionConferencService service, RequestFactory requestFactory,
                                      PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String name = request.getParameter(FIND_PARAM_NAME);
        final List<SectionConferenc> conferencesAll;
        try {
            conferencesAll = service.findByName(name);
            request.addAttributeToJsp(SECTION_CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(SECTION_CONFERENCES_PAGE));
    }

    public static FindSectionConferencByNameCommand getInstance() {
        return FindSectionConferencByNameCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindSectionConferencByNameCommand INSTANCE =
                new FindSectionConferencByNameCommand(ServiceFactory.simple().sectionConferencService(),
                        RequestFactory.getInstance(), PropertyContext.instance());
    }
}
