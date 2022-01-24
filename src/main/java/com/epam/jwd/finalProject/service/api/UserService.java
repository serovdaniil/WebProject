package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;

/**
 * The interface user service
 *
 * @author Daniil Serov
 */
import java.util.Optional;


public interface UserService extends EntityService<User> {

    /**
     * Find count all users
     *
     * @return count users
     */
    Long findCountAllUser() throws ServiceException;

    /**
     * Authenticate
     *
     * @param email email for section conferenc
     * @param password password for section conferenc
     * @return User
     * @throws ValidationException if there are validation problems
     */
    Optional<User> authenticate(String email, String password) throws ValidationException, ServiceException;

    /**
     * Registration
     *
     * @param email email for section conferenc
     * @param password password for section conferenc
     * @return User
     * @throws ValidationException if there are validation problems
     */
    Optional<User> registration(String email, String password) throws ValidationException, ServiceException;

    /**
     * Update password
     *
     * @param login login for section conferenc
     * @param password password for section conferenc
     * @return User
     * @throws ValidationException if there are validation problems
     */
    Optional<User> updatePasswordByLogin(String login, String password) throws ValidationException, ServiceException;

    /**
     * Update email
     *
     * @param email email for section conferenc
     * @param id id section conferenc conferenc
     * @return User
     * @throws ValidationException if there are validation problems
     */
    Optional<User> updateEmail(Long id, String email) throws ValidationException, ServiceException;

    /**
     * Update first name user
     *
     * @param id id for user
     * @param firstName first name for user
     * @return User
     * @throws ValidationException if there are validation problems
     */
    Optional<User> updateFirstName(Long id, String firstName) throws ValidationException, ServiceException;

    /**
     * Update last name user
     *
     * @param id id for user
     * @param lastName last name for user
     * @return User
     * @throws ValidationException if there are validation problems
     */
    Optional<User> updateLastName(Long id, String lastName) throws ValidationException, ServiceException;

    /**
     * Update role name user
     *
     * @param idAccount id for user
     * @param nameRole name role for user
     * @return User
     * @throws ValidationException if there are validation problems
     */
    Optional<User> updateRole(Long idAccount, String nameRole) throws ValidationException, ServiceException;
}
