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
     * @return List section conferenc
     */
    List<SectionConferenc> readAll() throws EntityExtractionFailedException, DaoException;

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
