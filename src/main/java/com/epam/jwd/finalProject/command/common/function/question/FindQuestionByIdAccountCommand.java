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
 * This command is for displaying questions of a specific user
 *
 * @author Daniil Serov
 */
public class FindQuestionByIdAccountCommand implements Command {
    private static final String FIND_PARAM_PAGE = "page";
    private static final String PAGES_ATTRIBUTE_NAME = "maxPagesCount";
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String QUESTION_ATTRIBUTE_NAME = "questions";
    private static final String FIND_QUESTIONS_BY_ID_ACCOUNT_PAGE = "page.findQuestionsByIdAccount";

    private static final Logger LOG = LogManager.getLogger(FindQuestionByIdAccountCommand.class);

    private final QuestionService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    FindQuestionByIdAccountCommand() {
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
        final List<Question> questionList;
        try {
            final Long pageNum = Long.valueOf(request.getParameter(FIND_PARAM_PAGE));
            final Long count = service.findCountAllQuestionByUser(idAccount);
            questionList = service.findAccountIdByQuestion(idAccount, pageNum);
            request.addAttributeToJsp(QUESTION_ATTRIBUTE_NAME, questionList);
            request.addAttributeToJsp(PAGES_ATTRIBUTE_NAME, count);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }

        return requestFactory.createForwardResponse(propertyContext.get(FIND_QUESTIONS_BY_ID_ACCOUNT_PAGE));
    }

    public static FindQuestionByIdAccountCommand getInstance() {
        return FindQuestionByIdAccountCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final FindQuestionByIdAccountCommand INSTANCE =
                new FindQuestionByIdAccountCommand();
    }
}
