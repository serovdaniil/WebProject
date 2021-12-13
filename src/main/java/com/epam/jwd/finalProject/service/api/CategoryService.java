package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;

/**
 * The interface category service
 *
 * @author Daniil Serov
 */
public interface CategoryService extends EntityService<Category> {
    /**
     * Create category
     *
     * @param name name for new category
     * @return boolean result of operation
     */
    boolean create(String name) throws ValidationException;
    /**
     * Change name category
     *
     * @param id id for category
     * @param name new name for category
     * @return boolean result of operation
     */
    boolean changeName(Long id, String name) throws ValidationException;
    /**
     * Find conferences in category
     *
     * @param id id for category
     * @return List conferences
     */
    List<Conferenc> findConferencInIdCategory(Long id) throws ValidationException;

    /**
     * Find sectionconferences in category
     *
     * @param id id for category
     * @return List section conferences
     */
    List<SectionConferenc> findSectionConferencInIdCategory(Long id) throws ValidationException;

}
