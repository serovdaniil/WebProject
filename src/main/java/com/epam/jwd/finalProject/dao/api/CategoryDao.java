package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodCategoryDaoImpl;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {
    boolean create(String name);

    boolean changeName(Long id, String name);

    List<Category> readAll() throws EntityExtractionFailedException;

    Optional<Category> readById(Long id);

    List<Conferenc> findConferencInIdCategory(Long id);

    List<Conferenc> findConferencInNameCategory(String name);

    List<SectionConferenc> findSectionConferencInIdCategory(Long id);

    List<SectionConferenc> findSectionConferencInNameCategory(String Name);

    boolean delete(Long id);

    static CategoryDao instance() {
        return MethodCategoryDaoImpl.getInstance();
    }
}
