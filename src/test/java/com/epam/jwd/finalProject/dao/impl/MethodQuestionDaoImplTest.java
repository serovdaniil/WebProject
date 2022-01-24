package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.model.Role;
import com.epam.jwd.finalProject.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MethodQuestionDaoImplTest extends Assert {
    private QuestionDaoImpl dao;
    private String question;
    private String answer;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private Long idUser;
    private Long idQuestion;
    private Question questionObject;
    private List<Question> questionList;
    private Date date;

    @Before
    public void setUp() {
        dao = mock(QuestionDaoImpl.class);
        question = "Do you have hobby?";
        answer = "Yes, I have.";
        firstName="Daniil";
        lastName ="Serov";
        email="daniils3rov@yandex.ru";
        login="daniils3rov@yandex.ru";
        idUser = (long) 1;
        idQuestion = (long) 1;
        questionObject = new Question((long) 1, "Do you have hobby?", "Yes, I have.",
                new java.sql.Date(524524L), new User((long) 1, "daniils3rov@yandex.ru",
                "daniils3rov@yandex.ru", "Serov231969", "Daniil", "Serov", Role.USER));
        questionList = new ArrayList<>();
        questionList.add(questionObject);
        date=new java.sql.Date(54252453);
    }

    @Test
    public void create() throws DaoException {
        boolean expectedResult = true;
        when(dao.create(question,date,idUser)).thenReturn(true);
        boolean actualResult = dao.create(question,date,idUser);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void addAnswer() throws DaoException {
        boolean expectedResult = true;
        when(dao.addAnswer(idUser,answer)).thenReturn(true);
        boolean actualResult = dao.addAnswer(idUser,answer);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readAll() throws EntityExtractionFailedException, DaoException  {
        List<Question> expectedResult = questionList;
        when(dao.readAll((long)1,(long)1)).thenReturn(expectedResult);
        List<Question> actualResult = dao.readAll((long)1,(long)1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readById()throws DaoException  {
        Optional<Question> expectedResult= Optional.of(questionObject);
        when(dao.readById((long)1)).thenReturn(expectedResult);
        Optional<Question> actualResult = dao.readById((long)1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findAccountIdByQuestion() throws DaoException {
        List<Question> expectedResult = questionList;
        when(dao.findAccountIdByQuestion(idUser,(long)1,(long)1)).thenReturn(expectedResult);
        List<Question> actualResult = dao.findAccountIdByQuestion(idUser,(long)1,(long)1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void delete()throws DaoException  {
        boolean expectedResult = true;
        when(dao.delete(idQuestion)).thenReturn(true);
        boolean actualResult = dao.delete(idQuestion);
        Assert.assertEquals(actualResult, expectedResult);
    }
}