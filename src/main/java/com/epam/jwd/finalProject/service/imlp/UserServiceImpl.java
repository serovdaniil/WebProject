package com.epam.jwd.finalProject.service.imlp;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodUserDaoImpl;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.UserService;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static at.favre.lib.crypto.bcrypt.BCrypt.MIN_COST;

public class UserServiceImpl implements UserService {
    private static final byte[] DUMMY_PASSWORD = "password".getBytes(StandardCharsets.UTF_8);
    private final MethodUserDaoImpl userDao;
    private final BCrypt.Hasher hasher;
    private final BCrypt.Verifyer verifier;

    public UserServiceImpl(MethodUserDaoImpl userDao, BCrypt.Hasher hasher, BCrypt.Verifyer verifier) {
        this.userDao = userDao.getInstance();
        this.hasher = hasher;
        this.verifier = verifier;
    }

    @Override
    public List<User> findAll() {
        try {
            return userDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<User> findId(Long id) {
        return userDao.readById(id);
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public Optional<User> authenticate(String login, String password) {
        if (login == null || password == null) {
            return Optional.empty();
        }
        final byte[] enteredPassword = password.getBytes(StandardCharsets.UTF_8);
        final Optional<User> readUser = userDao.findPasswordByLogin(login);
        if (readUser.isPresent()) {
            final byte[] hashedPassword = readUser.get()
                    .getPassword()
                    .getBytes(StandardCharsets.UTF_8);
            return verifier.verify(enteredPassword, hashedPassword).verified
                    ? readUser
                    : Optional.empty();
        } else {
            protectFromTimingAttack(enteredPassword);
            return Optional.empty();
        }
    }

    private void protectFromTimingAttack(byte[] enteredPassword) {
        verifier.verify(enteredPassword, DUMMY_PASSWORD);
    }

    @Override
    public Optional<User> registration(String email, String password) {
        final char[] rawPassword = password.toCharArray();
        final String hashedPassword = hasher.hashToString(MIN_COST, rawPassword);
        return userDao.create(email, hashedPassword);
    }

    @Override
    public Optional<User> updatePasswordByLogin(String login, String password) {
        if (login == null || password == null) {
            return Optional.empty();
        }
        final char[] rawPassword = password.toCharArray();
        final String hashedPassword = hasher.hashToString(MIN_COST, rawPassword);
        final Optional<User> readUser = userDao.updatePasswordByLogin(login, hashedPassword);
        return readUser;
    }

    @Override
    public boolean updateLogin(Long id, String login) {
        return false;
    }

    @Override
    public Optional<User> updateEmail(Long id, String email) {
        return userDao.updateEmail(id, email);
    }

    @Override
    public Optional<User> updateFirstName(Long id, String firstName) {
        return userDao.updateFirstName(id, firstName);
    }

    @Override
    public Optional<User> updateLastName(Long id, String lastName) {
        return userDao.updateLastName(id, lastName);
    }

    @Override
    public Optional<User> updateRole(Long idAccount, Long idRole) {
        return userDao.updateRole(idAccount, idRole);
    }
}
