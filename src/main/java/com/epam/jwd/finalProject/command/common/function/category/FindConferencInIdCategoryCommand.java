package com.epam.jwd.finalProject.command.common.function.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.api.CategoryService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This command is used to search for conferences in the category
 *
 * @author Daniil Serov
 */
public class FindConferencInIdCategoryCommand implements Command {
    private static final String PARAM_ID = "id";
    private static final String ALL_CONFERENC_IN_CATEGORY_ATTRIBUTE_NAME = "conferences";
    private static final String ALL_CONFERENC_IN_CATEGORY_PAGE = "page.allConferencesInCategory";

    private static final Logger LOG = LogManager.getLogger(FindConferencInIdCategoryCommand.class);

    private final CategoryService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    FindConferencInIdCategoryCommand() {
        this.service = ServiceFactory.simple().categoryService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final List<Conferenc> conferencInIdCategory;
        try {
            conferencInIdCategory = service.findConferencInIdCategory(id);
            request.addAttributeToJsp(ALL_CONFERENC_IN_CATEGORY_ATTRIBUTE_NAME, conferencInIdCategory);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(ALL_CONFERENC_IN_CATEGORY_PAGE));
    }

    public static FindConferencInIdCategoryCommand getInstance() {
        return FindConferencInIdCategoryCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindConferencInIdCategoryCommand INSTANCE =
                new FindConferencInIdCategoryCommand();
    }
}
