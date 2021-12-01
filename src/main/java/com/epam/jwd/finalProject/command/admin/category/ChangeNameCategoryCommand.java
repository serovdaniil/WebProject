package com.epam.jwd.finalProject.command.admin.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.service.api.CategoryService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ChangeNameCategoryCommand implements Command {
    private final CategoryService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_NAME = "name";
    private static final String PARAM_ID = "id";
    private static final String CATEGORIES_ATTRIBUTE_NAME_RESULT = "result";
    private static final String CATEGORIES_ATTRIBUTE_NAME = "categories";
    private static final String CATEGORIES_PAGE = "page.categories";
    private static final Logger LOG = LogManager.getLogger(ChangeNameCategoryCommand.class);

    ChangeNameCategoryCommand(CategoryService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().categoryService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final String name = request.getParameter(PARAM_NAME);
        final boolean resultChange;
        try {
            resultChange = service.changeName(id, name);
            final List<Category> categoriesALL = service.findAll();
            String result;
            if (!resultChange) {
                result = "Unsuccessful change name";
            } else {
                result = "Successful change name";
            }
            request.addAttributeToJsp(CATEGORIES_ATTRIBUTE_NAME, categoriesALL);
            request.addAttributeToJsp(CATEGORIES_ATTRIBUTE_NAME_RESULT, result);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(CATEGORIES_PAGE));
    }

    public static ChangeNameCategoryCommand getInstance() {
        return ChangeNameCategoryCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ChangeNameCategoryCommand INSTANCE =
                new ChangeNameCategoryCommand(ServiceFactory.simple().categoryService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
