package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.Role;
import com.epam.jwd.finalProject.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MethodUserDaoImplTest extends Assert {
    private MethodUserDaoImpl dao;
    private User user;
    private String firstName;
    private String lastName;
    private Long id;
    private String login;
    private String email;
    private String password;
    private List<User> users;

    @Before
    public void setUp(){
        dao = mock(MethodUserDaoImpl.class);
        user = new User((long) 1, "daniils3rov@yandex.ru", "daniils3rov@yandex.ru",
                "Serov231969", "Daniil", "Serov", Role.USER);
        firstName = "Daniil";
        lastName = "Serov";
        id = (long) 1;
        login = "serov321414@gmail.com";
        email = "serov321414@gmail.com";
        password = "Serov231969";
        users=new ArrayList<>();
        users.add(user);
    }

    @Test
    public void create() {
        Optional<User> expectedResult =Optional.of(user);
        when(dao.create(email, password)).thenReturn(expectedResult);
        Optional<User> actualResult = dao.create(email, password);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readById() {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.readById((long) 1)).thenReturn(expectedResult);
        Optional<User> actualResult = dao.readById((long) 1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updatePasswordByLogin() {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.updatePasswordByLogin(login, password)).thenReturn(expectedResult);
        Optional<User> actualResult = dao.updatePasswordByLogin(login, password);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateLogin() {
        boolean expectedResult = true;
        when(dao.updateLogin(id, login)).thenReturn(true);
        boolean actualResult = dao.updateLogin(id, login);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateEmail() {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.updateEmail(id, email)).thenReturn(expectedResult);
        Optional<User> actualResult = dao.updateEmail(id, email);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateFirstName() {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.updateFirstName(id, firstName)).thenReturn(expectedResult);
        Optional<User> actualResult = dao.updateFirstName(id, firstName);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateLastName() {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.updateLastName(id, lastName)).thenReturn(expectedResult);
        Optional<User> actualResult = dao.updateLastName(id, lastName);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateRole() {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.updateRole(id, (long)1)).thenReturn(expectedResult);
        Optional<User> actualResult = dao.updateRole(id, (long)1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readAll() throws EntityExtractionFailedException {
        List<User> expectedResult= users;
        when(dao.readAll()).thenReturn(expectedResult);
        List<User> actualResult = dao.readAll();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByFirstName() {
        List<User> expectedResult= users;
        when(dao.findByFirstName(firstName)).thenReturn(expectedResult);
        List<User> actualResult = dao.findByFirstName(firstName);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByLastName() {
        List<User> expectedResult= users;
        when(dao.findByLastName(lastName)).thenReturn(expectedResult);
        List<User> actualResult = dao.findByLastName(lastName);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByEmail() {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.findByEmail(email)).thenReturn(expectedResult);
        Optional<User> actualResult = dao.findByEmail(email);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findPasswordByLogin() {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.findPasswordByLogin(login)).thenReturn(expectedResult);
        Optional<User> actualResult = dao.findPasswordByLogin(login);
        assertEquals(actualResult, expectedResult);
    }
}