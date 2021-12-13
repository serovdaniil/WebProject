package com.epam.jwd.finalProject.controller;

import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;

import javax.servlet.http.HttpServletRequest;
/**
 * @author Daniil Serov
 */
public interface RequestFactory {
    CommandRequest createRequest(HttpServletRequest request);

    CommandResponse createForwardResponse(String path);

    CommandResponse createRedirectResponse(String path);

    static RequestFactory getInstance() {
        return SimpleRequestFactory.INSTANCE;
    }
}
