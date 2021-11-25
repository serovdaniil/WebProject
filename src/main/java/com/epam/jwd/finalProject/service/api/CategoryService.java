package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;

import java.util.List;
import java.util.Optional;

public interface CategoryService extends EntityService<Category> {
    boolean create(String name);

    boolean changeName(Long id, String name);

    List<Conferenc> findConferencInIdCategory(Long id);

    List<Conferenc> findConferencInNameCategory(String name);

    List<SectionConferenc> findSectionConferencInIdCategory(Long id);

    List<SectionConferenc> findSectionConferencInNameCategory(String Name);
}
