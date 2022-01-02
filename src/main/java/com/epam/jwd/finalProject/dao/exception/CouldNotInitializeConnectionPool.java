package com.epam.jwd.finalProject.dao.exception;
/**
 * @author Daniil Serov
 */
public class CouldNotInitializeConnectionPool extends Error{

    public CouldNotInitializeConnectionPool(String message, Throwable cause) {
        super(message, cause);
    }
}
