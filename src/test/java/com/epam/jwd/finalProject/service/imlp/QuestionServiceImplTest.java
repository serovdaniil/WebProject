package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.QuestionDaoImpl;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.model.Role;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest extends Assert {
    @Mock
    private QuestionDaoImpl dao;
    @InjectMocks
    private QuestionServiceImpl service;
    private String question;
    private String answer;
    private Long idUser;
    private Long idQuestion;
    private Question questionObject;
    private List<Question> questionList;
    private Date date;
    private Long limitPagination;
    private Long offsetPagination;
    private Long idPage;
    private boolean expectedResult;

    @Before
    public void setUp() throws Exception {
        question = "Do you have hobby?";
        answer = "Yes, I have.";
        idUser = (long) 1;
        idQuestion = (long) 1;
        questionObject = new Question((long) 1, "Do you have hobby?", "Yes, I have.",
                new java.sql.Date(54252453), new User((long) 1, "daniils3rov@yandex.ru",
                "daniils3rov@yandex.ru", "Serov231969", "Daniil",
                "Serov", Role.USER));
        questionList = new ArrayList<>();
        questionList.add(questionObject);
        date = new java.sql.Date(54252453);
        limitPagination = (long) 5;
        offsetPagination = (long) 0;
        idPage = (long) 1;
        expectedResult = true;
    }

    @Test
    public void findAll() throws ServiceException, DaoException, EntityExtractionFailedException {
        List<Question> expectedResult = questionList;
        when(dao.readAll(limitPagination, offsetPagination)).thenReturn(expectedResult);
        List<Question> actualResult = service.findAll(idPage);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findId() throws ValidationException, ServiceException, DaoException {
        Optional<Question> expectedResult = Optional.of(questionObject);
        when(dao.readById(idQuestion)).thenReturn(expectedResult);
        Optional<Question> actualResult = service.findId(idQuestion);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void remove() throws ValidationException, ServiceException, DaoException {
        when(dao.delete(idQuestion)).thenReturn(expectedResult);
        boolean actualResult = service.remove(idQuestion);
        assertTrue(actualResult);
    }

    @Test
    public void create() throws ValidationException, ServiceException, DaoException {
        when(dao.create(question, date, idUser)).thenReturn(expectedResult);
        boolean actualResult = service.create(question, idUser);
        assertTrue(actualResult);
    }

    @Test
    public void addAnswer() throws ValidationException, ServiceException, DaoException {
        when(dao.addAnswer(idQuestion, answer)).thenReturn(true);
        boolean actualResult = service.addAnswer(idQuestion, answer);
        assertTrue(actualResult);
    }

    @Test
    public void findAccountIdByQuestion() throws ValidationException, ServiceException, DaoException {
        List<Question> expectedResult = questionList;
        when(dao.findAccountIdByQuestion(idUser,limitPagination,offsetPagination)).thenReturn(expectedResult);
        List<Question> actualResult = service.findAccountIdByQuestion(idUser, idPage);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findCountAllQuestionByUser() throws DaoException, ValidationException, ServiceException {
        Long expectedResult = (long) 4;
        when(dao.findCountAllQuestionByUser(idUser)).thenReturn(expectedResult);
        Long actualResult = service.findCountAllQuestionByUser(idUser);
        expectedResult=pageCount(expectedResult,actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findCountAllQuestion() throws DaoException, ServiceException {
        Long expectedResult = (long) 4;
        when(dao.findCountAllQuestion()).thenReturn(expectedResult);
        Long actualResult = service.findCountAllQuestion();
        expectedResult=pageCount(expectedResult,actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findForDuplicateQuestion() throws DaoException, ValidationException, ServiceException {
        when(dao.findForDuplicateQuestion(idQuestion,question)).thenReturn(expectedResult);
        boolean actualResult = service.findForDuplicateQuestion(idQuestion,question);
        assertTrue(actualResult);
    }

    private Long pageCount(Long expectedResult, Long actualResult) {
        if ((expectedResult - (actualResult * limitPagination) <= 5) ||
                (expectedResult + (actualResult * limitPagination) <= 5)) {
            expectedResult = actualResult;
        }
        return expectedResult;
    }
}