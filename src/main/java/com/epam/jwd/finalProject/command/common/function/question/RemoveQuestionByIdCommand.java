package com.epam.jwd.finalProject.command.common.function.question;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.PropertyContext;
import com.epam.jwd.finalProject.controller.RequestFactory;
import com.epam.jwd.finalProject.service.api.QuestionService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This command is for deleting a question
 *
 * @author Daniil Serov
 */
public class RemoveQuestionByIdCommand implements Command {
    private static final String QUESTION_ATTRIBUTE_NAME = "result";
    private static final String PARAM_NAME = "id";
    private static final String FIND_QUESTIONS = "controller?command=find_questions_by_id_account&page=1";

    private static final Logger LOG = LogManager.getLogger(RemoveQuestionByIdCommand.class);

    private final QuestionService service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    RemoveQuestionByIdCommand(QuestionService service, RequestFactory requestFactory,
                              PropertyContext propertyContext) {
        this.service = ServiceFactory.simple().questionService();
        this.requestFactory = RequestFactory.getInstance();
        this.propertyContext = PropertyContext.instance();
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final Long id = Long.parseLong(request.getParameter(PARAM_NAME));
        final boolean resultRemove;
        try {
            resultRemove = service.remove(id);
            String result;
            if (!resultRemove) {
                result = "Unsuccessful remove";
            } else {
                result = "Successful remove";
            }
            request.addAttributeToJsp(QUESTION_ATTRIBUTE_NAME, result);
        } catch (ValidationException e) {
            LOG.error("The entered data is not correct!" + e);
        }catch (ServiceException e) {
            LOG.error("The service exception!" + e);
        }
        return requestFactory.createRedirectResponse(FIND_QUESTIONS);
    }

    public static RemoveQuestionByIdCommand getInstance() {
        return RemoveQuestionByIdCommand.Holder.INSTANCE;
    }

    private static class Holder {
        public static final RemoveQuestionByIdCommand INSTANCE =
                new RemoveQuestionByIdCommand(ServiceFactory.simple().questionService(),
                        RequestFactory.getInstance(), PropertyContext.instance());
    }
}
