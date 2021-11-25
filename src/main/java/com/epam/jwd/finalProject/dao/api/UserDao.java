package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodApplicationDaoImpl;
import com.epam.jwd.finalProject.dao.impl.MethodUserDaoImpl;
import com.epam.jwd.finalProject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean create(String email, String password);

    boolean updatePasswordByLogin(String login, String password);

    boolean updateLogin(Long id, String login);

    boolean updateEmail(Long id, String email);

    boolean updateFirstName(Long id, String firstName);

    boolean updateLastName(Long id, String lastName);

    boolean updateRole(Long idAccount, Long idRole);

    List<User> readAll() throws EntityExtractionFailedException;

    Optional<User> readById(Long id);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    Optional<User> findByEmail(String email);

    String findPasswordByLogin(String login);

    static UserDao instance() {
        return MethodUserDaoImpl.getInstance();
    }

}
