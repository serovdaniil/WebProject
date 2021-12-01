package com.epam.jwd.finalProject.command.admin.conferenc;

import com.epam.jwd.finalProject.command.admin.application.RemoveApplicationByIdCommand;
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

public class RemoveConferencByIdCommand implements Command {
    private final ConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String CONFERENCES_ATTRIBUTE_NAME_RESULT_REMOVE = "result";
    private static final String CONFERENCES_ATTRIBUTE_NAME = "conferences";
    private static final String CONFERENCES_PAGE = "page.conferences";
    private static final Logger LOG = LogManager.getLogger(RemoveConferencByIdCommand.class);

    RemoveConferencByIdCommand(ConferencService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().conferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final boolean resultRemove;
        try {LOG.info("11111");
            resultRemove = service.remove(id);
            LOG.info("222222");
            final List<Conferenc> conferencesAll = service.findAll();
            LOG.info("33333");
            String result;
            if (!resultRemove){
                result="Successful remove";
            }else{result="Unsuccessful remove";}
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME, conferencesAll);
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME_RESULT_REMOVE, result);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(CONFERENCES_PAGE));
    }

    public static RemoveConferencByIdCommand getInstance() {
        return RemoveConferencByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final RemoveConferencByIdCommand INSTANCE =
                new RemoveConferencByIdCommand(ServiceFactory.simple().conferencService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }

}
