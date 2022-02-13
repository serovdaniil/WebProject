package com.epam.jwd.finalProject.command.factory;

import com.epam.jwd.finalProject.model.User;

import java.util.Optional;

/**
 * The interface request
 *
 * @author Daniil Serov
 */
public interface CommandRequest {

    void addAttributeToJsp(String name, Object attribute);

    String getParameter(String name);

    boolean sessionExists();

    boolean addToSession(String name, Object value);

    Optional<User> retrieveFromSession(String name);

    Long retrieveFromSessionLong(String name);

    void clearSession();

    void createSession();
}
