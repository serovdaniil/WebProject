package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;

/**
 * The interface entity service
 *
 * @author Daniil Serov
 */
public interface SectionConferencService extends EntityService<SectionConferenc> {

    /**
     * Create section conferenc
     *
     * @param name        name for new section conferenc
     * @param description description for section conferenc
     * @return boolean result of operation
     * @throws ValidationException
     */
    boolean create(String name, String description, Long idConferenc) throws ValidationException, ServiceException;

    /**
     * Find for duplicate section conferenc
     *
     * @param name        name for section conferenc
     * @param description description for section conferenc
     * @param idConferenc id conferenc for section conferenc
     * @return boolean result of operation
     */
    boolean findForDuplicateSectionConferenc(String name, String description, Long idConferenc) throws ValidationException, ServiceException;


    /**
     * Update description for section conferenc
     *
     * @param id          id for section conferenc
     * @param description description for section conferenc
     * @return boolean result of operation
     * @throws ValidationException
     */
    boolean updateDescription(Long id, String description) throws ValidationException, ServiceException;

    /**
     * Find id section conferenc by name
     *
     * @param name name for section conferenc
     * @return List section conferenc
     * @throws ValidationException
     */
    List<SectionConferenc> findByName(String name) throws ValidationException, ServiceException;

    /**
     * Find id section conferenc by id
     *
     * @param id id for section conferenc
     * @return List section conferenc
     * @throws ValidationException
     */
    List<SectionConferenc> findSectionConferencesInConferencById(Long id) throws ValidationException, ServiceException;

    /**
     * Change status for section conferenc
     *
     * @param idSectionConferenc id for section conferenc
     * @param nameStatus         status for section conferenc
     * @return boolean result of operation
     * @throws ValidationException
     */
    boolean changeStatus(Long idSectionConferenc, String nameStatus) throws ValidationException, ServiceException;

    /**
     * Change status auto after update conferenc
     *
     * @param idConferenc id for section conferenc
     * @return boolean result of operation
     * @throws ValidationException
     */
    boolean changeStatusAfterUpdateConferenc(Long idConferenc) throws ValidationException, ServiceException;
}
