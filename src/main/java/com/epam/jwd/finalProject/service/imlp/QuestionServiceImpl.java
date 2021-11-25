package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodQuestionDaoImpl;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.service.api.QuestionService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class QuestionServiceImpl implements QuestionService {
    private final MethodQuestionDaoImpl questionDao;

    public QuestionServiceImpl(MethodQuestionDaoImpl questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List findAll() {
        try {
            return questionDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }return Collections.emptyList();
    }

    @Override
    public Optional findId(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public boolean create(Question question) {
        return false;
    }

    @Override
    public boolean addAnswer(Long id, String answer) {
        return false;
    }

    @Override
    public boolean updateQuestion(Long id, String question) {
        return false;
    }

    @Override
    public List<Question> findAccountIdByQuestion(Long id) {
        return null;
    }

    @Override
    public List<Question> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Question> findByLastName(String lastName) {
        return null;
    }

    @Override
    public List<Question> findByEmail(String email) {
        return null;
    }

    @Override
    public List<Question> findByLogin(String login) {
        return null;
    }

    @Override
    public List<Question> findByAnswer(String answer) {
        return null;
    }
}
