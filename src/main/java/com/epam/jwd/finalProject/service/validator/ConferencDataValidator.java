package com.epam.jwd.finalProject.service.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConferencDataValidator {
    private static final String REGEX_NAME = "^[A-zА-Я]*$|^[A-zА-я]+\\s[A-zА-Я]*$";
    private static final String REGEX_DESCRIPTION = "^[A-zА-Я]*$|^[A-zА-я]+\\s[A-zА-Я]*$";
    private static final Logger LOG = LogManager.getLogger(ConferencDataValidator.class);

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
    public boolean isDescriptionValid(String description) {
        if (description.equals("")) {
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
