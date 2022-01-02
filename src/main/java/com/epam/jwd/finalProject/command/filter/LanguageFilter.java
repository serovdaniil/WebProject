package com.epam.jwd.finalProject.command.filter;

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
    private static final String DEFAULT_PATH = "/";
    private static final String RUSSIAN_LANGUAGE = "ru_RU";
    private static final String BELORUSSIAN_LANGUAGE = "be_BE";
    private static final String FRANCE_LANGUAGE = "fr_FR";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final String languageFromRequest = request.getParameter(LANGUAGE_COOKIE_NAME);
        if (doesNotContainLangCookie((HttpServletRequest) request)) {
            addEnglishLangCookie((HttpServletResponse) response);
        }
        if(!doesNotContainLangCookie((HttpServletRequest) request)&&languageFromRequest!=null){
            addEnglishLangCookie((HttpServletResponse)response);
        }
        if(!doesNotContainLangCookie((HttpServletRequest) request) &&
                RUSSIAN_LANGUAGE.equalsIgnoreCase(languageFromRequest)){
            addRussianLangCookie((HttpServletResponse) response);
        }
        if(!doesNotContainLangCookie((HttpServletRequest) request) &&
                BELORUSSIAN_LANGUAGE.equalsIgnoreCase(languageFromRequest)){
            addBelorussianLangCookie((HttpServletResponse) response);
        }
        if(!doesNotContainLangCookie((HttpServletRequest) request) &&
                FRANCE_LANGUAGE.equalsIgnoreCase(languageFromRequest)){
            addFranceLangCookie((HttpServletResponse) response);
        }
        chain.doFilter(request, response);
    }

    private boolean doesNotContainLangCookie(HttpServletRequest httpRequest) {
        final Cookie[] cookies = httpRequest.getCookies();
        return cookies == null || Arrays.stream(cookies)
                .noneMatch(cookie -> cookie.getName().equals(LANGUAGE_COOKIE_NAME));
    }

    private void addEnglishLangCookie(HttpServletResponse response) {
        final Cookie languageCookie = new Cookie(LANGUAGE_COOKIE_NAME, DEFAULT_LANGUAGE);
        languageCookie.setPath(DEFAULT_PATH);
        response.addCookie(languageCookie);
    }
    private void addRussianLangCookie(HttpServletResponse response) {
        final Cookie languageCookie = new Cookie(LANGUAGE_COOKIE_NAME, RUSSIAN_LANGUAGE);
        languageCookie.setPath(DEFAULT_PATH);
        response.addCookie(languageCookie);
    }
    private void addBelorussianLangCookie(HttpServletResponse response) {
        final Cookie languageCookie = new Cookie(LANGUAGE_COOKIE_NAME, BELORUSSIAN_LANGUAGE);
        languageCookie.setPath(DEFAULT_PATH);
        response.addCookie(languageCookie);
    }
    private void addFranceLangCookie(HttpServletResponse response) {
        final Cookie languageCookie = new Cookie(LANGUAGE_COOKIE_NAME, FRANCE_LANGUAGE);
        languageCookie.setPath(DEFAULT_PATH);
        response.addCookie(languageCookie);
    }
}