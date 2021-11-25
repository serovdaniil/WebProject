package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodCategoryDaoImpl;
import com.epam.jwd.finalProject.dao.impl.MethodSectionConferencDaoImpl;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.CategoryService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    private final MethodCategoryDaoImpl categoryDao;

    public CategoryServiceImpl(MethodCategoryDaoImpl categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public boolean create(String name) {
        return categoryDao.create(name);
    }

    @Override
    public boolean changeName(Long id, String name) {
        return categoryDao.changeName(id, name);
    }

    @Override
    public List<Conferenc> findConferencInIdCategory(Long id) {
        return categoryDao.findConferencInIdCategory(id);
    }

    @Override
    public List<Conferenc> findConferencInNameCategory(String name) {
        return null;
    }

    @Override
    public List<SectionConferenc> findSectionConferencInIdCategory(Long id) {
        return categoryDao.findSectionConferencInIdCategory(id);
    }

    @Override
    public List<SectionConferenc> findSectionConferencInNameCategory(String Name) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        try {
            return categoryDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Category> findId(Long id) {
        return categoryDao.readById(id);
    }

    @Override
    public boolean remove(Long id) {
        return categoryDao.delete(id);
    }
}
