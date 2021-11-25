package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.impl.MethodConferencDaoImpl;
import com.epam.jwd.finalProject.dao.impl.MethodUserDaoImpl;
import com.epam.jwd.finalProject.model.User;
import com.epam.jwd.finalProject.service.api.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private  final MethodUserDaoImpl userDao;

    public UserServiceImpl(MethodUserDaoImpl userDao) {
        this.userDao = userDao.getInstance();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findId(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public Optional<User> authenticate(String email, String password) {
        final Optional<User> readUser = userDao.findByEmail(email);
        return readUser.filter(user -> user.getPassword().equals(password)); //todo: hash password
    }
}
