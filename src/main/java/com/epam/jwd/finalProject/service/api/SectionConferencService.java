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
     * Find count all section conferenc
     *
     * @param id id conferenc for section conferenc
     * @return count section conferences
     */
    Long findCountAllSectionConferencInConferenc(Long id) throws ServiceException, ValidationException;

    /**
     * Find count all section conferenc
     *
     * @return count section conferences
     */
    Long findCountAllSectionConferenc() throws ServiceException;

    /**
     * Find section conferenc by id conferenc with pagination
     *
     * @param id         id for conferenc
     * @param pageNumber selected page
     * @return List section conferenc
     */
    List<SectionConferenc> findSectionConferencesInConferencByIdWithPagination(Long id, Long pageNumber)
            throws ValidationException, ServiceException;

    /**
     * Create section conferenc
     *
     * @param name        name for new section conferenc
     * @param description description for section conferenc
     * @return boolean result of operation
     * @throws ValidationException exception
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
    boolean findForDuplicateSectionConferenc(String name, String description, Long idConferenc)
            throws ValidationException, ServiceException;


    /**
     * Update description for section conferenc
     *
     * @param id          id for section conferenc
     * @param description description for section conferenc
     * @return boolean result of operation
     * @throws ValidationException exception
     */
    boolean updateDescription(Long id, String description) throws ValidationException, ServiceException;

    /**
     * Find id section conferenc by name
     *
     * @param name name for section conferenc
     * @return List section conferenc
     * @throws ValidationException exception
     */
    List<SectionConferenc> findByName(String name) throws ValidationException, ServiceException;

    /**
     * Find id section conferenc by id
     *
     * @param id id for section conferenc
     * @return List section conferenc
     * @throws ValidationException exception
     */
    List<SectionConferenc> findSectionConferencesInConferencById(Long id) throws ValidationException,
            ServiceException;

    /**
     * Change status for section conferenc
     *
     * @param idSectionConferenc id for section conferenc
     * @param nameStatus         status for section conferenc
     * @return boolean result of operation
     * @throws ValidationException exception
     */
    boolean changeStatus(Long idSectionConferenc, String nameStatus) throws ValidationException, ServiceException;

    /**
     * Change status auto after update conferenc
     *
     * @param idConferenc id for section conferenc
     * @return boolean result of operation
     * @throws ValidationException exception
     */
    boolean changeStatusAfterUpdateConferenc(Long idConferenc) throws ValidationException, ServiceException;
}
