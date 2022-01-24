package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;
/**
 * The interface entity service
 *
 * @author Daniil Serov
 */
public interface QuestionService extends EntityService<Question>{

    /**
     * Find count all question by user
     *
     * @param id id user
     * @return count questions
     */
    Long findCountAllQuestionByUser(Long id) throws ServiceException, ValidationException;

    /**
     * Find count all question
     *
     * @return count questions
     */
    Long findCountAllQuestion() throws ServiceException;

    /**
     * Create question
     *
     * @param name name for new conferenc
     * @param idUser id user
     * @return boolean result of operation
     */
    boolean create(String name,  Long idUser) throws ValidationException, ServiceException;

    /**
     * Find for duplicate question
     *
     * @param idAccount id question
     * @param question  question for new question
     * @return boolean result of operation
     */
    boolean findForDuplicateQuestion(Long idAccount, String question)throws ValidationException, ServiceException;

    /**
     * Add answer for question
     *
     * @param id id question
     * @param answer new answer for question
     * @return boolean result of operation
     */
    boolean addAnswer(Long id, String answer) throws ValidationException, ServiceException;

    /**
     * Find questions for user
     *
     * @param id id user
     * @param pageNumber selected page
     * @return boolean result of operation
     */
    List<Question> findAccountIdByQuestion(Long id, Long pageNumber) throws ValidationException, ServiceException;
}
