package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.DaoException;
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
     * Find count all question by user
     *
     * @param id id user
     * @return count questions
     */
    Long findCountAllQuestionByUser(Long id) throws DaoException;

    /**
     * Find count all question
     *
     * @return count questions
     */
    Long findCountAllQuestion() throws DaoException;

    /**
     * Create question
     *
     * @param name   name for new question
     * @param date   date for new question
     * @param idUser id user for new question
     * @return boolean result of operation
     */
    boolean create(String name, Date date, Long idUser) throws DaoException;

    /**
     * Add answer by id question
     *
     * @param id     id question
     * @param answer answer for new question
     * @return boolean result of operation
     */
    boolean addAnswer(Long id, String answer) throws DaoException;

    /**
     * Find for duplicate question
     *
     * @param idAccount id question
     * @param question  question for new question
     * @return boolean result of operation
     */
    boolean findForDuplicateQuestion(Long idAccount, String question) throws DaoException;

    /**
     * Read all questions
     *
     * @param limit number of rows in the selection
     * @param offset offset from the beginning of the selection
     * @return List questions
     */
    List<Question> readAll(Long limit,Long offset) throws EntityExtractionFailedException, DaoException;

    /**
     * Read question by id
     *
     * @param id id question
     * @return Question
     */
    Optional<Question> readById(Long id) throws DaoException;

    /**
     * Find questions by id user
     *
     * @param id id user
     * @param limit number of rows in the selection
     * @param offset offset from the beginning of the selection
     * @return List questions
     */
    List<Question> findAccountIdByQuestion(Long id, Long limit,Long offset) throws DaoException;

    /**
     * Remove question by id
     *
     * @param id id question
     * @return boolean result of operation
     */
    boolean delete(Long id) throws DaoException;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    static QuestionDao instance() {
        return QuestionDaoImpl.getInstance();
    }
}
