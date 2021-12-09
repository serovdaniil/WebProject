package com.epam.jwd.finalProject.command.admin.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.UserService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UpdateRoleCommand implements Command {
    private final UserService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String FIND_PARAM_ID_ROLE = "roleNew";
    private static final String FIND_PARAM_ID_ACCOUNT = "id";
    private static final String URL_UPDATE_ROLE_USER_PAGE = "/controller?command=show_users";
    private static final String RESULT_ATTRIBUTE_NAME = "result";
    private static final String OPERATION_WAS_UNSUCCSESFUL = "The operation was unsuccsesful";
    private static final String SHOW_USERS_PAGE = "page.show_users";
    private static final Logger LOG = LogManager.getLogger(UpdateRoleCommand.class);

    UpdateRoleCommand(UserService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long idAccount = Long.parseLong(request.getParameter(FIND_PARAM_ID_ACCOUNT));
        final String nameRole = request.getParameter(FIND_PARAM_ID_ROLE);
        Optional<User> user = Optional.empty();
        try {
            user = service.updateRole(idAccount, nameRole);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        if (user.get().equals(Optional.empty())){
            request.addAttributeToJsp(RESULT_ATTRIBUTE_NAME, OPERATION_WAS_UNSUCCSESFUL);
            return requestFactory.createForwardResponse(propertyContext.get(SHOW_USERS_PAGE));
        }else{
            return requestFactory.createRedirectResponse(URL_UPDATE_ROLE_USER_PAGE);
        }
    }

    public static UpdateRoleCommand getInstance() {
        return UpdateRoleCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UpdateRoleCommand INSTANCE =
                new UpdateRoleCommand(ServiceFactory.simple().userService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
