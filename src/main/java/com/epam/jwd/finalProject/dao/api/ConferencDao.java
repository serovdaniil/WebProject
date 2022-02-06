package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.DaoException;
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
     * Find all Conferenc with pagination
     *
     * @param limit number of rows in the selection
     * @param offset offset from the beginning of the selection
     * @return List Conferenc
     */
    List<Conferenc> findAllConferencLimitOffsetPagination(Long limit,Long offset) throws DaoException;

    /**
     * Find count all conferenc by active status
     *
     * @return count conferences
     */
    Long findCountAllConferencByActiveStatus() throws DaoException;

    /**
     * Find count all conferenc
     *
     * @return count conferences
     */
    Long findCountAllConferenc() throws DaoException;

    /**
     * Create conferenc
     *
     * @param name        name for new conferenc
     * @param description description for new conferenc
     * @param idCategory  id category for new conferenc
     * @return boolean result of operation
     */
    boolean create(String name,String description,Long idCategory) throws DaoException;

    /**
     * Find for duplicate conferenc
     *
     * @param name        name for new conferenc
     * @param description description for new conferenc
     * @param idCategory  id category for new conferenc
     * @return boolean result of operation
     */
    boolean findForDuplicateConferenc(String name,String description,Long idCategory) throws DaoException;

    /**
     * Update description for conferenc
     *
     * @param id id conferenc
     * @param description new description for conferenc
     * @return boolean result of operation
     */
    boolean updateDescription(Long id, String description) throws DaoException;

    /**
     * Read all conferenc
     *
     * @param limit number of rows in the selection
     * @param offset offset from the beginning of the selection
     * @return List Conferenc
     */
    List<Conferenc> readAll(Long limit,Long offset) throws EntityExtractionFailedException, DaoException;

    /**
     * Read conferenc by id
     *
     * @param id id conferenc
     * @return Conferenc
     */
    Optional<Conferenc> readById(Long id) throws DaoException;

    /**
     * Find conferences by name
     *
     * @param name name conferenc
     * @return List Conferenc
     */
    List<Conferenc> findByName(String name) throws DaoException;

    /**
     * Remove conferenc by id
     *
     * @param id id conferenc
     * @return boolean result of operation
     */
    boolean delete(Long id) throws DaoException;

    /**
     * Change status conferenc by id
     *
     * @param idConferenc id conferenc
     * @param idStatus status conferenc
     * @return boolean result of operation
     */
    boolean changeStatus(Long idConferenc,Long idStatus) throws DaoException;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    static ConferencDao instance() {
        return ConferencDaoImpl.getInstance();
    }
}
