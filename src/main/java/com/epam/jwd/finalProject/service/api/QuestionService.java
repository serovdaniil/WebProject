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
     * Create question
     *
     * @param name name for new conferenc
     * @param idUser id user
     * @return boolean result of operation
     */
    boolean create(String name,  Long idUser) throws ValidationException, ServiceException;

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
     * @return boolean result of operation
     */
    List<Question> findAccountIdByQuestion(Long id) throws ValidationException, ServiceException;
}
