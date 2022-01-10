package com.epam.jwd.finalProject.service.validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserDataValidatorTest {

    @Test
    public void isFirstNameValid() {
        boolean resultFalse=UserDataValidator.getInstance().isFirstNameValid("Dan1");
        boolean resultFalseEmpty=UserDataValidator.getInstance().isFirstNameValid("");
        boolean resultTrue=UserDataValidator.getInstance().isFirstNameValid("Daniil");
        assertFalse(resultFalse);
        assertFalse(resultFalseEmpty);
        assertTrue(resultTrue);
    }

    @Test
    public void isLastNameValid() {
        boolean resultFalse=UserDataValidator.getInstance().isLastNameValid("Ser-ov");
        boolean resultFalseEmpty=UserDataValidator.getInstance().isLastNameValid("");
        boolean resultTrue=UserDataValidator.getInstance().isLastNameValid("Serov");
        assertFalse(resultFalse);
        assertFalse(resultFalseEmpty);
        assertTrue(resultTrue);
    }

    @Test
    public void isEmailValid() {
        boolean resultFalse=UserDataValidator.getInstance().isEmailValid("daniils3rov.com");
        boolean resultFalseEmpty=UserDataValidator.getInstance().isEmailValid("");
        boolean resultTrue=UserDataValidator.getInstance().isEmailValid("daniils3rov@yandex.com");
        assertFalse(resultFalse);
        assertFalse(resultFalseEmpty);
        assertTrue(resultTrue);
    }

    @Test
    public void isPasswordValid() {
        boolean resultFalse=UserDataValidator.getInstance().isPasswordValid("S498");
        boolean resultFalseEmpty=UserDataValidator.getInstance().isPasswordValid("");
        boolean resultTrue=UserDataValidator.getInstance().isPasswordValid("Serov19075");
        assertFalse(resultFalse);
        assertFalse(resultFalseEmpty);
        assertTrue(resultTrue);
    }

    @Test
    public void isLoginValid() {
        boolean resultFalse=UserDataValidator.getInstance().isLoginValid("daniils3rov.com");
        boolean resultFalseEmpty=UserDataValidator.getInstance().isLoginValid("");
        boolean resultTrue=UserDataValidator.getInstance().isLoginValid("daniils3rov@yandex.com");
        assertFalse(resultFalse);
        assertFalse(resultFalseEmpty);
        assertTrue(resultTrue);
    }

    @Test
    public void isIdValid() {
        boolean resultFalse=UserDataValidator.getInstance().isIdValid((long)-5);
        boolean resultTrue=UserDataValidator.getInstance().isIdValid((long)5959);
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }
}