package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.ApplicationDaoImpl;
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
     * Find count all application by user
     *
     * @param id id user
     * @return count applications
     */
    Long findCountAllApplicationByUser(Long id) throws DaoException;

    /**
     * Find count all application
     *
     * @return count applications
     */
    Long findCountAllApplication() throws DaoException;

    /**
     * Create application
     *
     * @param idAccount          id account
     * @param idSectionConferenc id section conferenc for application
     * @param idResultSection    id result section
     * @return boolean result of operation
     */
    boolean create(Long idAccount, Long idSectionConferenc, Long idResultSection) throws DaoException;

    /**
     * Find for duplicate application
     *
     * @param idAccount          id account
     * @param idSectionConferenc id section conferenc for application
     * @param idResultSection    id result section
     * @return boolean result of operation
     */
    boolean findForDuplicateApplication(Long idAccount, Long idSectionConferenc,
                                        Long idResultSection) throws DaoException;

    /**
     * Change status application after section conferenc
     *
     * @param idSectionConferenc id section conferenc for application
     * @return boolean result of operation
     */
    boolean changeStatusApplicationAfterUpdateSectionConferenc(Long idSectionConferenc) throws DaoException;

    /**
     * Update status application by id
     *
     * @param idApplication   id application
     * @param idResultSection result section
     * @return boolean result of operation
     */
    boolean updateIdStatusApplication(Long idApplication, Long idResultSection) throws DaoException;

    /**
     * Read all application
     *
     * @param limit number of rows in the selection
     * @param offset offset from the beginning of the selection
     * @return List application
     */
    List<Application> readAll(Long limit, Long offset) throws EntityExtractionFailedException, DaoException;

    /**
     * Read apllication by id
     *
     * @param id id application
     * @return Application
     */
    Optional<Application> readById(Long id) throws DaoException;

    /**
     * Find application by id account
     *
     * @param id id application
     * @param limit number of rows in the selection
     * @param offset offset from the beginning of the selection
     * @return List application
     */
    List<Application> findAccountIdByApplication(Long id, Long limit, Long offset) throws DaoException;

    /**
     * Find application by status result
     *
     * @param idStatus id status application
     * @return List application
     */
    List<Application> findByStatusResult(Long idStatus) throws DaoException;

    /**
     * Remove application by id
     *
     * @param id id application
     * @return boolean result of operation
     */
    boolean delete(Long id) throws DaoException;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    static ApplicationDao instance() {
        return ApplicationDaoImpl.getInstance();
    }
}
