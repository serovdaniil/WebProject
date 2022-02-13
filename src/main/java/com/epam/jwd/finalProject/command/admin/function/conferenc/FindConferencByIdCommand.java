package com.epam.jwd.finalProject.command.admin.function.conferenc;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * This command is for detailed display of the conference
 *
 * @author Daniil Serov
 */
public class FindConferencByIdCommand implements Command {
    private static final String PARAM_ID = "id";
    private static final String CONFERENCES_ATTRIBUTE_NAME_FIND = "conferenc";
    private static final String CONFERENCES_PAGE = "page.readConferencById";

    private static final Logger LOG = LogManager.getLogger(FindConferencByIdCommand.class);

    private final ConferencService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    FindConferencByIdCommand() {
        this.service = ServiceFactory.simple().conferencService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final Optional<Conferenc> conferencOptional;
        Conferenc conferenc = null;
        try {
            conferencOptional = service.findId(id);
            if (conferencOptional.isPresent()) {
                conferenc = conferencOptional.get();
            }
            request.addAttributeToJsp(CONFERENCES_ATTRIBUTE_NAME_FIND, conferenc);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(CONFERENCES_PAGE));
    }

    public static FindConferencByIdCommand getInstance() {
        return FindConferencByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindConferencByIdCommand INSTANCE =
                new FindConferencByIdCommand();
    }
}