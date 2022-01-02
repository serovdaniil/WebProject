package com.epam.jwd.finalProject.command.common.page.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;
/**
 * This page displays categories
 *
 * @author Daniil Serov
 */
public class ShowCategoryPageCommand implements Command {
    private static final String CATEGORIES_ATTRIBUTE_NAME = "categories";
    private static final String CATEGORIES_PAGE = "page.categories";

    private final EntityService<Category> service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowCategoryPageCommand(EntityService<Category> service, RequestFactory requestFactory,
                            PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().serviceFor(Category.class);
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<Category> categoriesALL = service.findAll();
        request.addAttributeToJsp(CATEGORIES_ATTRIBUTE_NAME, categoriesALL);
        return requestFactory.createForwardResponse(propertyContext.get(CATEGORIES_PAGE));
    }

    public static ShowCategoryPageCommand getInstance() {
        return ShowCategoryPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowCategoryPageCommand INSTANCE =
                new ShowCategoryPageCommand(ServiceFactory.simple().serviceFor(Category.class),
                        RequestFactory.getInstance(), PropertyContext.instance());
    }
}
