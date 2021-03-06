package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;
/**
 * The interface conferenc service
 *
 * @author Daniil Serov
 */
public interface ConferencService extends EntityService<Conferenc> {

    /**
     * Find all Conferenc with pagination
     *
     * @param pageNumber selected page
     * @return List Conferenc
     */
    List<Conferenc> findAllConferencLimitOffsetPagination(Long pageNumber)throws ValidationException,
            ServiceException;

    /**
     * Find count all conferenc by active status
     *
     * @return count conferences
     */
    Long findCountAllConferencByActiveStatus() throws ServiceException;

    /**
     * Find count all conferenc
     *
     * @return count conferences
     */
    Long findCountAllConferenc() throws ServiceException;

    /**
     * Create conferenc
     *
     * @param name name for new conferenc
     * @param description description for new conferenc
     * @param idCategory category for conferenc
     * @return boolean result of operation
     */
    boolean create(String name, String description,Long idCategory) throws ValidationException, ServiceException;

    /**
     * Find for duplicate conferenc
     *
     * @param name        name for new conferenc
     * @param description description for new conferenc
     * @param idCategory  id category for new conferenc
     * @return boolean result of operation
     */
    boolean findForDuplicateConferenc(String name,String description,Long idCategory)  throws ValidationException,
            ServiceException;

    /**
     * Update description for conferenc
     *
     * @param id id conferenc
     * @param description description for new conferenc
     * @return boolean result of operation
     */
    boolean updateDescription(Long id, String description) throws ValidationException, ServiceException;

    /**
     * Find conferences by name
     *
     * @param name name conferenc
     * @return List conferenc
     */
    List<Conferenc> findByName(String name) throws ValidationException, ServiceException;

    /**
     * Find all status
     *
     * @param pageNumber selected page
     * @return List conferenc
     */
    List<Conferenc> findAllStatus(Long pageNumber) throws ServiceException;

    /**
     * Change status for conferenc
     *
     * @param idConferenc id conferenc
     * @param nameStatus name status for conferenc
     * @return boolean result of operation
     */
    boolean changeStatus(Long idConferenc,String nameStatus) throws ValidationException, ServiceException;
}
