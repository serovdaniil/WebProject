package com.epam.jwd.finalProject.dao.exception;
/**
 * @author Daniil Serov
 */
public class EntityExtractionFailedException extends Exception {

    public EntityExtractionFailedException(String message) {
        super(message);
    }

    public EntityExtractionFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityExtractionFailedException() {

    }
}
