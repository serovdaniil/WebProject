package com.epam.jwd.finalProject.command.admin.function.question;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.api.QuestionService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This command is for adding an answer to a question
 *
 * @author Daniil Serov
 */
public class AddAnswerToQuestionCommand implements Command {
    private static final String PARAM_ID = "id";
    private static final String PARAM_ANSWER = "answer";
    private static final String APPLICATIONS_ATTRIBUTE_NAME_RESULT_ADD = "result";
    private static final String QUESTIONS_ATTRIBUTE_NAME = "question";
    private static final String PAGES_ATTRIBUTE_NAME = "maxPagesCount";
    private static final String URL_QUESTION_PAGE = "/controller?command=show_questions&page=1";

    private static final Logger LOG = LogManager.getLogger(AddAnswerToQuestionCommand.class);


    private final QuestionService questionService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    AddAnswerToQuestionCommand(EntityService<Question> questionServiceservice, RequestFactory requestFactory,
                               PropertyContext propertyContext) {
        this.questionService = ServiceFactory.simple().questionService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_ID));
        final String answer = request.getParameter(PARAM_ANSWER);
        final Long pageNumber=(long)1;
        final boolean resultAdd;
        try {
            final Long count = questionService.findCountAllQuestion();
            resultAdd = questionService.addAnswer(id, answer);
            final List<Question> questionList = questionService.findAll(pageNumber);
            String result;
            if (!resultAdd) {
                result = "Unsuccessful create";
            } else {
                result = "Successful create";
            }
            request.addAttributeToJsp(QUESTIONS_ATTRIBUTE_NAME, questionList);
            request.addAttributeToJsp(PAGES_ATTRIBUTE_NAME, count);
            request.addAttributeToJsp(APPLICATIONS_ATTRIBUTE_NAME_RESULT_ADD, result);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }

        return requestFactory.createRedirectResponse(URL_QUESTION_PAGE);
    }

    public static AddAnswerToQuestionCommand getInstance() {
        return AddAnswerToQuestionCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final AddAnswerToQuestionCommand INSTANCE =
                new AddAnswerToQuestionCommand(ServiceFactory.simple().serviceFor(Question.class),
                        RequestFactory.getInstance(), PropertyContext.instance());
    }
}
