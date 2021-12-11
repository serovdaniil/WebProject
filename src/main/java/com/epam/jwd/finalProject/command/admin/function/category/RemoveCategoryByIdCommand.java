package com.epam.jwd.finalProject.command.admin.function.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.CategoryService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RemoveCategoryByIdCommand implements Command {
    private final CategoryService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String CATEGORIES_ATTRIBUTE_NAME_RESULT = "result";
    private static final String OPERATION_WAS_UNSUCCSESFUL = "The operation was unsuccsesful";
    private static final String CATEGORY_ADMIN_PANEL_PAGE = "page.adminPanelCategory";
    private static final String CATEGORY_PAGE = "/controller?command=show_categories";
    private static final Logger LOG = LogManager.getLogger(RemoveCategoryByIdCommand.class);

    RemoveCategoryByIdCommand(CategoryService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().categoryService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id =Long.parseLong(request.getParameter(PARAM_ID));
        boolean resultRemove=false;
        try {
            resultRemove = service.remove(id);
         } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        if (!resultRemove) {
            request.addAttributeToJsp(CATEGORIES_ATTRIBUTE_NAME_RESULT, OPERATION_WAS_UNSUCCSESFUL);
            return requestFactory.createForwardResponse(propertyContext.get(CATEGORY_ADMIN_PANEL_PAGE));
        } else {
            return requestFactory.createRedirectResponse(CATEGORY_PAGE);
        }
    }

    public static RemoveCategoryByIdCommand getInstance() {
        return RemoveCategoryByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final RemoveCategoryByIdCommand INSTANCE =
                new RemoveCategoryByIdCommand(ServiceFactory.simple().categoryService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
