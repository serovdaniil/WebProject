package com.epam.jwd.finalProject.command.admin.function.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.UserService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * This command displays detailed information about the user
 *
 * @author Daniil Serov
 */
public class ReadUserByIdCommand implements Command {
    private static final String FIND_PARAM_ID_ACCOUNT = "id";
    private static final String USER_ATTRIBUTE_NAME = "user";
    private static final String READ_USER_BY_ID_PAGE = "page.readUserById";

    private static final Logger LOG = LogManager.getLogger(ReadUserByIdCommand.class);

    private final UserService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ReadUserByIdCommand() {
        this.service = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long idAccount = Long.parseLong(request.getParameter(FIND_PARAM_ID_ACCOUNT));
        final Optional<User> optionalUser;
        User user = null;
        try {
            optionalUser = service.findId(idAccount);
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }
            request.addAttributeToJsp(USER_ATTRIBUTE_NAME, user);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }

        return requestFactory.createForwardResponse(propertyContext.get(READ_USER_BY_ID_PAGE));
    }

    public static ReadUserByIdCommand getInstance() {
        return ReadUserByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ReadUserByIdCommand INSTANCE =
                new ReadUserByIdCommand();
    }
}
