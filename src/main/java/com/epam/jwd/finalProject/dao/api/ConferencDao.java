package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodConferencDaoImpl;
import com.epam.jwd.finalProject.model.Conferenc;


import java.util.List;
import java.util.Optional;

public interface ConferencDao {
    boolean create(String name,String description,Long idCategory);

    boolean updateDescription(Long id, String description);

    List<Conferenc> readAllActive() throws EntityExtractionFailedException;

    List<Conferenc> readAll() throws EntityExtractionFailedException;

    Optional<Conferenc> readById(Long id);

    List<Conferenc> findByName(String name);

    boolean delete(Long id);

    boolean changeStatus(Long idConferenc,Long idStatus);

    static ConferencDao instance() {
        return MethodConferencDaoImpl.getInstance();
    }
}
