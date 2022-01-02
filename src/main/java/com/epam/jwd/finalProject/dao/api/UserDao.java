package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.UserDaoImpl;
import com.epam.jwd.finalProject.model.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface user dao
 *
 * @author Daniil Serov
 */
public interface UserDao {

    /**
     * Create user
     *
     * @param email    email for new user
     * @param password password for new user
     * @return User
     */
    Optional<User> create(String email, String password);

    /**
     * Update password by login
     *
     * @param login    login for user
     * @param password password for user
     * @return User
     */
    Optional<User> updatePasswordByLogin(String login, String password);

    /**
     * Update email
     *
     * @param id    id for user
     * @param email email for user
     * @return User
     */
    Optional<User> updateEmail(Long id, String email);

    /**
     * Update first name
     *
     * @param id        id for user
     * @param firstName firstName for user
     * @return User
     */
    Optional<User> updateFirstName(Long id, String firstName);

    /**
     * Update last name
     *
     * @param id       id for user
     * @param lastName lastName for user
     * @return User
     */
    Optional<User> updateLastName(Long id, String lastName);

    /**
     * Update role
     *
     * @param idAccount id for user
     * @param idRole    id role for user
     * @return User
     */
    Optional<User> updateRole(Long idAccount, Long idRole);

    /**
     * Read all users
     *
     * @return List user
     */
    List<User> readAll() throws EntityExtractionFailedException;

    /**
     * Read user by id
     *
     * @param id id for user
     * @return User
     */
    Optional<User> readById(Long id);

    /**
     * Find user by email
     *
     * @param email email for user
     * @return User
     */
    Optional<User> findByEmail(String email);

    /**
     * Find password by login
     *
     * @param login login for user
     * @return User
     */
    Optional<User> findPasswordByLogin(String login);

    /**
     * Gets instance.
     *
     * @return the instance
     */
    static UserDao instance() {
        return UserDaoImpl.getInstance();
    }

}
