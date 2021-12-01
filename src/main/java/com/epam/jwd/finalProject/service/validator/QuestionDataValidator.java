package com.epam.jwd.finalProject.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionDataValidator {
    private static final String REGEX_NAME = "^[A-ZА-Яa-zа-я]+((\\s)?((\\'|\\-|\\.|\\,)?([A-ZА-Яa-zа-я])+))*$";
    private static final String REGEX_ANSWER = "^[A-ZА-Яa-zа-я]+((\\s)?((\\'|\\-|\\.|\\,)?([A-ZА-Яa-zа-я])+))*$";

    private Pattern pattern;
    private Matcher matcher;

    public boolean isIdValid(Long id) {
        if (id<=0) {
            return false;
        }
        return true;
    }
    public boolean isNameValid(String name) {
        if (name.equals("")) {
            return false;
        }
        pattern = Pattern.compile(REGEX_NAME);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public boolean isAnswerValid(String answer) {
        if (answer.equals("")) {
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
