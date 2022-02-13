package com.epam.jwd.finalProject.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Daniil Serov
 */
public class UserDataValidator {
    /**
     * Regex string
     */
    private static final String REGEX_FIRST_NAME =
            "(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,22}$)|(^[А-Я][а-я]{0,22}(-[А-Я])*[а-я]{0,22}$)|(^[A-Z][a-z]{0,45}$)|(^[А-Я][а-я]{0,45}$)";
    private static final String REGEX_LAST_NAME =
            "(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,22}$)|(^[А-Я][а-я]{0,22}(-[А-Я])*[а-я]{0,22}$)|(^[A-Z][a-z]{0,45}$)|(^[А-Я][а-я]{0,45}$)";
    private static final String REGEX_EMAIL =
            "^([A-Za-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String REGEX_LOGIN =
            "^([A-Za-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String REGEX_PASSWORD =
            "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$";
    private static final String REGEX_CODE ="(?<!\\d)[1-9]\\d{5}(?!\\d)";

    private Pattern pattern;
    private Matcher matcher;

    /**
     * Validator code for recovery password
     *
     * @param code param for validator
     * @return boolean
     */
    public boolean isCodeValid(String code) {
        if (code.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(REGEX_CODE, Pattern.UNICODE_CHARACTER_CLASS);
        matcher = pattern.matcher(code);
        return matcher.matches();
    }

    /**
     * Validator first name
     *
     * @param firstName param for validator
     * @return boolean
     */
    public boolean isFirstNameValid(String firstName) {
        if (firstName.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(REGEX_FIRST_NAME, Pattern.UNICODE_CHARACTER_CLASS);
        matcher = pattern.matcher(firstName);
        return matcher.matches();
    }
    /**
     * Validator last name
     *
     * @param lastName param for validator
     * @return boolean
     */
    public boolean isLastNameValid(String lastName) {
        if (lastName.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(REGEX_LAST_NAME);
        matcher = pattern.matcher(lastName);
        return matcher.matches();
    }
    /**
     * Validator email
     *
     * @param email param for validator
     * @return boolean
     */
    public boolean isEmailValid(String email) {
        if (email.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(REGEX_EMAIL);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    /**
     * Validator password
     *
     * @param password param for validator
     * @return boolean
     */
    public boolean isPasswordValid(String password) {
        if (password.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(REGEX_PASSWORD);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
    /**
     * Validator login
     *
     * @param login param for validator
     * @return boolean
     */
    public boolean isLoginValid(String login) {
        if (login.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(REGEX_LOGIN);
        matcher = pattern.matcher(login);
        return matcher.matches();
    }
    /**
     * Validator id
     *
     * @param id param for validator
     * @return boolean
     */
    public boolean isIdValid(Long id) {
        return id<=0;
    }
    public static UserDataValidator getInstance() {
        return UserDataValidator.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UserDataValidator INSTANCE = new UserDataValidator();
    }

}