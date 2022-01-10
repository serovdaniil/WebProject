package com.epam.jwd.finalProject.service.validator;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionDataValidatorTest {

    @Test
    public void isIdValid() {
        boolean resultFalse=QuestionDataValidator.getInstance().isIdValid((long)-5);
        boolean resultTrue=QuestionDataValidator.getInstance().isIdValid((long)5959);
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }

    @Test
    public void isNameValid() {
        boolean resultFalse=QuestionDataValidator.getInstance().isNameValid("");
        boolean resultTrue=QuestionDataValidator.getInstance().isNameValid("IT");
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }

    @Test
    public void isAnswerValid() {
        boolean resultFalse=QuestionDataValidator.getInstance().isAnswerValid("");
        boolean resultTrue=QuestionDataValidator.getInstance().isAnswerValid("IT");
        assertFalse(resultFalse);
        assertTrue(resultTrue);
    }
}