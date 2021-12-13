package com.epam.jwd.finalProject.command.factory;

/**
 * The interface response
 *
 * @author Daniil Serov
 */
public interface CommandResponse {

    boolean isRedirect();

    String getPath();
}
