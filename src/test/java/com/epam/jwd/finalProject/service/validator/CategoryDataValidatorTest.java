package com.epam.jwd.finalProject.service.validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryDataValidatorTest {

    @Test
    public void isIdValid() {
        boolean resultFalse=CategoryDataValidator.getInstance().isIdValid((long)-5);
        boolean resultTrue=CategoryDataValidator.getInstance().isIdValid((long)5959);
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }

    @Test
    public void isNameValid() {
        boolean resultFalse=CategoryDataValidator.getInstance().isNameValid("");
        boolean resultTrue=CategoryDataValidator.getInstance().isNameValid("IT");
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }
}