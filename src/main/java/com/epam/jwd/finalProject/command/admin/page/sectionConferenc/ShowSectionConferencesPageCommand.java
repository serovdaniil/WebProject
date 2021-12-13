package com.epam.jwd.finalProject.command.admin.page.sectionConferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;

public class ShowSectionConferencesPageCommand implements Command {
    private static final String CONFERENCES_ATTRIBUTE_NAME = "sectionConferences";
    private static final String SECTION_CONFERENCES_PAGE = "page.sectionConferences";

    private final EntityService<SectionConferenc> service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowSectionConferencesPageCommand(EntityService<SectionConferenc> service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().serviceFor(SectionConferenc.class);
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<SectionConferenc> sectionConferencesAll = service.findAll();
        request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, sectionConferencesAll);
        return requestFactory.createForwardResponse(propertyContext.get(SECTION_CONFERENCES_PAGE));
    }

    public static ShowSectionConferencesPageCommand getInstance() {
        return ShowSectionConferencesPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowSectionConferencesPageCommand INSTANCE =
                new ShowSectionConferencesPageCommand(ServiceFactory.simple().serviceFor(SectionConferenc.class), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
