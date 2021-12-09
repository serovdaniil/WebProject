package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodCategoryDaoImpl;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.CategoryService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.validator.CategoryDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    private final MethodCategoryDaoImpl categoryDao;
    private static final Logger LOG = LogManager.getLogger(CategoryServiceImpl.class);
    private final CategoryDataValidator categoryDataValidator=new CategoryDataValidator().getInstance();

    public CategoryServiceImpl(MethodCategoryDaoImpl categoryDao) {
        this.categoryDao = categoryDao.getInstance();
    }

    @Override
    public boolean create(String name) throws ValidationException {
        LOG.debug("Service: Creating category started.");
        if (!categoryDataValidator.isNameValid(name)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Creating category finished.");
        return categoryDao.create(name);
    }

    @Override
    public boolean changeName(Long id, String name) throws ValidationException {
        LOG.debug("Service: Changing the category name started.");
        if (!categoryDataValidator.isNameValid(name) || !categoryDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Changing the category name finished.");
        return categoryDao.changeName(id, name);
    }

    @Override
    public List<Conferenc> findConferencInIdCategory(Long id) throws ValidationException {
        LOG.debug("Service: Search for categories in conferences started.");
        if (!categoryDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Search for categories in conferences finished.");
        return categoryDao.findConferencInIdCategory(id);
    }

    @Override
    public List<SectionConferenc> findSectionConferencInIdCategory(Long id) throws ValidationException {
        LOG.debug("Service: Search for categories in section conferences started.");
        if (!categoryDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Search for categories in section conferences finished.");
        return categoryDao.findSectionConferencInIdCategory(id);
    }

    @Override
    public List<Category> findAll() {
        LOG.debug("Service: Search all categories started.");
        try {
            return categoryDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }
        LOG.debug("Service: Search all categories finished.");
        return Collections.emptyList();
    }

    @Override
    public Optional<Category> findId(Long id) throws ValidationException {
        LOG.debug("Service: Search category by id started.");
        if (!categoryDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Search category by id finished.");
        return categoryDao.readById(id);
    }

    @Override
    public boolean remove(Long id) throws ValidationException {
        LOG.debug("Service: Removing category by id started.");
        if (!categoryDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Removing category by id finished.");
        return categoryDao.delete(id);
    }
}
