package com.epam.jwd.finalProject.dao.exception;

public class EntityExtractionFailedException extends Exception {
    private static final long serialVersionUID = 934886307168607804L;

    public EntityExtractionFailedException(String message) {
        super(message);
    }

    public EntityExtractionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityExtractionFailedException() {

    }
}
