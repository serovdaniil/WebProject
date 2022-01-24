package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.SectionConferencDaoImpl;
import com.epam.jwd.finalProject.model.SectionConferenc;

import java.util.List;
import java.util.Optional;

/**
 * The interface section conferenc dao
 *
 * @author Daniil Serov
 */
public interface SectionConferencDao {

    /**
     * Create section conferenc
     *
     * @param name        name for new section conferenc
     * @param description description for new section conferenc
     * @param idConferenc id conferenc for new section conferenc
     * @return boolean result of operation
     */
    boolean create(String name, String description, Long idConferenc) throws DaoException;

    /**
     * Find for duplicate section conferenc
     *
     * @param name        name for section conferenc
     * @param description description for section conferenc
     * @param idConferenc id conferenc for section conferenc
     * @return boolean result of operation
     */

    boolean findForDuplicateSectionConferenc(String name, String description, Long idConferenc) throws DaoException;

    /**
     * Update description by id section conferenc
     *
     * @param id          id for section conferenc
     * @param description description for section conferenc
     * @return boolean result of operation
     */
    boolean updateDescription(Long id, String description) throws DaoException;

    /**
     * Read all section conferenc
     *
     * @param limit  number of rows in the selection
     * @param offset offset from the beginning of the selection
     * @return List section conferenc
     */
    List<SectionConferenc> readAll(Long limit, Long offset) throws EntityExtractionFailedException, DaoException;

    /**
     * Find section conferenc by id
     *
     * @param id id for section conferenc
     * @return Section conferenc
     */
    Optional<SectionConferenc> readById(Long id) throws DaoException;

    /**
     * Find section conferenc by name
     *
     * @param name name for section conferenc
     * @return List section conferenc
     */
    List<SectionConferenc> findByName(String name) throws DaoException;

    /**
     * Find section conferenc by id conferenc with pagination
     *
     * @param id     id for conferenc
     * @param limit  number of rows in the selection
     * @param offset offset from the beginning of the selection
     * @return List section conferenc
     */
    List<SectionConferenc> findSectionConferencesInConferencByIdWithPagination(Long id, Long limit,
                                                                               Long offset) throws DaoException;

    /**
     * Find count all section conferenc
     *
     * @param id id for conferenc
     * @return count section conferences
     */
    Long findCountAllSectionConferencInConferenc(Long id) throws DaoException;

    /**
     * Find count all section conferenc
     *
     * @return count section conferences
     */
    Long findCountAllSectionConferenc() throws DaoException;

    /**
     * Find section conferenc by id conferenc
     *
     * @param id id for conferenc
     * @return List section conferenc
     */
    List<SectionConferenc> findSectionConferencesInConferencById(Long id) throws DaoException;

    /**
     * Remove section conferenc by id
     *
     * @param id id for section conferenc
     * @return boolean result of operation
     */
    boolean delete(Long id) throws DaoException;

    /**
     * Change status by id section conferenc
     *
     * @param idSectionConferenc id for section conferenc
     * @param idStatus           id status for section conferenc
     * @return boolean result of operation
     */
    boolean changeStatus(Long idSectionConferenc, Long idStatus) throws DaoException;

    /**
     * Change status after update status conferenc by id conferenc
     *
     * @param idConferenc id for conferenc
     * @return boolean result of operation
     */
    boolean changeStatusAfterUpdateConferenc(Long idConferenc) throws DaoException;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    static SectionConferencDao instance() {
        return SectionConferencDaoImpl.getInstance();
    }
}
