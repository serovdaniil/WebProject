package com.epam.jwd.finalProject.command.admin.function.category;

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

import java.util.Optional;

/**
 * This command is for detailed display of the category
 *
 * @author Daniil Serov
 */
public class FindCategoryByIdCommand implements Command {
    private final CategoryService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String CATEGORIES_ATTRIBUTE_NAME = "category";
    private static final String CATEGORIES_PAGE = "page.adminPanelCategory";
    private static final Logger LOG = LogManager.getLogger(FindCategoryByIdCommand.class);

    FindCategoryByIdCommand(CategoryService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().categoryService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final Optional<Category> category;
        try {
            category = service.findId(id);
            request.addAttributeToJsp(CATEGORIES_ATTRIBUTE_NAME, category);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(CATEGORIES_PAGE));
    }

    public static FindCategoryByIdCommand getInstance() {
        return FindCategoryByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindCategoryByIdCommand INSTANCE =
                new FindCategoryByIdCommand(ServiceFactory.simple().categoryService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
