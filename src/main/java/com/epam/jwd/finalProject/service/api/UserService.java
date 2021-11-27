package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.User;

import java.util.Optional;

public interface UserService extends EntityService<User> {
    Optional<User> authenticate(String email, String password);

    Optional<User> registration(String email, String password);
    Optional<User> updatePasswordByLogin(String login,String password);

    boolean updateLogin(Long id, String login);

    Optional<User> updateEmail(Long id, String email);

    Optional<User> updateFirstName(Long id, String firstName);

    Optional<User> updateLastName(Long id, String lastName);

    Optional<User> updateRole(Long idAccount, Long idRole);
}
