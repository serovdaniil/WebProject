package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.CategoryDaoImpl;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;

import java.util.List;
import java.util.Optional;

/**
 * The interface category dao
 *
 * @author Daniil Serov
 */
public interface CategoryDao {

    /**
     * Create category
     *
     * @param name name for new category
     * @return boolean result of operation
     */
    boolean create(String name) throws DaoException;

    /**
     * Change name for category
     *
     * @param id id category
     * @param name new name for category
     * @return boolean result of operation
     */
    boolean changeName(Long id, String name) throws DaoException;

    /**
     * Find all categories
     *
     * @return List category
     */
    List<Category> findAll() throws EntityExtractionFailedException, DaoException;

    /**
     * Find category by id
     *
     * @param id id category
     * @return Category
     */
    Optional<Category> findById(Long id) throws DaoException;

    /**
     * Find conferences by id category
     *
     * @param id id category
     * @return List conferences
     */
    List<Conferenc> findConferencInIdCategory(Long id) throws DaoException;

    /**
     * Find section conferences by id category
     *
     * @param id id category
     * @return List section conferences
     */
    List<SectionConferenc> findSectionConferencInIdCategory(Long id) throws DaoException;

    /**
     * Remove category by id
     *
     * @param id id category
     * @return boolean result of operation
     */
    boolean delete(Long id) throws DaoException;
    /**
     * Gets instance.
     *
     * @return the instance
     */
    static CategoryDao instance() {
        return CategoryDaoImpl.getInstance();
    }
}
