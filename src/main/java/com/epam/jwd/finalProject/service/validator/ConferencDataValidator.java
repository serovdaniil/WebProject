package com.epam.jwd.finalProject.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConferencDataValidator {
    private static final String REGEX_NAME = "^.{2,400}$";
    private static final String REGEX_DESCRIPTION = "^.{0,1000}$";

    private Pattern pattern;
    private Matcher matcher;

    public boolean isIdValid(Long id) {
        if (id<=0) {
            return false;
        }
        return true;
    }
    public boolean isNameValid(String name) {
        if (name.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(REGEX_NAME);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public boolean isDescriptionValid(String description) {
        if (description.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(REGEX_DESCRIPTION);
        matcher = pattern.matcher(description);
        return matcher.matches();
    }
    public static ConferencDataValidator getInstance() {
        return ConferencDataValidator.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ConferencDataValidator INSTANCE = new ConferencDataValidator();
    }
}
