package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.exception.DaoException;
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
    private ApplicationDaoImpl dao;
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
        dao = mock(ApplicationDaoImpl.class);
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
                new User((long) 1, "daniils3rov@yandex.ru", "daniils3rov@yandex.ru", "Serov231969",
                        "Daniil", "Serov", Role.USER), new SectionResult((long) 1, "Open"),
                new SectionConferenc((long) 1, "ART", "Name", new Conferenc((long) 2, "SD",
                        "Qwerty", new Category((long) 3, "QWE"), new Status((long) 4, "Active")),
                        new Status((long) 1, "Active")));
        applicationList = new ArrayList<>();
        applicationList.add(application);
    }

    @Test
    public void create() throws DaoException {
        boolean expectedResult = true;
        when(dao.create(idUser, idSectionConferenc, idResult)).thenReturn(true);
        boolean actualResult = dao.create(idApplication, idUser, idResult);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void changeStatusApplicationAfterUpdateSectionConferenc()throws DaoException  {
        boolean expectedResult = true;
        when(dao.changeStatusApplicationAfterUpdateSectionConferenc(idSectionConferenc)).thenReturn(true);
        boolean actualResult = dao.changeStatusApplicationAfterUpdateSectionConferenc(idSectionConferenc);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateIdStatusApplication() throws DaoException {
        boolean expectedResult = true;
        when(dao.updateIdStatusApplication(idApplication, idResult)).thenReturn(true);
        boolean actualResult = dao.updateIdStatusApplication(idApplication, idResult);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readAll() throws EntityExtractionFailedException, DaoException {
        List<Application> expectedResult = applicationList;
        when(dao.readAll()).thenReturn(expectedResult);
        List<Application> actualResult = dao.readAll();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readById() throws DaoException {
        Optional<Application> expectedResult = Optional.of(application);
        when(dao.readById((long) 1)).thenReturn(expectedResult);
        Optional<Application> actualResult = dao.readById((long) 1);
        assertEquals(actualResult, expectedResult);
    }


    @Test
    public void findAccountIdByApplication()throws DaoException  {
        List<Application> expectedResult = applicationList;
        when(dao.findAccountIdByApplication(idApplication)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findAccountIdByApplication(idApplication);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByStatusResult() throws DaoException {
        List<Application> expectedResult = applicationList;
        when(dao.findByStatusResult(idResult)).thenReturn(expectedResult);
        List<Application> actualResult = dao.findByStatusResult(idResult);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void delete()throws DaoException  {
        boolean expectedResult = true;
        when(dao.delete(idApplication)).thenReturn(true);
        boolean actualResult = dao.delete(idApplication);
        Assert.assertEquals(actualResult, expectedResult);
    }
}