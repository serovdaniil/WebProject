package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.QuestionDaoImpl;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.service.api.QuestionService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.validator.QuestionDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Daniil Serov
 * @see QuestionDaoImpl
 */
public class QuestionServiceImpl implements QuestionService {
    /**
     * Validator for this service
     */
    private final QuestionDataValidator questionDataValidator = new QuestionDataValidator().getInstance();
    /**
     * Dao for this service
     */
    private final QuestionDaoImpl questionDao;
    /**
     * Logger for this service
     */
    private static final Logger LOG = LogManager.getLogger(QuestionServiceImpl.class);

    /**
     * Constructor - creating a new object
     *
     * @param questionDao dao for this service
     */
    public QuestionServiceImpl(QuestionDaoImpl questionDao) {
        this.questionDao = questionDao.getInstance();
    }

    /**
     * Find all question
     *
     * @return List question
     */
    @Override
    public List<Question> findAll() throws ServiceException {
        try {
            LOG.debug("Service: Reading all questions started.");
            try {
                return questionDao.readAll();
            } catch (EntityExtractionFailedException e) {
                e.printStackTrace();
            }
            LOG.debug("Service: Reading all questions finished.");
            return Collections.emptyList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find question by id
     *
     * @param id id question
     * @return List question
     * @throws ValidationException if there are validation problems
     */
    @Override
    public Optional<Question> findId(Long id) throws ValidationException, ServiceException {
        try {
            LOG.debug("Service: Finding question by id started.");
            if (!questionDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            LOG.debug("Service: Finding question by id finished.");
            return questionDao.readById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Remove question by id
     *
     * @param id id question
     * @return Boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean remove(Long id) throws ValidationException, ServiceException {
        try {
            LOG.debug("Service: Removing question by id started.");
            if (!questionDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            LOG.debug("Service: Removing question by id finished.");
            return questionDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Create question by id
     *
     * @param name   name question
     * @param idUser user id
     * @return Boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean create(String name, Long idUser) throws ValidationException, ServiceException {
        try {
            LOG.debug("Service: Creating question started.");
            if (!questionDataValidator.isIdValid(idUser) || !questionDataValidator.isNameValid(name)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            LocalDate localDate = LocalDate.now();
            Date date = java.sql.Date.valueOf(localDate);
            LOG.debug("Service: Creating questionc finished.");
            return questionDao.create(name, date, idUser);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Add answer question by id
     *
     * @param answer answer for question
     * @param id     question id
     * @return Boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean addAnswer(Long id, String answer) throws ValidationException, ServiceException {
        try {
            LOG.debug("Service: Add answer started.");
            if (!questionDataValidator.isIdValid(id) || !questionDataValidator.isAnswerValid(answer)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            LOG.debug("Service: Add answer finished.");
            return questionDao.addAnswer(id, answer);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find questions for account id
     *
     * @param id user id
     * @return List questions
     * @throws ValidationException if there are validation problems
     */
    @Override
    public List<Question> findAccountIdByQuestion(Long id) throws ValidationException, ServiceException {
        try {
            LOG.debug("Service: Finding questions by id user started.");
            if (!questionDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            LOG.debug("Service: Finding questions by id user finished.");
            return questionDao.findAccountIdByQuestion(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
