package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodQuestionDaoImpl;
import com.epam.jwd.finalProject.model.Question;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface QuestionDao {
    boolean create(String name, Date date, Long idUser);

    boolean addAnswer(Long id, String answer);

    boolean updateQuestion(Long id, String question);

    List<Question> readAll() throws EntityExtractionFailedException;

    Optional<Question> readById(Long id);

    List<Question> findAccountIdByQuestion(Long id);

    List<Question> findByFirstName(String firstName);

    List<Question> findByLastName(String lastName);

    List<Question> findByEmail(String email);

    List<Question> findByLogin(String login);

    List<Question> findByAnswer(String answer);

    boolean delete(Long id);

    static QuestionDao instance() {
        return MethodQuestionDaoImpl.getInstance();
    }
}
