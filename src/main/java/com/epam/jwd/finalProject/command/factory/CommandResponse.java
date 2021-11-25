package com.epam.jwd.finalProject.command.factory;

public interface CommandResponse {

    boolean isRedirect();

    String getPath();
}
