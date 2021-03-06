package com.epam.jwd.finalProject.service.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author Daniil Serov
 */
public class SectionConferencDataValidator {
    /**
     * Regex string
     */
    private static final String REGEX_NAME = "^.{2,400}$";
    private static final String REGEX_DESCRIPTION = "^.{0,1000}$";

    private Pattern pattern;
    private Matcher matcher;
    /**
     * Validator id
     *
     * @param id param for validator
     * @return boolean
     */
    public boolean isIdValid(Long id) {
        return id<=0;
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
     * Validator description
     *
     * @param description param for validator
     * @return boolean
     */
    public boolean isDescriptionValid(String description) {
        if (description.isEmpty()) {
            return false;
        }
        pattern = Pattern.compile(REGEX_DESCRIPTION);
        matcher = pattern.matcher(description);
        return matcher.matches();
    }

    public static SectionConferencDataValidator getInstance() {
        return SectionConferencDataValidator.Holder.INSTANCE;
    }

    private static class Holder {
        public static final SectionConferencDataValidator INSTANCE = new SectionConferencDataValidator();
    }
}
