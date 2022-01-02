package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.ConferencDaoImpl;
import com.epam.jwd.finalProject.model.Conferenc;

import java.util.List;
import java.util.Optional;

/**
 * The interface conferenc dao
 *
 * @author Daniil Serov
 */
public interface ConferencDao {

    /**
     * Create conferenc
     *
     * @param name        name for new conferenc
     * @param description description for new conferenc
     * @param idCategory  id category for new conferenc
     * @return boolean result of operation
     */
    boolean create(String name,String description,Long idCategory);

    /**
     * Update description for conferenc
     *
     * @param id id conferenc
     * @param description new description for conferenc
     * @return boolean result of operation
     */
    boolean updateDescription(Long id, String description);

    /**
     * Read all active status conferenc
     *
     * @return List Conferenc
     */
    List<Conferenc> readAllActive() throws EntityExtractionFailedException;

    /**
     * Read all conferenc
     *
     * @return List Conferenc
     */
    List<Conferenc> readAll() throws EntityExtractionFailedException;

    /**
     * Read conferenc by id
     *
     * @param id id conferenc
     * @return Conferenc
     */
    Optional<Conferenc> readById(Long id);

    /**
     * Find conferences by name
     *
     * @param name name conferenc
     * @return List Conferenc
     */
    List<Conferenc> findByName(String name);

    /**
     * Remove conferenc by id
     *
     * @param id id conferenc
     * @return boolean result of operation
     */
    boolean delete(Long id);

    /**
     * Change status conferenc by id
     *
     * @param idConferenc id conferenc
     * @param idStatus status conferenc
     * @return boolean result of operation
     */
    boolean changeStatus(Long idConferenc,Long idStatus);

    /**
     * Gets instance.
     *
     * @return the instance
     */
    static ConferencDao instance() {
        return ConferencDaoImpl.getInstance();
    }
}
