package com.epam.jwd.finalProject.command.common.function.sectionConferenc;

import com.epam.jwd.finalProject.command.common.page.conferenc.ShowConferencesPageCommand;
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

public class ShowSectionConferencWithPaginationForFirstPageCommand implements Command {
    private static final String FIND_PARAM_PAGE = "page";
    private static final String PAGES_ATTRIBUTE_NAME = "maxPagesCount";
    private static final String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private static final String ID_CONFERENC_SESSION_ATTRIBUTE_NAME = "idConferenc";
    private static final String FIND_PARAM_ID = "id";
    private static final String FIND_SECTION_CONFERENCES_BY_NAME_PAGE = "page." +
            "findSectionConferencesInConferencById";

    private static final Logger LOG = LogManager.getLogger(ShowConferencesPageCommand.class);

    private final SectionConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowSectionConferencWithPaginationForFirstPageCommand(SectionConferencService service,
                                                          RequestFactory requestFactory,
                                                          PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().sectionConferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final Long pageNum = Long.valueOf(request.getParameter(FIND_PARAM_PAGE));
             Long id = Long.parseLong(request.getParameter(FIND_PARAM_ID));
            if ((pageNum == 1) && !(id == 0)) {
                request.addToSession(ID_CONFERENC_SESSION_ATTRIBUTE_NAME, id);
            }
            final List<SectionConferenc> conferencesAll =
                    service.findSectionConferencesInConferencByIdWithPagination(id, pageNum);
            final Long count = service.findCountAllSectionConferencInConferenc(id);
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
            request.addAttributeToJsp(PAGES_ATTRIBUTE_NAME, count);
        } catch (ServiceException | ValidationException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(FIND_SECTION_CONFERENCES_BY_NAME_PAGE));
    }

    public static ShowSectionConferencWithPaginationForFirstPageCommand getInstance() {
        return ShowSectionConferencWithPaginationForFirstPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowSectionConferencWithPaginationForFirstPageCommand INSTANCE =
                new ShowSectionConferencWithPaginationForFirstPageCommand(ServiceFactory.simple().sectionConferencService(),
                        RequestFactory.getInstance(), PropertyContext.instance());
    }
}
