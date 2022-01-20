package com.epam.jwd.finalProject.command.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Localization filter
 *
 * @author Daniil Serov
 */
@WebFilter(urlPatterns = "/*")
public class LanguageFilter implements Filter {

    private static final String LANGUAGE_COOKIE_NAME = "lang";
    private static final String DEFAULT_LANGUAGE = "en_US";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        if (cookies == null || Arrays.stream(cookies).noneMatch(this::isLanguageCookie)) {
              addDefaultLanguageCookie((HttpServletResponse) response);
        }
        chain.doFilter(request, response);
    }

    private boolean isLanguageCookie(Cookie cookie) {
        return cookie.getName().equals(LANGUAGE_COOKIE_NAME);
    }

    private void addDefaultLanguageCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(LANGUAGE_COOKIE_NAME, DEFAULT_LANGUAGE);
        response.addCookie(cookie);
    }
}