package com.epam.jwd.finalProject.controller;
/**
 * @author Daniil Serov
 */
public interface PropertyContext {
    String get(String name);

    static PropertyContext instance() {
        return SimplePropertyContext.getInstance();
    }
}
