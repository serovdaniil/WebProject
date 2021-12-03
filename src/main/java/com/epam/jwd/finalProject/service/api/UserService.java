package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.Optional;

public interface UserService extends EntityService<User> {
    Optional<User> authenticate(String email, String password) throws ValidationException;

    Optional<User> registration(String email, String password) throws ValidationException;
    Optional<User> updatePasswordByLogin(String login,String password) throws ValidationException;

    Optional<User> updateEmail(Long id, String email) throws ValidationException;

    Optional<User> updateFirstName(Long id, String firstName) throws ValidationException;

    Optional<User> updateLastName(Long id, String lastName) throws ValidationException;

    Optional<User> updateRole(Long idAccount, String nameRole) throws ValidationException;
}
