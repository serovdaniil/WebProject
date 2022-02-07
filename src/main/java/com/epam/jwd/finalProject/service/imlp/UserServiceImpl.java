package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.UserDaoImpl;
import com.epam.jwd.finalProject.mail.MailSender;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.security.PasswordEncoder;
import com.epam.jwd.finalProject.service.api.UserService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * @author Daniil Serov
 * @see UserDaoImpl
 */
public class UserServiceImpl implements UserService {
    /**
     * Logger for this service
     */
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    /**
     * Dao for this service
     */
    private final UserDaoImpl userDao;

    /**
     * BCrypt for password
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Validator for this service
     */
    private final UserDataValidator userDataValidator = new UserDataValidator().getInstance();

    /**
     * Limit for pagination
     */
    private static final Long LIMIT = (long) 5;

    /**
     * Subject message
     */
    private static final String subjectMsg = "Password recovery code!";

    /**
     * Text message
     */
    private static final String textMsg = "Enter this code in the appropriate field on the website " +
            "to recover the password from your personal account. Code: ";

    /**
     * Constructor - creating a new object
     *
     * @param userDao         dao for this service
     * @param passwordEncoder encoder for this password
     */
    public UserServiceImpl(UserDaoImpl userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder.getInstance();
    }

    /**
     * Find user by email
     *
     * @param email email for user
     *
     * @return result found
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean findByEmail(String email) throws ValidationException, ServiceException {
        try {
            if (!userDataValidator.isEmailValid(email)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return userDao.findByEmail(email).isPresent();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Mail to send the password recovery code
     *
     * @param email Email to send
     * @param code  Recovery code
     */
    @Override
    public void recoveryPassword(String email, Long code) throws ValidationException {
        if (!userDataValidator.isCodeValid(Long.toString(code)) ||
                !userDataValidator.isEmailValid(email)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        MailSender mailSender = new MailSender();
        final String text = textMsg + code;
        mailSender.send(subjectMsg, text, email);
    }

    /**
     * Find count all users
     *
     * @return count users
     */
    @Override
    public Long findCountAllUser() throws ServiceException {
        try {
            final Long countUser = userDao.findCountAllUser();
            Long pageCount = countUser / LIMIT;
            if ((countUser - pageCount * LIMIT) > 0) {
                pageCount++;
            }
            return pageCount;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find all users
     *
     * @param pageNumber selected page
     * @return List user
     */
    @Override
    public List<User> findAll(Long pageNumber) throws ServiceException {
        try {
            final Long offset = LIMIT * (pageNumber - 1);
            return userDao.readAll(LIMIT, offset);
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return Collections.emptyList();
    }

    /**
     * Find user by id
     *
     * @param id id user
     * @return User
     * @throws ValidationException if there are validation problems
     */
    @Override
    public Optional<User> findId(Long id) throws ValidationException, ServiceException {
        try {
            if (!userDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return userDao.readById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    /**
     * Authenticate user
     *
     * @param login    login for user
     * @param password password for user
     * @return User
     * @throws ValidationException if there are validation problems
     */
    @Override
    public Optional<User> authenticate(String login, String password) throws ValidationException, ServiceException {
        try {
            if (!userDataValidator.isLoginValid(login) || !userDataValidator.isPasswordValid(password)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            final byte[] enteredPassword = password.getBytes(StandardCharsets.UTF_8);
            final Optional<User> readUser = userDao.findPasswordByLogin(login);
            if (readUser.isPresent()) {
                final byte[] hashedPassword = readUser.get()
                        .getPassword()
                        .getBytes(StandardCharsets.UTF_8);
                return passwordEncoder.checkPassword(enteredPassword, hashedPassword)
                        ? readUser
                        : Optional.empty();
            } else {
                passwordEncoder.protectFromTimingAttack(enteredPassword);
                return Optional.empty();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Registration user
     *
     * @param email    email for user
     * @param password password for user
     * @return User
     * @throws ValidationException if there are validation problems
     */
    @Override
    public Optional<User> registration(String email, String password)
            throws ValidationException, ServiceException {
        try {
            if (!userDataValidator.isEmailValid(email) || !userDataValidator.isPasswordValid(password)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return userDao.create(email, passwordEncoder.encoder(password));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update password by login
     *
     * @param login    login for user
     * @param password password for user
     * @return User
     * @throws ValidationException if there are validation problems
     */
    @Override
    public Optional<User> updatePasswordByLogin(String login, String password)
            throws ValidationException, ServiceException {
        try {
            if (!userDataValidator.isLoginValid(login) || !userDataValidator.isPasswordValid(password)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            final String hashedPassword = passwordEncoder.encoder(password);
            final Optional<User> readUser = userDao.updatePasswordByLogin(login, hashedPassword);
            return readUser;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update email for user
     *
     * @param id    id user
     * @param email email for user
     * @return User
     * @throws ValidationException if there are validation problems
     */
    @Override
    public Optional<User> updateEmail(Long id, String email) throws ValidationException, ServiceException {
        try {
            if (!userDataValidator.isEmailValid(email) || !userDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return userDao.updateEmail(id, email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update first name for user
     *
     * @param id        id user
     * @param firstName first name for user
     * @return User
     * @throws ValidationException if there are validation problems
     */
    @Override
    public Optional<User> updateFirstName(Long id, String firstName) throws ValidationException, ServiceException {
        try {
            if (!userDataValidator.isFirstNameValid(firstName) || !userDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return userDao.updateFirstName(id, firstName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update last name for user
     *
     * @param id       id user
     * @param lastName last name for user
     * @return User
     * @throws ValidationException if there are validation problems
     */
    @Override
    public Optional<User> updateLastName(Long id, String lastName) throws ValidationException, ServiceException {
        try {
            if (!userDataValidator.isLastNameValid(lastName) || !userDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return userDao.updateLastName(id, lastName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update role for user
     *
     * @param idAccount id user
     * @param nameRole  role for user
     * @return User
     * @throws ValidationException if there are validation problems
     */
    @Override
    public Optional<User> updateRole(Long idAccount, String nameRole) throws ValidationException, ServiceException {
        try {
            final Long idRole = role(nameRole);
            if (!userDataValidator.isIdValid(idAccount) || !userDataValidator.isIdValid(idRole)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return userDao.updateRole(idAccount, idRole);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Check result section
     *
     * @param nameRole role name
     * @return Long idRole
     */
    private Long role(String nameRole) {
        Long idResult = null;
        if ((nameRole.equals("User")) || (nameRole.equals("Юзер")) || (nameRole.equals("Uzer")) ||
                (nameRole.equals("Юзэр"))) {
            idResult = (long) 1;
        }
        if ((nameRole.equals("Admin")) || (nameRole.equals("Админ")) ||
                (nameRole.equals("Адмін"))) {
            idResult = (long) 2;
        }
        if ((nameRole.equals("Bloked")) || (nameRole.equals("Bloqué")) ||
                (nameRole.equals("Заблокированный")) || (nameRole.equals("Заблакаваны"))) {
            idResult = (long) 4;
        }
        return idResult;
    }
}
