package com.epam.jwd.finalProject.command.admin.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.CategoryService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

public class ChangeNameCategoryCommand implements Command {
    private final CategoryService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_NAME = "name";
    private static final String PARAM_ID = "id";
    private static final String CATEGORIES_ATTRIBUTE_NAME = "result";
    private static final String UPDATE_NAME_CATEGORY_BY_ID_PAGE = "page.changeNameCategory";
    ChangeNameCategoryCommand(CategoryService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().categoryService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id =Long.parseLong(request.getParameter(PARAM_ID));
        final String name = request.getParameter(PARAM_NAME);
        final boolean result = service.changeName(id,name);
        request.addAttributeToJsp(CATEGORIES_ATTRIBUTE_NAME, result);
        return requestFactory.createForwardResponse(propertyContext.get(UPDATE_NAME_CATEGORY_BY_ID_PAGE));
    }

    public static ChangeNameCategoryCommand getInstance() {
        return ChangeNameCategoryCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ChangeNameCategoryCommand INSTANCE =
                new ChangeNameCategoryCommand(ServiceFactory.simple().categoryService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
