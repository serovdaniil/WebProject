package com.epam.jwd.finalProject.controller;

import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Daniil Serov
 */
public enum SimpleRequestFactory implements RequestFactory {
    INSTANCE;

    private final Map<String, CommandResponse> forwardResponseCache = new ConcurrentHashMap<>();
    private final Map<String, CommandResponse> redirectResponseCache = new ConcurrentHashMap<>();

    @Override
    public CommandRequest createRequest(HttpServletRequest request) {
        return new WrappingCommandRequest(request);
    }

    @Override
    public CommandResponse createForwardResponse(String path) {
        return forwardResponseCache.computeIfAbsent(path, PlainCommandResponse::new);
    }

    @Override
    public CommandResponse createRedirectResponse(String path) {
        return redirectResponseCache.computeIfAbsent(path, p -> new PlainCommandResponse(true, p));
    }
}
