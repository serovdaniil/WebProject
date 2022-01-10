package com.epam.jwd.finalProject.service.validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConferencDataValidatorTest {

    @Test
    public void isIdValid() {
        boolean resultFalse=ConferencDataValidator.getInstance().isIdValid((long)-5);
        boolean resultTrue=ConferencDataValidator.getInstance().isIdValid((long)5959);
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }

    @Test
    public void isNameValid() {
        boolean resultFalse=ConferencDataValidator.getInstance().isNameValid("");
        boolean resultTrue=ConferencDataValidator.getInstance().isNameValid("IT");
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }

    @Test
    public void isDescriptionValid() {
        boolean resultFalse=ConferencDataValidator.getInstance().isDescriptionValid("");
        boolean resultTrue=ConferencDataValidator.getInstance().isDescriptionValid("IT");
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }
}