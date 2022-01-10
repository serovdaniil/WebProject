package com.epam.jwd.finalProject.service.validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationDataValidatorTest {

    @Test
    public void isIdValid() {
        boolean resultFalse=ApplicationDataValidator.getInstance().isIdValid((long)-5);
        boolean resultTrue=ApplicationDataValidator.getInstance().isIdValid((long)5959);
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }
}