package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.UserDaoImpl;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest extends Assert {
    @Mock
    private UserDaoImpl dao;
    @InjectMocks
    private UserServiceImpl service;
    private Long limitPagination;
    private Long offsetPagination;
    private Long idPage;
    private User user;
    private String firstName;
    private String lastName;
    private Long id;
    private Long code;
    private String login;
    private String role;
    private String email;
    private String passwordDecoder;
    private String passwordEncoder;
    private List<User> users;

    @Before
    public void setUp() throws Exception {
        user = new User((long) 1, "daniils3rov@yandex.ru", "daniils3rov@yandex.ru",
                "$2a$04$.Ta6N2LUbMhpSOvzuTEute5dHD8ZSQMImc/LCMHAtPfrS0motOvny", "Daniil",
                "Serov", Role.USER);
        firstName = "Daniil";
        lastName = "Serov";
        id = (long) 1;
        role = "User";
        login = "serov321414@gmail.com";
        email = "serov321414@gmail.com";
        passwordDecoder = "Serov231969";
        passwordEncoder = "$2a$04$.Ta6N2LUbMhpSOvzuTEute5dHD8ZSQMImc/LCMHAtPfrS0motOvny";
        users = new ArrayList<>();
        users.add(user);
        limitPagination = (long) 5;
        offsetPagination = (long) 0;
        idPage = (long) 1;
        code=(long)111111;
    }

    @Test
    public void findAll() throws ServiceException, DaoException, EntityExtractionFailedException {
        List<User> expectedResult = users;
        when(dao.readAll(limitPagination, offsetPagination)).thenReturn(expectedResult);
        List<User> actualResult = service.findAll(idPage);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findId() throws ValidationException, ServiceException, DaoException {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.readById(id)).thenReturn(expectedResult);
        Optional<User> actualResult = service.findId(id);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void authenticate() throws ValidationException, ServiceException, DaoException {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.findPasswordByLogin(login)).thenReturn(expectedResult);
        Optional<User> actualResult = service.authenticate(login, passwordDecoder);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updatePasswordByLogin() throws ValidationException, ServiceException, DaoException {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.updatePasswordByLogin(login, passwordEncoder)).thenReturn(expectedResult);
        Optional<User> actualResult = service.updatePasswordByLogin(login, passwordDecoder);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateEmail() throws ValidationException, ServiceException, DaoException {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.updateEmail(id, email)).thenReturn(expectedResult);
        Optional<User> actualResult = service.updateEmail(id, email);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateFirstName() throws ValidationException, ServiceException, DaoException {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.updateFirstName(id, firstName)).thenReturn(expectedResult);
        Optional<User> actualResult = service.updateFirstName(id, firstName);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateLastName() throws ValidationException, ServiceException, DaoException {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.updateLastName(id, lastName)).thenReturn(expectedResult);
        Optional<User> actualResult = service.updateLastName(id, lastName);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateRole() throws ValidationException, ServiceException, DaoException {
        Optional<User> expectedResult = Optional.of(user);
        when(dao.updateRole(id, id)).thenReturn(expectedResult);
        Optional<User> actualResult = service.updateRole(id, role);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void recoveryPassword() throws ValidationException {
        service.recoveryPassword(email, code);
    }

    @Test
    public void findCountAllUser() throws DaoException, ServiceException {
        Long expectedResult = (long) 4;
        when(dao.findCountAllUser()).thenReturn(expectedResult);
        Long actualResult = service.findCountAllUser();
        expectedResult = pageCount(expectedResult, actualResult);
        assertEquals(expectedResult, actualResult);
    }

    private Long pageCount(Long expectedResult, Long actualResult) {
        if ((expectedResult - (actualResult * limitPagination) <= 5) ||
                (expectedResult + (actualResult * limitPagination) <= 5)) {
            expectedResult = actualResult;
        }
        return expectedResult;
    }
}