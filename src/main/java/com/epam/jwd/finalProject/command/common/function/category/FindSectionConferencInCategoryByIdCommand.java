package com.epam.jwd.finalProject.command.common.function.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.CategoryService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This command is used to search for section conferences in the category
 *
 * @author Daniil Serov
 */
public class FindSectionConferencInCategoryByIdCommand implements Command {
    private final CategoryService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String ALL_SECTION_CONFERENC_IN_CATEGORY_ATTRIBUTE_NAME = "sectionConferences";
    private static final String ALL_SECTION_CONFERENC_IN_CATEGORY_PAGE = "page.allSectionConferencesInCategory";
    private static final Logger LOG = LogManager.getLogger(FindSectionConferencInCategoryByIdCommand.class);

    FindSectionConferencInCategoryByIdCommand(CategoryService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().categoryService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final List<SectionConferenc> conferencInIdCategory;
        try {
            conferencInIdCategory = service.findSectionConferencInIdCategory(id);
            request.addAttributeToJsp(ALL_SECTION_CONFERENC_IN_CATEGORY_ATTRIBUTE_NAME, conferencInIdCategory);

        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(ALL_SECTION_CONFERENC_IN_CATEGORY_PAGE));
    }

    public static FindSectionConferencInCategoryByIdCommand getInstance() {
        return FindSectionConferencInCategoryByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindSectionConferencInCategoryByIdCommand INSTANCE =
                new FindSectionConferencInCategoryByIdCommand(ServiceFactory.simple().categoryService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
