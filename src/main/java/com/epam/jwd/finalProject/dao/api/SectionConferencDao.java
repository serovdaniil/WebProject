package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodConferencDaoImpl;
import com.epam.jwd.finalProject.dao.impl.MethodSectionConferencDaoImpl;
import com.epam.jwd.finalProject.model.SectionConferenc;

import java.util.List;
import java.util.Optional;

public interface SectionConferencDao {
    boolean create(String name, String discription, Long idConferenc);

    boolean updateDescription(Long id, String description);

    List<SectionConferenc> readAll() throws EntityExtractionFailedException;

    Optional<SectionConferenc> readById(Long id);

    List<SectionConferenc> findByName(String name);

    List<SectionConferenc> findSectionConferencesInConferencById(Long id);

    boolean delete(Long id);

    static SectionConferencDao instance() {
        return MethodSectionConferencDaoImpl.getInstance();
    }
}
