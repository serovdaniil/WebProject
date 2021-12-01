package com.epam.jwd.finalProject.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidator {
    private static final String REGEX_FIRST_NAME = "@^([А-Я]{1}[а-яё]{1,44}|[A-Z]{1}[a-z]{1,44})$@gm";
    private static final String REGEX_LAST_NAME = "^[А-Я][а-я]*(-[А-Я][а-я]*)?$";
    private static final String REGEX_EMAIL = "^([A-Za-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String REGEX_LOGIN = "^([A-Za-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String REGEX_PASSWORD = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$";



    private Pattern pattern;
    private Matcher matcher;

    public boolean isFirstNameValid(String firstName) {
        if (firstName.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_FIRST_NAME);
        matcher = pattern.matcher(firstName);
        return matcher.matches();
    }

    public boolean isLastNameValid(String lastName) {
        if (lastName.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_LAST_NAME);
        matcher = pattern.matcher(lastName);
        return matcher.matches();
    }

    public boolean isEmailValid(String email) {
        if (email.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_EMAIL);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isPasswordValid(String password) {
        if (password.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_PASSWORD);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean isLoginValid(String login) {
        if (login.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_LOGIN);
        matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public boolean isIdValid(Long id) {
        if (id<=0) {
            return false;
        }
        return true;
    }
    public static UserDataValidator getInstance() {
        return UserDataValidator.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UserDataValidator INSTANCE = new UserDataValidator();
    }

}