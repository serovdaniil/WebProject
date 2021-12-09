package com.epam.jwd.finalProject.command.common.question;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.service.api.QuestionService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class FindQuestionByIdCommand implements Command {
    private final QuestionService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String QUESTION_ATTRIBUTE_NAME = "question";
    private static final String PARAM_NAME = "id";
    private static final String FIND_QUESTIONS_BY_ID = "page.findQuestionById";
    private static final Logger LOG = LogManager.getLogger(FindQuestionByIdCommand.class);

    FindQuestionByIdCommand(QuestionService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().questionService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_NAME));
        final Optional<Question> questionById;
        final Question question;
        try {
            questionById = service.findId(id);
            question=questionById.get();
            request.addAttributeToJsp(QUESTION_ATTRIBUTE_NAME, question);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(FIND_QUESTIONS_BY_ID));
    }

    public static FindQuestionByIdCommand getInstance() {
        return FindQuestionByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindQuestionByIdCommand INSTANCE =
                new FindQuestionByIdCommand(ServiceFactory.simple().questionService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}