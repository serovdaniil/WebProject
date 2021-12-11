package com.epam.jwd.finalProject.command.common.function.question;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.QuestionService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CreateQuestionCommand implements Command {
    private final QuestionService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;
    private static final String FIND_PARAM_NAME = "name";
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String QUESTION_ATTRIBUTE_NAME_RESULT = "result";
    private static final String QUESTION_ATTRIBUTE_NAME = "questions";
    private static final String FIND_QUESTIONS_BY_ID_ACCOUNT_PAGE = "controller?command=find_questions_by_id_account";
    private static final Logger LOG = LogManager.getLogger(CreateQuestionCommand.class);

    CreateQuestionCommand(QuestionService service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().questionService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Optional<User> userOptional = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        final Long idAccount = userOptional.get().getId();
        final String name = request.getParameter(FIND_PARAM_NAME);
        final boolean result;
        final List<Question> questionList;
        try {
            result = service.create(name,idAccount);
            questionList = service.findAccountIdByQuestion(idAccount);
            request.addAttributeToJsp(QUESTION_ATTRIBUTE_NAME, questionList);
            request.addAttributeToJsp(QUESTION_ATTRIBUTE_NAME_RESULT, result);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }

        return requestFactory.createRedirectResponse(FIND_QUESTIONS_BY_ID_ACCOUNT_PAGE);
    }

    public static CreateQuestionCommand getInstance() {
        return CreateQuestionCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreateQuestionCommand INSTANCE =
                new CreateQuestionCommand(ServiceFactory.simple().questionService(), RequestFactory.getInstance(),
                        PropertyContext.instance());
    }
}
