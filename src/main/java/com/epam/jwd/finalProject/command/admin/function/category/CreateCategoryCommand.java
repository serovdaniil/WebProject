package com.epam.jwd.finalProject.command.admin.function.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.CategoryService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This command is for creating a category
 *
 * @author Daniil Serov
 */
public class CreateCategoryCommand implements Command {
    private static final String PARAM_NAME = "name";
    private static final String CATEGORIES_ATTRIBUTE_NAME_RESULT = "result";
    private static final String OPERATION_WAS_UNSUCCSESFUL = "The operation was unsuccsesful";
    private static final String CATEGORY_ADMIN_PANEL_PAGE = "page.adminPanelCategory";
    private static final String CATEGORY_PAGE = "/controller?command=show_categories&page=1";

    private static final Logger LOG = LogManager.getLogger(CreateCategoryCommand.class);

    private final CategoryService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    CreateCategoryCommand() {
        this.service = ServiceFactory.simple().categoryService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String name = request.getParameter(PARAM_NAME);
        boolean resultCreate = false;
        try {
            resultCreate=service.findForDuplicateCategory(name);
            if (resultCreate) {
                request.addAttributeToJsp(CATEGORIES_ATTRIBUTE_NAME_RESULT, OPERATION_WAS_UNSUCCSESFUL);
                return requestFactory.createForwardResponse(propertyContext.get(CATEGORY_ADMIN_PANEL_PAGE));
            }
            resultCreate = service.create(name);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        if (!resultCreate) {
            request.addAttributeToJsp(CATEGORIES_ATTRIBUTE_NAME_RESULT, OPERATION_WAS_UNSUCCSESFUL);
            return requestFactory.createForwardResponse(propertyContext.get(CATEGORY_ADMIN_PANEL_PAGE));
        } else {
            return requestFactory.createRedirectResponse(CATEGORY_PAGE);
        }
    }

    public static CreateCategoryCommand getInstance() {
        return CreateCategoryCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreateCategoryCommand INSTANCE =
                new CreateCategoryCommand();
    }
}
