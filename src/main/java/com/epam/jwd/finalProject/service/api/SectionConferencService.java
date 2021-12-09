package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;

public interface SectionConferencService extends EntityService<SectionConferenc> {
    boolean create(String name, String description,Long idConferenc) throws ValidationException;

    boolean updateDescription(Long id, String description) throws ValidationException;

    List<SectionConferenc> findByName(String name) throws ValidationException;

    List<SectionConferenc> findSectionConferencesInConferencById(Long id) throws ValidationException;
}
