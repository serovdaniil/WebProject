package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService extends EntityService{
    boolean create(Question question);

    boolean addAnswer(Long id, String answer);

    boolean updateQuestion(Long id, String question);

    List<Question> findAccountIdByQuestion(Long id);

    List<Question> findByFirstName(String firstName);

    List<Question> findByLastName(String lastName);

    List<Question> findByEmail(String email);

    List<Question> findByLogin(String login);

    List<Question> findByAnswer(String answer);

}
