package com.epam.jwd.finalProject.command.common.function.question;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.QuestionService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * This command is for creating a question
 *
 * @author Daniil Serov
 */
public class CreateQuestionCommand implements Command {
    private static final String FIND_PARAM_NAME = "name";
    private static final String PAGES_ATTRIBUTE_NAME = "maxPagesCount";
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String QUESTION_ATTRIBUTE_NAME_RESULT = "result";
    private static final String QUESTION_ATTRIBUTE_NAME = "questions";
    private static final String URL_FIND_QUESTIONS_BY_ID_ACCOUNT_PAGE = "controller?" +
            "command=find_questions_by_id_account&page=1";
    private static final String FIND_QUESTIONS_BY_ID_ACCOUNT_PAGE = "page.FINDQUESTIONSBYIDACCOUNT";
    private static final String ERROR_DUPLICATE_PASS_ATTRIBUTE = "errorDuplicatePassMessage";
    private static final String ERROR_DUPLICATE_PASS_MESSAGE = "You have already asked such a question, " +
            "you can check the answer to it in your personal account!";

    private static final Logger LOG = LogManager.getLogger(CreateQuestionCommand.class);

    private final QuestionService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    CreateQuestionCommand() {
        this.service = ServiceFactory.simple().questionService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Optional<User> userOptional = request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME);
        Long idAccount = (long) 0;
        if (userOptional.isPresent()) {
            idAccount = userOptional.get().getId();
        }
        final Long pageNumber = (long) 1;
        final String name = request.getParameter(FIND_PARAM_NAME);
        boolean result;
        final List<Question> questionList;
        try {
            result = service.findForDuplicateQuestion(idAccount, name);
            final Long count = service.findCountAllQuestionByUser(idAccount);
            if (result) {
                request.addAttributeToJsp(ERROR_DUPLICATE_PASS_ATTRIBUTE, ERROR_DUPLICATE_PASS_MESSAGE);
                questionList = service.findAccountIdByQuestion(idAccount, pageNumber);
                request.addAttributeToJsp(QUESTION_ATTRIBUTE_NAME, questionList);
                request.addAttributeToJsp(PAGES_ATTRIBUTE_NAME, count);
                return requestFactory.createForwardResponse(propertyContext.get(FIND_QUESTIONS_BY_ID_ACCOUNT_PAGE));
            }
            result = service.create(name, idAccount);
            questionList = service.findAccountIdByQuestion(idAccount, pageNumber);
            request.addAttributeToJsp(QUESTION_ATTRIBUTE_NAME, questionList);
            request.addAttributeToJsp(QUESTION_ATTRIBUTE_NAME_RESULT, result);
            request.addAttributeToJsp(PAGES_ATTRIBUTE_NAME, count);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createRedirectResponse(URL_FIND_QUESTIONS_BY_ID_ACCOUNT_PAGE);
    }

    public static CreateQuestionCommand getInstance() {
        return CreateQuestionCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CreateQuestionCommand INSTANCE =
                new CreateQuestionCommand();
    }
}
