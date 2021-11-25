package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.User;

import java.util.Optional;

public interface UserService extends EntityService<User>{
    Optional<User> authenticate(String email, String password);
}
