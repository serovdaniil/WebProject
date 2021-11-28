package com.epam.jwd.finalProject.command.admin.question;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.api.QuestionService;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;

import java.util.List;

public class AddAnswerToQuestionCommand implements Command {
    private static final String PARAM_ID = "id";
    private static final String PARAM_ANSWER = "answer";
    private static final String APPLICATIONS_ATTRIBUTE_NAME_RESULT_ADD = "result";
    private static final String QUESTIONS_ATTRIBUTE_NAME = "questions";
    private static final String QUESTION_PAGE = "page.questions";

    private final QuestionService questionService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    AddAnswerToQuestionCommand(EntityService<Question> questionServiceservice, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.questionService = ServiceFactory.simple().questionService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id =Long.parseLong(request.getParameter(PARAM_ID));
        final String answer =request.getParameter(PARAM_ANSWER);
        final boolean resultAdd = questionService.addAnswer(id,answer);
        final List<Question> questionList = questionService.findAll();
        String result;
        if (!resultAdd) {
            result = "Unsuccessful create";
        } else {
            result = "Successful create";
        }
        request.addAttributeToJsp(QUESTIONS_ATTRIBUTE_NAME, questionList);
        request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME_RESULT_ADD, result);
        return requestFactory.createForwardResponse(propertyContext.get(QUESTION_PAGE));
    }

    public static AddAnswerToQuestionCommand getInstance() {
        return AddAnswerToQuestionCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final AddAnswerToQuestionCommand INSTANCE =
                new AddAnswerToQuestionCommand(ServiceFactory.simple().serviceFor(Question.class), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
