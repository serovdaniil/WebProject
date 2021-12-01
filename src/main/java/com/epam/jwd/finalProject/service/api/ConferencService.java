package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;
import java.util.Optional;

public interface ConferencService extends EntityService<Conferenc> {
    boolean create(String name, String description,Long idCategory) throws ValidationException;

    boolean updateDescription(Long id, String description) throws ValidationException;

    List<Conferenc> findByName(String name) throws ValidationException;
}
