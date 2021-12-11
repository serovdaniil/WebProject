package com.epam.jwd.finalProject.command.admin.page.question;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;

public class ShowQuestionPageCommand implements Command {
    private static final String QUESTIONS_ATTRIBUTE_NAME = "questions";
    private static final String QUESTION_PAGE = "page.questions";

    private final EntityService<Question> service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowQuestionPageCommand(EntityService<Question> service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().serviceFor(Question.class);
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<Question> questionList = service.findAll();
        request.addAttributeToJsp(QUESTIONS_ATTRIBUTE_NAME, questionList);
        return requestFactory.createForwardResponse(propertyContext.get(QUESTION_PAGE));
    }

    public static ShowQuestionPageCommand getInstance() {
        return ShowQuestionPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowQuestionPageCommand INSTANCE =
                new ShowQuestionPageCommand(ServiceFactory.simple().serviceFor(Question.class), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
