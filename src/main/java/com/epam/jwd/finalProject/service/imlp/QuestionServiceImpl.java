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
     * Limit for pagination
     */
    private static final Long LIMIT = (long) 5;

    /**
     * Constructor - creating a new object
     *
     * @param questionDao dao for this service
     */
    public QuestionServiceImpl(QuestionDaoImpl questionDao) {
        this.questionDao = questionDao.getInstance();
    }

    /**
     * Find count all question by user
     *
     * @param id id user
     * @return count questions
     */
    @Override
    public Long findCountAllQuestionByUser(Long id) throws ServiceException, ValidationException {
        try { if (!questionDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
            final Long countConferenc = questionDao.findCountAllQuestionByUser(id);
            Long pageCount = countConferenc / LIMIT;
            if ((countConferenc - pageCount * LIMIT) > 0) {
                pageCount++;
            }
            return pageCount;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find count all question
     *
     * @return count questions
     */
    @Override
    public Long findCountAllQuestion() throws ServiceException {
        try {
            final Long countConferenc = questionDao.findCountAllQuestion();
            Long pageCount = countConferenc / LIMIT;
            if ((countConferenc - pageCount * LIMIT) > 0) {
                pageCount++;
            }
            return pageCount;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find all question
     *
     * @param pageNumber selected page
     * @return List question
     */
    @Override
    public List<Question> findAll(Long pageNumber) throws ServiceException {
            try {
                final Long offset = LIMIT * (pageNumber - 1);
                return questionDao.readAll(LIMIT, offset);
            } catch (EntityExtractionFailedException e) {
                e.printStackTrace();
            }catch (DaoException e) {
                throw new ServiceException(e);
            }
            return Collections.emptyList();
        }


    /**
     * Find for duplicate question
     *
     * @param idAccount id question
     * @param question  question for new question
     * @return boolean result of operation
     */
    @Override
    public boolean findForDuplicateQuestion(Long idAccount, String question)
            throws ValidationException, ServiceException {
        try {
            if (!questionDataValidator.isIdValid(idAccount) || !questionDataValidator.isAnswerValid(question)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return questionDao.findForDuplicateQuestion(idAccount, question);
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
            if (!questionDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
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
            if (!questionDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
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
            if (!questionDataValidator.isIdValid(idUser) || !questionDataValidator.isNameValid(name)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            LocalDate localDate = LocalDate.now();
            Date date = java.sql.Date.valueOf(localDate);
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
            if (!questionDataValidator.isIdValid(id) || !questionDataValidator.isAnswerValid(answer)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
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
    public List<Question> findAccountIdByQuestion(Long id, Long pageNumber)
            throws ValidationException, ServiceException {
        try {
            if (!questionDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            final Long offset = LIMIT * (pageNumber - 1);
            return questionDao.findAccountIdByQuestion(id, LIMIT, offset);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
