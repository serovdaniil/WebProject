package com.epam.jwd.finalProject.service.validator;

/**
 * @author Daniil Serov
 */
public class ApplicationDataValidator {
    /**
     * Validator id
     *
     * @param id param for validator
     * @return boolean
     */
    public boolean isIdValid(Long id) {
        return id<=0;
    }
    public static ApplicationDataValidator getInstance() {
        return ApplicationDataValidator.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ApplicationDataValidator INSTANCE = new ApplicationDataValidator();
    }
}
