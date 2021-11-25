package com.epam.jwd.finalProject.command.common.category;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.api.CategoryService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;

public class FindConferencInIdCategoryCommand implements Command {
    private final CategoryService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String PARAM_ID = "id";
    private static final String ALL_CONFERENC_IN_CATEGORY_ATTRIBUTE_NAME = "conferences";
    private static final String ALL_CONFERENC_IN_CATEGORY_PAGE = "page.allConferencesInCategory";
    FindConferencInIdCategoryCommand(CategoryService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().categoryService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id =Long.parseLong(request.getParameter(PARAM_ID));
        final List<Conferenc> conferencInIdCategory= service.findConferencInIdCategory(id);
        request.addAttributeToJsp(ALL_CONFERENC_IN_CATEGORY_ATTRIBUTE_NAME, conferencInIdCategory);
        return requestFactory.createForwardResponse(propertyContext.get(ALL_CONFERENC_IN_CATEGORY_PAGE));
    }

    public static FindConferencInIdCategoryCommand getInstance() {
        return FindConferencInIdCategoryCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindConferencInIdCategoryCommand INSTANCE =
                new FindConferencInIdCategoryCommand(ServiceFactory.simple().categoryService(),RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
