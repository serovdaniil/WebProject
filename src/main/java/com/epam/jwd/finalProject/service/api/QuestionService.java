package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;

public interface QuestionService extends EntityService{
    boolean create(String name,  Long idUser) throws ValidationException;

    boolean addAnswer(Long id, String answer) throws ValidationException;

    List<Question> findAccountIdByQuestion(Long id) throws ValidationException;
}
