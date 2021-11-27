package com.epam.jwd.finalProject.command.factory;

import com.epam.jwd.finalProject.model.User;

import java.util.Optional;

public interface CommandRequest {

    void addAttributeToJsp(String name, Object attribute); //todo: make adding to jsp better

    String getParameter(String name);

    boolean sessionExists();

    boolean addToSession(String name, Object value);

    Optional<User> retrieveFromSession(String name);

    void clearSession();

    void createSession();
}
