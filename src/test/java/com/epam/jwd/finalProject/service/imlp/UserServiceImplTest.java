package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.model.Role;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest extends Assert {
    private UserServiceImpl service;
    private User user;
    private String firstName;
    private String lastName;
    private Long id;
    private String login;
    private String role;
    private String email;
    private String password;
    private List<User> users;

    @Before
    public void setUp() throws Exception {
        service = mock(UserServiceImpl.class);
        user = new User((long) 1, "daniils3rov@yandex.ru", "daniils3rov@yandex.ru",
                "Serov231969", "Daniil", "Serov", Role.USER);
        firstName = "Daniil";
        lastName = "Serov";
        id = (long) 1;
        role="User";
        login = "serov321414@gmail.com";
        email = "serov321414@gmail.com";
        password = "Serov231969";
        users=new ArrayList<>();
        users.add(user);
    }

    @Test
    public void findAll() throws ServiceException {
        List<User> expectedResult= users;
        when(service.findAll((long)1)).thenReturn(expectedResult);
        List<User> actualResult = service.findAll((long)1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findId() throws ValidationException, ServiceException  {
        Optional<User> expectedResult = Optional.of(user);
        when(service.findId(id)).thenReturn(expectedResult);
        Optional<User> actualResult = service.findId(id);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void remove() {
        boolean expectedResult = true;
        when(service.remove(id)).thenReturn(true);
        boolean actualResult = service.remove(id);
        org.junit.Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void authenticate() throws ValidationException, ServiceException  {
        Optional<User> expectedResult = Optional.of(user);
        when(service.authenticate(login,password)).thenReturn(expectedResult);
        Optional<User> actualResult = service.authenticate(login,password);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void registration() throws ValidationException, ServiceException  {
        Optional<User> expectedResult = Optional.of(user);
        when(service.registration(email,password)).thenReturn(expectedResult);
        Optional<User> actualResult = service.registration(email,password);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updatePasswordByLogin() throws ValidationException, ServiceException  {
        Optional<User> expectedResult = Optional.of(user);
        when(service.updatePasswordByLogin(login,password)).thenReturn(expectedResult);
        Optional<User> actualResult = service.updatePasswordByLogin(login,password);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateEmail() throws ValidationException, ServiceException  {
        Optional<User> expectedResult = Optional.of(user);
        when(service.updateEmail(id,email)).thenReturn(expectedResult);
        Optional<User> actualResult = service.updateEmail(id,email);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateFirstName() throws ValidationException, ServiceException  {
        Optional<User> expectedResult = Optional.of(user);
        when(service.updateFirstName(id,firstName)).thenReturn(expectedResult);
        Optional<User> actualResult = service.updateFirstName(id,firstName);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateLastName() throws ValidationException, ServiceException  {
        Optional<User> expectedResult = Optional.of(user);
        when(service.updateLastName(id,lastName)).thenReturn(expectedResult);
        Optional<User> actualResult = service.updateLastName(id,lastName);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateRole() throws ValidationException, ServiceException  {
        Optional<User> expectedResult = Optional.of(user);
        when(service.updateRole(id,role)).thenReturn(expectedResult);
        Optional<User> actualResult = service.updateRole(id,role);
        assertEquals(actualResult, expectedResult);
    }
}