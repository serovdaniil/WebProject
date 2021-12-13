package com.epam.jwd.finalProject.service.imlp;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodUserDaoImpl;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.UserService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static at.favre.lib.crypto.bcrypt.BCrypt.MIN_COST;

public class UserServiceImpl implements UserService {
    private static final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
    private static final byte[] DUMMY_PASSWORD = "password".getBytes(StandardCharsets.UTF_8);
    private final MethodUserDaoImpl userDao;
    private final BCrypt.Hasher hasher;
    private final BCrypt.Verifyer verifier;
    private final UserDataValidator userDataValidator = new UserDataValidator().getInstance();

    public UserServiceImpl(MethodUserDaoImpl userDao, BCrypt.Hasher hasher, BCrypt.Verifyer verifier) {
        this.userDao = userDao.getInstance();
        this.hasher = hasher;
        this.verifier = verifier;
    }

    @Override
    public List<User> findAll() {
        LOG.debug("Service: Reading all users started.");
        try {
            return userDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }
        LOG.debug("Service: Reading all users finished.");
        return Collections.emptyList();
    }

    @Override
    public Optional<User> findId(Long id) throws ValidationException {
        LOG.debug("Service: Reading user started.");
        if (!userDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Reading user finished.");
        return userDao.readById(id);
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public Optional<User> authenticate(String login, String password) throws ValidationException {
        if (!userDataValidator.isLoginValid(login) || !userDataValidator.isPasswordValid(password)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Authenticating started.");
        final byte[] enteredPassword = password.getBytes(StandardCharsets.UTF_8);
        final Optional<User> readUser = userDao.findPasswordByLogin(login);
        if (readUser.isPresent()) {
            final byte[] hashedPassword = readUser.get()
                    .getPassword()
                    .getBytes(StandardCharsets.UTF_8);
            LOG.debug("Service: Authenticating finished.");
            return verifier.verify(enteredPassword, hashedPassword).verified
                    ? readUser
                    : Optional.empty();
        } else {
            protectFromTimingAttack(enteredPassword);
            LOG.debug("Service: Authenticating finished.");
            return Optional.empty();
        }
    }

    private void protectFromTimingAttack(byte[] enteredPassword) {
        verifier.verify(enteredPassword, DUMMY_PASSWORD);
    }

    @Override
    public Optional<User> registration(String email, String password) throws ValidationException {
        LOG.debug("Service: Registration started.");
        if (!userDataValidator.isEmailValid(email) || !userDataValidator.isPasswordValid(password)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }

        final char[] rawPassword = password.toCharArray();
        final String hashedPassword = hasher.hashToString(MIN_COST, rawPassword);
        LOG.debug("Service: Registration finished.");
        return userDao.create(email, hashedPassword);
    }

    @Override
    public Optional<User> updatePasswordByLogin(String login, String password) throws ValidationException {
        LOG.debug("Service: Updating password started.");
        if (!userDataValidator.isLoginValid(login) || !userDataValidator.isPasswordValid(password)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        final char[] rawPassword = password.toCharArray();
        final String hashedPassword = hasher.hashToString(MIN_COST, rawPassword);
        final Optional<User> readUser = userDao.updatePasswordByLogin(login, hashedPassword);
        LOG.debug("Service: Updating password finished.");
        return readUser;
    }

    @Override
    public Optional<User> updateEmail(Long id, String email) throws ValidationException {
        LOG.debug("Service: Updating email started.");
        if (!userDataValidator.isEmailValid(email) || !userDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Updating email finished.");
        return userDao.updateEmail(id, email);
    }

    @Override
    public Optional<User> updateFirstName(Long id, String firstName) throws ValidationException {
        LOG.debug("Service: Updating first name started.");
        if (!userDataValidator.isFirstNameValid(firstName) || !userDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Updating first name finished.");
        return userDao.updateFirstName(id, firstName);
    }

    @Override
    public Optional<User> updateLastName(Long id, String lastName) throws ValidationException {
        LOG.debug("Service: Updating last name started.");
        if (!userDataValidator.isLastNameValid(lastName) || !userDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Updating last name finished.");
        return userDao.updateLastName(id, lastName);
    }

    @Override
    public Optional<User> updateRole(Long idAccount, String nameRole) throws ValidationException {
        LOG.debug("Service: Updating role for user started.");
        final Long idRole = role(nameRole);
        if (!userDataValidator.isIdValid(idAccount) || !userDataValidator.isIdValid(idRole)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Updating role for user finished.");
        return userDao.updateRole(idAccount, idRole);
    }

    private Long role(String nameRole) {
        Long idResult = null;
        if ((nameRole.equals("User")) || (nameRole.equals("Юзер")) || (nameRole.equals("Uzer")) || (nameRole.equals("Юзэр"))) {
            idResult = (long) 1;
        }
        if ((nameRole.equals("Admin")) || (nameRole.equals("Админ")) || (nameRole.equals("Адмін"))) {
            idResult = (long) 2;
        }
        if ((nameRole.equals("Bloked")) || (nameRole.equals("Bloqué")) || (nameRole.equals("Заблокированный")) || (nameRole.equals("Заблакаваны"))) {
            idResult = (long) 4;
        }
        return idResult;
    }
}
