package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodCategoryDaoImpl;
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
    boolean create(String name);

    /**
     * Change name for category
     *
     * @param id id category
     * @param name new name for category
     * @return boolean result of operation
     */
    boolean changeName(Long id, String name);

    /**
     * Find all categories
     *
     * @return List category
     */
    List<Category> findAll() throws EntityExtractionFailedException;

    /**
     * Find category by id
     *
     * @param id id category
     * @return Category
     */
    Optional<Category> findById(Long id);

    /**
     * Find conferences by id category
     *
     * @param id id category
     * @return List conferences
     */
    List<Conferenc> findConferencInIdCategory(Long id);

    /**
     * Find section conferences by id category
     *
     * @param id id category
     * @return List section conferences
     */
    List<SectionConferenc> findSectionConferencInIdCategory(Long id);

    /**
     * Remove category by id
     *
     * @param id id category
     * @return boolean result of operation
     */
    boolean delete(Long id);
    /**
     * Gets instance.
     *
     * @return the instance
     */
    static CategoryDao instance() {
        return MethodCategoryDaoImpl.getInstance();
    }
}
