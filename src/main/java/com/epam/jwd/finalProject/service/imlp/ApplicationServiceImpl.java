package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.ApplicationDaoImpl;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.service.api.ApplicationService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.validator.ApplicationDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Daniil Serov
 * @see ApplicationDaoImpl
 */
public class ApplicationServiceImpl implements ApplicationService {
    /**
     * Dao for this service
     */
    private final ApplicationDaoImpl applicationDao;

    /**
     * Logger for this service
     */
    private static final Logger LOG = LogManager.getLogger(ApplicationServiceImpl.class);

    /**
     * Validator for this service
     */
    private final ApplicationDataValidator applicationDataValidator = new ApplicationDataValidator().getInstance();

    /**
     * Constructor - creating a new object
     *
     * @param applicationDao dao for this service
     */
    public ApplicationServiceImpl(ApplicationDaoImpl applicationDao) {
        this.applicationDao = applicationDao.getInstance();
    }

    /**
     * Change status application
     *
     * @param idSectionConferenc id section conferenc
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean changeStatusApplicationAfterUpdateSectionConferenc(Long idSectionConferenc) throws ValidationException {
        LOG.debug("Service: Change status after update status section conferenc started.");
        if (!applicationDataValidator.isIdValid(idSectionConferenc)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Change status after update status section conferenc   finished.");
        return applicationDao.changeStatusApplicationAfterUpdateSectionConferenc(idSectionConferenc);
    }

    /**
     * Create application
     *
     * @param idSectionConferenc id section conferenc
     * @param idUser             id user
     * @param idResultSection id result section
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean create(Long idUser, Long idSectionConferenc, Long idResultSection) throws ValidationException {
        LOG.debug("Service: Creating application started.");
        if (!applicationDataValidator.isIdValid(idUser) ||
                !applicationDataValidator.isIdValid(idSectionConferenc) ||
                !applicationDataValidator.isIdValid(idResultSection)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Creating application finished.");
        return applicationDao.create(idUser, idSectionConferenc, idResultSection);
    }
    /**
     * Update id status application
     *
     * @param idApplication id application
     * @param resultSection name result section
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean updateIdStatusApplication(Long idApplication, String resultSection) throws ValidationException {
        LOG.debug("Service: Updating status result for application started.");
        LOG.info(resultSection);
        final Long idResult = resultSection(resultSection);
        LOG.info(idResult);
        if (!applicationDataValidator.isIdValid(idApplication) ||
                !applicationDataValidator.isIdValid(idResult)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Updating status result for application  finished.");
        return applicationDao.updateIdStatusApplication(idApplication, idResult);
    }
    /**
     * Find application for user by id
     *
     * @param id id application
     * @return List applications
     * @throws ValidationException if there are validation problems
     */
    @Override
    public List<Application> findAccountIdByApplication(Long id) throws ValidationException {
        LOG.debug("Service: Find applications by id user started.");
        if (!applicationDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Find applications by id user finished.");
        return applicationDao.findAccountIdByApplication(id);
    }
    /**
     * Find application by status result
     *
     * @param nameStatus name status result
     * @return List applications
     * @throws ValidationException if there are validation problems
     */
    @Override
    public List<Application> findByStatusResult(String nameStatus) throws ValidationException {
        LOG.debug("Service: Find applications by id status result started.");
        final Long idStatus = resultSection(nameStatus);
        if (!applicationDataValidator.isIdValid(idStatus)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Find applications by id status result finished.");
        return applicationDao.findByStatusResult(idStatus);
    }
    /**
     * Find all applications
     *
     * @return List applications
     */
    @Override
    public List<Application> findAll() {
        LOG.debug("Service: Reading all applications started.");
        try {
            return applicationDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }
        LOG.debug("Service: Reading all applications finished.");
        return Collections.emptyList();
    }
    /**
     * Find application by id
     *
     * @param id id application
     * @return Applications
     * @throws ValidationException if there are validation problems
     */
    @Override
    public Optional<Application> findId(Long id) throws ValidationException {
        LOG.debug("Service: Finding application by id started.");
        if (!applicationDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Finding application by id finished.");
        return applicationDao.readById(id);
    }
    /**
     * Remove application for user by id
     *
     * @param id id application
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean remove(Long id) throws ValidationException {
        LOG.debug("Service: Removing application started.");
        if (!applicationDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug(id);
        LOG.debug("Service: Removing application finished.");
        return applicationDao.delete(id);
    }
    /**
     * Check result section
     *
     * @param resultSection name result section
     * @return Long id
     */
    private Long resultSection(String resultSection) {
        Long idResult = null;
        if ((resultSection.equals("Activate")) || (resultSection.equals("Создана")) ||
                (resultSection.equals("Créé")) || (resultSection.equals("Створаны"))) {
            idResult = (long) 1;
        }
        if ((resultSection.equals("Waiting")) || (resultSection.equals("В ожидании")) ||
                (resultSection.equals("En attendant")) || (resultSection.equals("У чаканні"))) {
            idResult = (long) 2;
        }
        if ((resultSection.equals("Completed")) || (resultSection.equals("Завершена")) ||
                (resultSection.equals("Achevé")) || (resultSection.equals("Завершаны"))) {
            idResult = (long) 3;
        }
        if ((resultSection.equals("Deleted")) || (resultSection.equals("Удалена")) ||
                (resultSection.equals("Distant")) || (resultSection.equals("Выдалены"))) {
            idResult = (long) 4;
        }
        return idResult;
    }
}
