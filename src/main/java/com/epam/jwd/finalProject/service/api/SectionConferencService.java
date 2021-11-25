package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;

import java.util.List;

public interface SectionConferencService extends EntityService<SectionConferenc> {
    boolean create(String name, String description,Long idConferenc);

    boolean updateDescription(Long id, String description);

    List<SectionConferenc> findByName(String name);

    List<SectionConferenc> findSectionConferencesInConferencById(Long id);
}
