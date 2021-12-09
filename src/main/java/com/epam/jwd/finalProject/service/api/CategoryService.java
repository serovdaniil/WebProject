package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;

public interface CategoryService extends EntityService<Category> {
    boolean create(String name) throws ValidationException;

    boolean changeName(Long id, String name) throws ValidationException;

    List<Conferenc> findConferencInIdCategory(Long id) throws ValidationException;

    List<SectionConferenc> findSectionConferencInIdCategory(Long id) throws ValidationException;

}
