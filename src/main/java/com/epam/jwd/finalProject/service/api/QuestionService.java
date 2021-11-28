package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Question;

import java.sql.Date;
import java.util.List;

public interface QuestionService extends EntityService{
    boolean create(String name, Date date, Long idUser);

    boolean addAnswer(Long id, String answer);

    boolean updateQuestion(Long id, String question);

    List<Question> findAccountIdByQuestion(Long id);

    List<Question> findByFirstName(String firstName);

    List<Question> findByLastName(String lastName);

    List<Question> findByEmail(String email);

    List<Question> findByLogin(String login);

    List<Question> findByAnswer(String answer);

}
