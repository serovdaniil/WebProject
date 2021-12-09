package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodQuestionDaoImpl;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.service.api.QuestionService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.validator.QuestionDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class QuestionServiceImpl implements QuestionService {
    private final QuestionDataValidator questionDataValidator = new QuestionDataValidator().getInstance();
    private final MethodQuestionDaoImpl questionDao;
    private static final Logger LOG = LogManager.getLogger(QuestionServiceImpl.class);
    public QuestionServiceImpl(MethodQuestionDaoImpl questionDao) {
        this.questionDao = questionDao.getInstance();
    }

    @Override
    public List findAll() {
        LOG.debug("Service: Reading all questions started.");
        try {
            return questionDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }
        LOG.debug("Service: Reading all questions finished.");
        return Collections.emptyList();
    }

    @Override
    public Optional<Question> findId(Long id) throws ValidationException {
        LOG.debug("Service: Finding question by id started.");
        if (!questionDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Finding question by id finished.");
        return questionDao.readById(id);
    }

    @Override
    public boolean remove(Long id) throws ValidationException {
        LOG.debug("Service: Removing question by id started.");
        if (!questionDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Removing question by id finished.");
        return questionDao.delete(id);
    }

    @Override
    public boolean create(String name, Long idUser) throws ValidationException {
        LOG.debug("Service: Creating question started.");
        if (!questionDataValidator.isIdValid(idUser)||!questionDataValidator.isNameValid(name)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LocalDate localDate = LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);
        LOG.debug("Service: Creating questionc finished.");
        return questionDao.create(name, date, idUser);
    }

    @Override
    public boolean addAnswer(Long id, String answer) throws ValidationException {
        LOG.debug("Service: Add answer started.");
        if (!questionDataValidator.isIdValid(id)||!questionDataValidator.isAnswerValid(answer)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Add answer finished.");
        return questionDao.addAnswer(id, answer);
    }

    @Override
    public List<Question> findAccountIdByQuestion(Long id) throws ValidationException {
        LOG.debug("Service: Finding questions by id user started.");
        if (!questionDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Finding questions by id user finished.");
        return questionDao.findAccountIdByQuestion(id);
    }
}
