package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;

/**
 * The interface application service
 *
 * @author Daniil Serov
 */
public interface ApplicationService extends EntityService<Application> {

    /**
     * Create application
     *
     * @param idAccount          id account
     * @param idSectionConferenc id for section conferenc
     * @param idResultSection    id for result section
     * @return boolean result of operation
     */
    boolean create(Long idAccount, Long idSectionConferenc, Long idResultSection) throws ValidationException;

    /**
     * Update description application
     *
     * @param idApplication id for application
     * @param resultSection string for result section
     * @return boolean result of operation
     */
    boolean updateIdStatusApplication(Long idApplication, String resultSection) throws ValidationException;

    /**
     * Find application for account by id
     *
     * @param id id for application
     * @return List application
     */
    List<Application> findAccountIdByApplication(Long id) throws ValidationException;

    /**
     * Find application by status result
     *
     * @param nameStatus name status for application
     * @return List application
     */
    List<Application> findByStatusResult(String nameStatus) throws ValidationException;

    /**
     * Change status application
     *
     * @param idSectionConferenc id section conferenc for application
     * @return boolean result of operation
     */
    boolean changeStatusApplicationAfterUpdateSectionConferenc(Long idSectionConferenc) throws ValidationException;
}
