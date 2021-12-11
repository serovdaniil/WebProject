package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodUserDaoImpl;
import com.epam.jwd.finalProject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> create(String email, String password);

    Optional<User> updatePasswordByLogin(String login, String password);

    boolean updateLogin(Long id, String login);

    Optional<User> updateEmail(Long id, String email);

    Optional<User> updateFirstName(Long id, String firstName);

    Optional<User> updateLastName(Long id, String lastName);

    Optional<User> updateRole(Long idAccount, Long idRole);

    List<User> readAll() throws EntityExtractionFailedException;

    Optional<User> readById(Long id);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    Optional<User> findByEmail(String email);

    Optional<User> findPasswordByLogin(String login);

    static UserDao instance() {
        return MethodUserDaoImpl.getInstance();
    }

}
