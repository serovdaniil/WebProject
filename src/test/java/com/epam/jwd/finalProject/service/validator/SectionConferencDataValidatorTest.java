package com.epam.jwd.finalProject.service.validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class SectionConferencDataValidatorTest {

    @Test
    public void isIdValid() {
        boolean resultFalse=SectionConferencDataValidator.getInstance().isIdValid((long)-5);
        boolean resultTrue=SectionConferencDataValidator.getInstance().isIdValid((long)5959);
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }

    @Test
    public void isNameValid() {
        boolean resultFalse=SectionConferencDataValidator.getInstance().isNameValid("");
        boolean resultTrue=SectionConferencDataValidator.getInstance().isNameValid("IT");
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }

    @Test
    public void isDescriptionValid() {
        boolean resultFalse=SectionConferencDataValidator.getInstance().isDescriptionValid("");
        boolean resultTrue=SectionConferencDataValidator.getInstance().isDescriptionValid("IT");
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }
}