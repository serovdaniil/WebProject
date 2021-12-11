package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MethodApplicationDaoImplTest extends Assert {
    private MethodApplicationDaoImpl dao;
    private Long idApplication;
    private Long idUser;
    private Long idResult;
    private Long idSectionConferenc;
    private Long idConferenc;
    private Long idCategory;
    private String category;
    private String sectionConferenc;
    private String conferenc;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private Application application;
    private List<Application> applicationList;

    @Before
    public void setUp() {
        dao = mock(MethodApplicationDaoImpl.class);
        firstName = "Daniil";
        lastName = "Serov";
        email = "daniils3rov@yandex.ru";
        login = "daniils3rov@yandex.ru";
        sectionConferenc = "Number";
        conferenc = "Java";
        category = "IT";
        idCategory = (long) 6;
        idSectionConferenc = (long) 4;
        idApplication = (long) 1;
        idUser = (long) 1;
        idResult = (long) 1;
        idConferenc = (long) 5;
        application = new Application((long) 1,
                new User((long) 1, "daniils3rov@yandex.ru", "daniils3rov@yandex.ru", "Serov231969", "Daniil", "Serov", Role.USER),
                new SectionResult((long) 1, "Open"), new SectionConferenc((long) 1, "ART", "Name",
                new Conferenc((long) 2, "SD", "Qwerty", new Category((long) 3, "QWE"))));
        applicationList = new ArrayList<>();
        applicationList.add(application);
    }

    @Test
    public void create() {
        boolean expectedResult = true;
        when(dao.create(idUser, idSectionConferenc, idResult)).thenReturn(true);
        boolean actualResult = dao.create(idApplication, idUser, idResult);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateIdStatusApplication() {
        boolean expectedResult = true;
        when(dao.updateIdStatusApplication(idApplication, idResult)).thenReturn(true);
        boolean actualResult = dao.updateIdStatusApplication(idApplication, idResult);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readAll() throws EntityExtractionFailedException {
        List<Application> expectedResult = applicationList;
        when(dao.readAll()).thenReturn(expectedResult);
        List<Application> actualResult = dao.readAll();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readById() {
        Optional<Application> expectedResult = Optional.of(application);
        when(dao.readById((long) 1)).thenReturn(expectedResult);
        Optional<Application> actualResult = dao.readById((long) 1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findBySectionConferencId() {
        List<Application> expectedResult = applicationList;
        when(dao.findBySectionConferencId(idSectionConferenc)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findBySectionConferencId(idSectionConferenc);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findBySectionConferencName() {
        List<Application> expectedResult = applicationList;
        when(dao.findBySectionConferencName(sectionConferenc)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findBySectionConferencName(sectionConferenc);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findAccountIdByApplication() {
        List<Application> expectedResult = applicationList;
        when(dao.findAccountIdByApplication(idApplication)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findAccountIdByApplication(idApplication);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByFirstName() {
        List<Application> expectedResult = applicationList;
        when(dao.findByFirstName(firstName)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findByFirstName(firstName);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByLastName() {
        List<Application> expectedResult = applicationList;
        when(dao.findByLastName(lastName)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findByLastName(lastName);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByEmail() {
        List<Application> expectedResult = applicationList;
        when(dao.findByEmail(email)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findByEmail(email);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByLogin() {
        List<Application> expectedResult = applicationList;
        when(dao.findByLogin(login)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findByLogin(login);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByStatusResult() {
        List<Application> expectedResult = applicationList;
        when(dao.findByStatusResult(idResult)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findByStatusResult(idResult);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByConferencId() {
        List<Application> expectedResult = applicationList;
        when(dao.findByConferencId(idConferenc)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findByConferencId(idConferenc);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByConferencName() {
        List<Application> expectedResult = applicationList;
        when(dao.findByConferencName(conferenc)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findByConferencName(conferenc);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByCategoryId() {
        List<Application> expectedResult = applicationList;
        when(dao.findByCategoryId(idCategory)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findByCategoryId(idCategory);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByCategoryName() {
        List<Application> expectedResult = applicationList;
        when(dao.findByCategoryName(category)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findByCategoryName(category);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void delete() {
        boolean expectedResult = true;
        when(dao.delete(idApplication)).thenReturn(true);
        boolean actualResult = dao.delete(idApplication);
        Assert.assertEquals(actualResult, expectedResult);
    }
}