package com.epam.jwd.finalProject.service.validator;

public class ApplicationDataValidator {
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
