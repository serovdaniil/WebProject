package com.epam.jwd.finalProject.command.common.function.user;

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
 * This command is for updating the first name
 *
 * @author Daniil Serov
 */
public class UpdateFirstNameCommand implements Command {
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String FIND_PARAM_FIRTS_NAME = "firstName";
    private static final String RESULT_ATTRIBUTE_NAME = "result";
    private static final String URL_ACCOUNT_PAGE = "/controller?command=show_personal_infomation";
    private static final String ACCOUNT_PAGE = "page.personalInformation";
    private static final String UNSUCCESSFUL_RESULT_UPDATE_INFORMATION = "Unsuccessful " +
            "updating of personal information";

    private static final Logger LOG = LogManager.getLogger(UpdateFirstNameCommand.class);

    private final UserService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    UpdateFirstNameCommand() {
        this.service = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Optional<User> userOptional = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        Long id=(long)0;
        String newFirstName="";
        if (userOptional.isPresent()){
            id = userOptional.get().getId();
        }
        final String firstName = request.getParameter(FIND_PARAM_FIRTS_NAME);
        Optional<User> user = Optional.empty();
        try {
            user = service.updateFirstName(id, firstName);
            if (user.isPresent()){
                newFirstName=user.get().getFirstName();
            }
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        if (firstName.equals(newFirstName) && (user.isPresent())) {
            request.clearSession();
            request.createSession();
            request.addToSession(USER_SESSION_ATTRIBUTE_NAME, user.get());
            return requestFactory.createRedirectResponse(URL_ACCOUNT_PAGE);
        } else {
            request.addAttributeToJsp(RESULT_ATTRIBUTE_NAME, UNSUCCESSFUL_RESULT_UPDATE_INFORMATION);
            return requestFactory.createForwardResponse(propertyContext.get(ACCOUNT_PAGE));
        }
    }

    public static UpdateFirstNameCommand getInstance() {
        return UpdateFirstNameCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdateFirstNameCommand INSTANCE =
                new UpdateFirstNameCommand();
    }
}