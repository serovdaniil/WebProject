package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodConferencDaoImpl;
import com.epam.jwd.finalProject.dao.impl.MethodUserDaoImpl;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private  final MethodUserDaoImpl userDao;

    public UserServiceImpl(MethodUserDaoImpl userDao) {
        this.userDao = userDao.getInstance();
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
        final Optional<User> readUser = userDao.findPasswordByLogin(login);
        return readUser.filter(user -> user.getPassword().equals(password)); //todo: hash password
    }

    @Override
    public Optional<User> registration(String email, String password) {
        return userDao.create(email,password);
    }

    @Override
    public Optional<User> updatePasswordByLogin(String login,String password) {
        return userDao.updatePasswordByLogin(login, password);
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
        return userDao.updateFirstName(id,firstName);
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
