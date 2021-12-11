package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.model.Role;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class QuestionServiceImplTest extends Assert {
    private QuestionServiceImpl service;
    private String question;
    private String answer;
    private Long idUser;
    private Long idQuestion;
    private Question questionObject;
    private List<Question> questionList;
    private Date date;

    @Before
    public void setUp() throws Exception {
        service = mock(QuestionServiceImpl.class);
        question = "Do you have hobby?";
        answer = "Yes, I have.";
        idUser = (long) 1;
        idQuestion = (long) 1;
        questionObject = new Question((long) 1, "Do you have hobby?", "Yes, I have.", new java.sql.Date(524524L), new User((long) 1, "daniils3rov@yandex.ru", "daniils3rov@yandex.ru", "Serov231969", "Daniil", "Serov", Role.USER));
        questionList = new ArrayList<>();
        questionList.add(questionObject);
        date = new java.sql.Date(54252453);
    }

    @Test
    public void findAll() {
        List<Question> expectedResult = questionList;
        when(service.findAll()).thenReturn(expectedResult);
        List<Question> actualResult = service.findAll();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findId() throws ValidationException {
        Optional<Question> expectedResult = Optional.of(questionObject);
        when(service.findId(idQuestion)).thenReturn(expectedResult);
        Optional<Question> actualResult = service.findId((long) 1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void remove() throws ValidationException {
        boolean expectedResult = true;
        when(service.remove(idQuestion)).thenReturn(true);
        boolean actualResult = service.remove(idQuestion);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void create() throws ValidationException {
        boolean expectedResult = true;
        when(service.create(question, idUser)).thenReturn(true);
        boolean actualResult = service.create(question, idUser);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void addAnswer() throws ValidationException {
        boolean expectedResult = true;
        when(service.addAnswer(idQuestion, answer)).thenReturn(true);
        boolean actualResult = service.addAnswer(idQuestion, answer);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findAccountIdByQuestion() throws ValidationException {
        List<Question> expectedResult = questionList;
        when(service.findAccountIdByQuestion(idUser)).thenReturn(expectedResult);
        List<Question> actualResult = service.findAccountIdByQuestion(idUser);
        assertEquals(actualResult, expectedResult);
    }
}