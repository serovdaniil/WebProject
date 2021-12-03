package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodApplicationDaoImpl;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.service.api.ApplicationService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.validator.ApplicationDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ApplicationServiceImpl implements ApplicationService {
    private final MethodApplicationDaoImpl applicationDao;
    private static final Logger LOG = LogManager.getLogger(ApplicationServiceImpl.class);
    private final ApplicationDataValidator applicationDataValidator = new ApplicationDataValidator().getInstance();

    public ApplicationServiceImpl(MethodApplicationDaoImpl applicationDao) {
        this.applicationDao = applicationDao.getInstance();
    }

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

    @Override
    public boolean updateIdStatusApplication(Long idApplication, String resultSection) throws ValidationException {
        LOG.debug("Service: Updating status result for application started.");
        final Long idResult = resultSection(resultSection);
        if (!applicationDataValidator.isIdValid(idApplication) ||
                !applicationDataValidator.isIdValid(idResult)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Updating status result for application  finished.");
        return applicationDao.updateIdStatusApplication(idApplication, idResult);
    }

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

    @Override
    public List<Application> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public List<Application> findByLastName(String lastName) {
        return null;
    }

    @Override
    public List<Application> findByEmail(String email) {
        return null;
    }

    @Override
    public List<Application> findByLogin(String login) {
        return null;
    }

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

    @Override
    public List<Application> findByConferencId(Long idConferenc) {
        return null;
    }

    @Override
    public List<Application> findByConferencName(String nameConferenc) {
        return null;
    }

    @Override
    public List<Application> findBySectionConferencId(Long idSectionConferenc) {
        return null;
    }

    @Override
    public List<Application> findBySectionConferencName(String nameSectionConferenc) {
        return null;
    }

    @Override
    public List<Application> findByCategoryId(Long idCategory) {
        return null;
    }

    @Override
    public List<Application> findByCategoryName(String nameCategory) {
        return null;
    }

    @Override
    public List findAll() {
        LOG.debug("Service: Reading all applications started.");
        try {
            return applicationDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }
        LOG.debug("Service: Reading all applications finished.");
        return Collections.emptyList();
    }

    @Override
    public Optional findId(Long id) throws ValidationException {
        LOG.debug("Service: Finding application by id started.");
        if (!applicationDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Finding application by id finished.");
        return applicationDao.readById(id);
    }

    @Override
    public boolean remove(Long id) throws ValidationException {
        LOG.debug("Service: Removing application started.");
        if (!applicationDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Removing application finished.");
        return applicationDao.delete(id);
    }

    private Long resultSection(String resultSection) {
        final Long idResult;
        switch (resultSection) {
            case "Open": {
                idResult = (long) 1;
                break;
            }
            case "Waiting": {
                idResult = (long) 2;
                break;
            }
            case "Completed": {
                idResult = (long) 3;
                break;
            }
            default: {
                idResult = (long) 4;
                break;
            }
        }
        return idResult;
    }
}
