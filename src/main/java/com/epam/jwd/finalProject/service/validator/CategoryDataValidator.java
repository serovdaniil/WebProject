package com.epam.jwd.finalProject.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryDataValidator {
    private static final String REGEX_NAME = "^.{2,50}$";
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
    public static CategoryDataValidator getInstance() {
        return CategoryDataValidator.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CategoryDataValidator INSTANCE = new CategoryDataValidator();
    }
}
