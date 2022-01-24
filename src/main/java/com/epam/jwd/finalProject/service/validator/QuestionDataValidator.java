package com.epam.jwd.finalProject.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Daniil Serov
 */
public class QuestionDataValidator {
    /**
     * Regex string
     */
    private static final String REGEX_NAME = "^.{2,1000}$";
    private static final String REGEX_ANSWER = "^.{0,1000}$";

    private Pattern pattern;
    private Matcher matcher;
    /**
     * Validator id
     *
     * @param id param for validator
     * @return boolean
     */
    public boolean isIdValid(Long id) {
        if (id<=0) {
            return false;
        }
        return true;
    }
    /**
     * Validator name
     *
     * @param name param for validator
     * @return boolean
     */
    public boolean isNameValid(String name) {
        if (name.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(REGEX_NAME);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }
    /**
     * Validator answer
     *
     * @param answer param for validator
     * @return boolean
     */
    public boolean isAnswerValid(String answer) {
        if (answer.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(REGEX_ANSWER);
        matcher = pattern.matcher(answer);
        return matcher.matches();
    }

    public static QuestionDataValidator getInstance() {
        return QuestionDataValidator.Holder.INSTANCE;
    }

    private static class Holder {
        public static final QuestionDataValidator INSTANCE = new QuestionDataValidator();
    }
}
