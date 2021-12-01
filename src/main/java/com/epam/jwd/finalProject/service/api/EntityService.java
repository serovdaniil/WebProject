package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Entity;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;
import java.util.Optional;

public interface EntityService<T extends Entity> {

    List<T> findAll();

    Optional<T> findId(Long id) throws ValidationException;

    boolean remove(Long id);
}
