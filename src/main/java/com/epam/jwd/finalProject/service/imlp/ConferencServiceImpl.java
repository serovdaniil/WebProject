package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodConferencDaoImpl;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.validator.ConferencDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ConferencServiceImpl implements ConferencService {

    private final MethodConferencDaoImpl conferencDao;
    private static final Logger LOG = LogManager.getLogger(ConferencServiceImpl.class);
    private final ConferencDataValidator conferencDataValidator = new ConferencDataValidator().getInstance();


    public ConferencServiceImpl(MethodConferencDaoImpl conferencDao) {
        this.conferencDao = conferencDao.getInstance();
    }

    @Override
    public boolean updateDescription(Long id, String description) throws ValidationException {
        LOG.debug("Service: Updating description in conferenc started.");
        if (!conferencDataValidator.isIdValid(id) || !conferencDataValidator.isDescriptionValid(description)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Updating description in conferenc finished.");
        return conferencDao.updateDescription(id, description);
    }

    @Override
    public List<Conferenc> findByName(String name) throws ValidationException {
        LOG.debug("Service: Search conferenc by name started.");
        if (!conferencDataValidator.isNameValid(name)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Search conferenc by name finished.");
        return conferencDao.findByName(name);
    }

    @Override
    public List<Conferenc> findAll() {
        LOG.debug("Service: Readind all conferences started1.");
        try {
            return conferencDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }
        LOG.debug("Service: Readind all conferences finished.");
        return Collections.emptyList();
    }

    @Override
    public boolean create(String name, String description, Long idCategory) throws ValidationException {
        LOG.debug("Service: Creating conferenc started.");
        if (!conferencDataValidator.isNameValid(name) ||
                !conferencDataValidator.isDescriptionValid(description) ||
                !conferencDataValidator.isIdValid(idCategory)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Creating conferenc finished.");
        return conferencDao.create(name, description, idCategory);
    }

    @Override
    public Optional<Conferenc> findId(Long id) throws ValidationException {
        LOG.debug("Service: Search conferenc by id started.");
        if (!conferencDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Search conferenc by id finished.");
        return conferencDao.readById(id);
    }

    @Override
    public boolean remove(Long id) throws ValidationException {
        LOG.debug("Service: Removing conferenc by id started.");
        if (!conferencDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Removing conferenc by id finished.");
        return conferencDao.delete(id);
    }
}
