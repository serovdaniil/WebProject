package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.CategoryDaoImpl;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.CategoryService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.validator.CategoryDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Daniil Serov
 * @see CategoryDaoImpl
 */
public class CategoryServiceImpl implements CategoryService {
    /**
     * Dao for this service
     */
    private final CategoryDaoImpl categoryDao;
    /**
     * Logger for this service
     */
    private static final Logger LOG = LogManager.getLogger(CategoryServiceImpl.class);
    /**
     * Validator for this service
     */
    private final CategoryDataValidator categoryDataValidator = new CategoryDataValidator().getInstance();

    /**
     * Constructor - creating a new object
     *
     * @param categoryDao dao for this service
     */
    public CategoryServiceImpl(CategoryDaoImpl categoryDao) {
        this.categoryDao = categoryDao.getInstance();
    }

    /**
     * Create category
     *
     * @param name name category
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean create(String name) throws ValidationException, ServiceException {
        try {
            if (!categoryDataValidator.isNameValid(name)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return categoryDao.create(name);
        } catch (
                DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find for duplicate category
     *
     * @param name name for new category
     * @return boolean result of operation
     */
    @Override
    public boolean findForDuplicateCategory(String name) throws ValidationException, ServiceException {
        try {
            if (!categoryDataValidator.isNameValid(name)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return categoryDao.findForDuplicateCategory(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Change name category
     *
     * @param id   id category
     * @param name new name category
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean changeName(Long id, String name) throws ValidationException, ServiceException {
        try {
            if (!categoryDataValidator.isNameValid(name) || !categoryDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return categoryDao.changeName(id, name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find conferences in category by id
     *
     * @param id id category
     * @return List conferences
     * @throws ValidationException if there are validation problems
     */
    @Override
    public List<Conferenc> findConferencInIdCategory(Long id) throws ValidationException, ServiceException {
        try {
            if (!categoryDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return categoryDao.findConferencInIdCategory(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find section conferences in category by id
     *
     * @param id id category
     * @return List section conferences
     * @throws ValidationException if there are validation problems
     */
    @Override
    public List<SectionConferenc> findSectionConferencInIdCategory(Long id) throws ValidationException, ServiceException {
        try {
            if (!categoryDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return categoryDao.findSectionConferencInIdCategory(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find all categories
     *
     * @return List categories
     */
    @Override
    public List<Category> findAll() throws ServiceException {
        try {
            try {
                return categoryDao.findAll();
            } catch (EntityExtractionFailedException e) {
                e.printStackTrace();
            }
            return Collections.emptyList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find category by id
     *
     * @param id id category
     * @return category
     * @throws ValidationException if there are validation problems
     */
    @Override
    public Optional<Category> findId(Long id) throws ValidationException, ServiceException {
        try {
            if (!categoryDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return categoryDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Remove category by id
     *
     * @param id id category
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean remove(Long id) throws ValidationException, ServiceException {
        try {
            if (!categoryDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return categoryDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
