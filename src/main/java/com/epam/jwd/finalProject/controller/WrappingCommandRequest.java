package com.epam.jwd.finalProject.controller;

import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author Daniil Serov
 */
public class WrappingCommandRequest implements CommandRequest {
    private final HttpServletRequest request;

    public WrappingCommandRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void addAttributeToJsp(String name, Object attribute) {
        request.setAttribute(name, attribute);
    }

    @Override
    public String getParameter(String name) {
        return request.getParameter(name);
    }

    @Override
    public boolean sessionExists() {
        return request.getSession(false) != null;
    }

    @Override
    public boolean addToSession(String name, Object value) {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(name, value);
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> retrieveFromSession(String name) {
        return Optional.ofNullable(request.getSession(false))
                .map(session -> (User) session.getAttribute(name));
    }

    @Override
    public void clearSession() {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public void createSession() {
        request.getSession(true);
    }

    @Override
    public String getURL() {
        return String.valueOf(request.getRequestURL());
    }
}
