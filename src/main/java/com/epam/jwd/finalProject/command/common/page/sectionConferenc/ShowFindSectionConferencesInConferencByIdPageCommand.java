package com.epam.jwd.finalProject.command.common.page.sectionConferenc;

import com.epam.jwd.finalProject.command.common.function.sectionConferenc.FindSectionConferencByNameCommand;
import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The find section conferences in conferenc page
 *
 * @author Daniil Serov
 */
public class ShowFindSectionConferencesInConferencByIdPageCommand implements Command {
    private static final String FIND_SECTION_CONFERENCES_BY_NAME_PAGE = "page.findSectionConferencesInConferencById";
    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String FIND_PARAM_NAME = "name";
    private static final String CONFERENCES_ATTRIBUTE_NAME = "conferences";
   private static final Logger LOG = LogManager.getLogger(FindSectionConferencByNameCommand.class);

    ShowFindSectionConferencesInConferencByIdPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
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
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(FIND_SECTION_CONFERENCES_BY_NAME_PAGE));
    }

    public static ShowFindSectionConferencesInConferencByIdPageCommand getInstance() {
        return ShowFindSectionConferencesInConferencByIdPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowFindSectionConferencesInConferencByIdPageCommand INSTANCE =
                new ShowFindSectionConferencesInConferencByIdPageCommand(RequestFactory.getInstance(), PropertyContext.instance());
    }
}
