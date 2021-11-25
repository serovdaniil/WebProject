package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Conferenc;

import java.util.List;
import java.util.Optional;

public interface ConferencService extends EntityService<Conferenc> {
    boolean create(String name, String description,Long idCategory);

    boolean updateDescription(Long id, String description);

    List<Conferenc> findByName(String name);
}
