package com.epam.jwd.finalProject.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationDataValidator {
    private Pattern pattern;
    private Matcher matcher;

    public boolean isIdValid(Long id) {
        if (id<=0) {
            return false;
        }
        return true;
    }
    public static ApplicationDataValidator getInstance() {
        return ApplicationDataValidator.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ApplicationDataValidator INSTANCE = new ApplicationDataValidator();
    }
}
