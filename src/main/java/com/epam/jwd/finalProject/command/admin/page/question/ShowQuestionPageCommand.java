package com.epam.jwd.finalProject.command.admin.page.question;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.service.api.QuestionService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This command displays all user questions
 *
 * @author Daniil Serov
 */
public class ShowQuestionPageCommand implements Command {
    private static final String FIND_PARAM_PAGE = "page";
    private static final String PAGES_ATTRIBUTE_NAME = "maxPagesCount";
    private static final String QUESTIONS_ATTRIBUTE_NAME = "questions";
    private static final String QUESTION_PAGE = "page.questions";

    private static final Logger LOG = LogManager.getLogger(ShowQuestionPageCommand.class);

    private final QuestionService questionService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowQuestionPageCommand() {
        this.questionService = ServiceFactory.simple().questionService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        try {
            final Long pageNum = Long.valueOf(request.getParameter(FIND_PARAM_PAGE));
            final Long count = questionService.findCountAllQuestion();
            final List<Question> questionList = questionService.findAll(pageNum);
            request.addAttributeToJsp(QUESTIONS_ATTRIBUTE_NAME, questionList);
            request.addAttributeToJsp(PAGES_ATTRIBUTE_NAME, count);
        } catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createForwardResponse(propertyContext.get(QUESTION_PAGE));
    }

    public static ShowQuestionPageCommand getInstance() {
        return ShowQuestionPageCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ShowQuestionPageCommand INSTANCE =
                new ShowQuestionPageCommand();
    }
}
