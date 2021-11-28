package com.epam.jwd.finalProject.command.admin.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.service.api.CategoryService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;

public class CreateCategoryCommand implements Command {
    private final CategoryService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_NAME = "name";
    private static final String CATEGORIES_ATTRIBUTE_NAME_RESULT = "result";
    private static final String CATEGORIES_ATTRIBUTE_NAME = "categories";
    private static final String CATEGORIES_PAGE = "page.categories";

    CreateCategoryCommand(CategoryService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().categoryService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String name = request.getParameter(PARAM_NAME);
        final boolean resultCreate = service.create(name);
        final List<Category> categoriesALL = service.findAll();
        String result;
        if (!resultCreate) {
            result = "Unsuccessful create";
        } else {
            result = "Successful create";
        }
        request.addAttributeToJsp(CATEGORIES_ATTRIBUTE_NAME, categoriesALL);
        request.addAttributeToJsp(CATEGORIES_ATTRIBUTE_NAME_RESULT, result);
        return requestFactory.createForwardResponse(propertyContext.get(CATEGORIES_PAGE));
    }

    public static CreateCategoryCommand getInstance() {
        return CreateCategoryCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreateCategoryCommand INSTANCE =
                new CreateCategoryCommand(ServiceFactory.simple().categoryService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
