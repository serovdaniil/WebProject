package com.epam.jwd.finalProject.command.common.function.conferenc;

import com.epam.jwd.finalProject.command.admin.function.conferenc.RemoveConferencByIdCommand;
import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindConferencByNameCommand implements Command {
    private final ConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String FIND_PARAM_NAME = "name";
    private static final String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private static final String FIND_CONFERENCES_BY_NAME_PAGE = "page.findConferencesByName";
    private static final Logger LOG = LogManager.getLogger(RemoveConferencByIdCommand.class);

    FindConferencByNameCommand(ConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().conferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String name = request.getParameter(FIND_PARAM_NAME);
        final List<Conferenc> conferencesAll;
        LOG.info(name);
        try {
            conferencesAll = service.findByName(name);
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
        }  catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(FIND_CONFERENCES_BY_NAME_PAGE));
    }

    public static FindConferencByNameCommand getInstance() {
        return FindConferencByNameCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindConferencByNameCommand INSTANCE =
                new FindConferencByNameCommand(ServiceFactory.simple().conferencService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
