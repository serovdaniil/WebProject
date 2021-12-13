package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodSectionConferencDaoImpl;
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
    boolean create(String name, String description, Long idConferenc);

    /**
     * Update description by id section conferenc
     *
     * @param id          id for section conferenc
     * @param description description for section conferenc
     * @return boolean result of operation
     */
    boolean updateDescription(Long id, String description);

    /**
     * Read all section conferenc
     *
     * @return List section conferenc
     */
    List<SectionConferenc> readAll() throws EntityExtractionFailedException;

    /**
     * Find section conferenc by id
     *
     * @param id id for section conferenc
     * @return Section conferenc
     */
    Optional<SectionConferenc> readById(Long id);

    /**
     * Find section conferenc by name
     *
     * @param name name for section conferenc
     * @return List section conferenc
     */
    List<SectionConferenc> findByName(String name);

    /**
     * Find section conferenc by id conferenc
     *
     * @param id id for conferenc
     * @return List section conferenc
     */
    List<SectionConferenc> findSectionConferencesInConferencById(Long id);

    /**
     * Remove section conferenc by id
     *
     * @param id id for section conferenc
     * @return boolean result of operation
     */
    boolean delete(Long id);

    /**
     * Change status by id section conferenc
     *
     * @param idSectionConferenc id for section conferenc
     * @param idStatus           id status for section conferenc
     * @return boolean result of operation
     */
    boolean changeStatus(Long idSectionConferenc, Long idStatus);

    /**
     * Change status after update status conferenc by id conferenc
     *
     * @param idConferenc id for conferenc
     * @return boolean result of operation
     */
    boolean changeStatusAfterUpdateConferenc(Long idConferenc);

    /**
     * Gets instance.
     *
     * @return the instance
     */
    static SectionConferencDao instance() {
        return MethodSectionConferencDaoImpl.getInstance();
    }
}
