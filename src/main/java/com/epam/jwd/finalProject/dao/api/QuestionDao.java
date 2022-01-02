package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.QuestionDaoImpl;
import com.epam.jwd.finalProject.model.Question;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The interface question dao
 *
 * @author Daniil Serov
 */
public interface QuestionDao {

    /**
     * Create question
     *
     * @param name   name for new question
     * @param date   date for new question
     * @param idUser id user for new question
     * @return boolean result of operation
     */
    boolean create(String name, Date date, Long idUser);

    /**
     * Add answer by id question
     *
     * @param id     id question
     * @param answer answer for new question
     * @return boolean result of operation
     */
    boolean addAnswer(Long id, String answer);

    /**
     * Read all questions
     *
     * @return List questions
     */
    List<Question> readAll() throws EntityExtractionFailedException;

    /**
     * Read question by id
     *
     * @param id id question
     * @return Question
     */
    Optional<Question> readById(Long id);

    /**
     * Find questions by id user
     *
     * @param id id user
     * @return List questions
     */
    List<Question> findAccountIdByQuestion(Long id);

    /**
     * Remove question by id
     *
     * @param id id question
     * @return boolean result of operation
     */
    boolean delete(Long id);

    /**
     * Gets instance.
     *
     * @return the instance
     */
    static QuestionDao instance() {
        return QuestionDaoImpl.getInstance();
    }
}
