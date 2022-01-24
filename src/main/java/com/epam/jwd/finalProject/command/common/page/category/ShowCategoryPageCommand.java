package com.epam.jwd.finalProject.command.common.page.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.service.api.CategoryService;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This page displays categories
 *
 * @author Daniil Serov
 */
public class ShowCategoryPageCommand implements Command {
    private static final String FIND_PARAM_PAGE = "page";
    private static final String PAGES_ATTRIBUTE_NAME = "maxPagesCount";
    private static final String CATEGORIES_ATTRIBUTE_NAME = "categories";
    private static final String CATEGORIES_PAGE = "page.categories";

    private static final Logger LOG = LogManager.getLogger(ShowCategoryPageCommand.class);

    private final CategoryService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowCategoryPageCommand(CategoryService service, RequestFactory requestFactory,
                            PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().categoryService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final Long pageNum = Long.valueOf(request.getParameter(FIND_PARAM_PAGE));
            final Long count = service.findCountAllCategory();
            final List<Category> categoriesALL = service.findAll(pageNum);
            request.addAttributeToJsp(CATEGORIES_ATTRIBUTE_NAME, categoriesALL);
            request.addAttributeToJsp(PAGES_ATTRIBUTE_NAME, count);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(CATEGORIES_PAGE));
    }

    public static ShowCategoryPageCommand getInstance() {
        return ShowCategoryPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowCategoryPageCommand INSTANCE =
                new ShowCategoryPageCommand(ServiceFactory.simple().categoryService(),
                        RequestFactory.getInstance(), PropertyContext.instance());
    }
}
