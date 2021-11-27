package com.epam.jwd.finalProject.command.admin.user;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.UserService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;
import java.util.Optional;

public class UpdateRoleCommand implements Command {
    private final UserService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String FIND_PARAM_ID_ROLE = "role";
    private static final String FIND_PARAM_ID_ACCOUNT = "id";
    private static final String USERS_ATTRIBUTE_NAME = "users";
    private static final String UPDATE_ROLE_USER_PAGE = "page.users";
    // private static final String SUCCESSFUL_RESULT_UPDATE_INFORMATION = "Successful updating of personal information";
    //private static final String UNSUCCESSFUL_UPDATE_PASSWORD_USER_PAGE = "Unsuccessful updating of personal information";

    UpdateRoleCommand(UserService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().userService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Optional<User> userOptional = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        final Long idAccount = Long.parseLong(request.getParameter(FIND_PARAM_ID_ACCOUNT));
        final Long idRole = Long.parseLong(request.getParameter(FIND_PARAM_ID_ROLE));
        final Optional<User> user = service.updateRole(idAccount, idRole);
        //if (idRole == 1) {
        //  if (Role.USER.equals(user.get().getRole())) {
        //request.addAttributeToJsp(USERS_ATTRIBUTE_NAME, SUCCESSFUL_RESULT_UPDATE_INFORMATION);
        //} else

        //{
        //  request.addAttributeToJsp(USERS_ATTRIBUTE_NAME, UNSUCCESSFUL_UPDATE_PASSWORD_USER_PAGE);
        //}
//}
        //      if(idRole==2){
        //            if(Role.ADMIN.equals(user.get().getRole())){
        //          request.addAttributeToJsp(USERS_ATTRIBUTE_NAME,SUCCESSFUL_RESULT_UPDATE_INFORMATION);
        //        }else{
        //      request.addAttributeToJsp(USERS_ATTRIBUTE_NAME,UNSUCCESSFUL_UPDATE_PASSWORD_USER_PAGE);
        //    }
        //  }
        final List<User> usersAll = service.findAll();
        request.addAttributeToJsp(USERS_ATTRIBUTE_NAME, usersAll);
        return requestFactory.createForwardResponse(propertyContext.get(UPDATE_ROLE_USER_PAGE));
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
