package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodApplicationDaoImpl;
import com.epam.jwd.finalProject.model.Application;

import java.util.List;
import java.util.Optional;

/**
 * The interface application dao
 *
 * @author Daniil Serov
 */
public interface ApplicationDao {
    /**
     * Create application
     *
     * @param idAccount          id account
     * @param idSectionConferenc id section conferenc for application
     * @param idResultSection    id resulr section
     * @return boolean result of operation
     */
    boolean create(Long idAccount, Long idSectionConferenc, Long idResultSection);

    /**
     * Change status application after section conferenc
     *
     * @param idSectionConferenc id section conferenc for application
     * @return boolean result of operation
     */
    boolean changeStatusApplicationAfterUpdateSectionConferenc(Long idSectionConferenc);

    /**
     * Update status application by id
     *
     * @param idApplication   id application
     * @param idResultSection result section
     * @return boolean result of operation
     */
    boolean updateIdStatusApplication(Long idApplication, Long idResultSection);

    /**
     * Read all application
     *
     * @return List application
     */
    List<Application> readAll() throws EntityExtractionFailedException;

    /**
     * Read apllication by id
     *
     * @param id id application
     * @return Application
     */
    Optional<Application> readById(Long id);

    /**
     * Find application by id account
     *
     * @param id id application
     * @return List application
     */
    List<Application> findAccountIdByApplication(Long id);

    /**
     * Find application by status result
     *
     * @param idStatus id status application
     * @return List application
     */
    List<Application> findByStatusResult(Long idStatus);

    /**
     * Remove application by id
     *
     * @param id id application
     * @return boolean result of operation
     */
    boolean delete(Long id);

    /**
     * Gets instance.
     *
     * @return the instance
     */
    static ApplicationDao instance() {
        return MethodApplicationDaoImpl.getInstance();
    }
}
