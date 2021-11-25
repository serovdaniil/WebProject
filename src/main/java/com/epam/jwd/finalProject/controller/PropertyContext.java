package com.epam.jwd.finalProject.controller;

public interface PropertyContext {
    String get(String name);

    static PropertyContext instance() {
        return SimplePropertyContext.getInstance();
    }
}
