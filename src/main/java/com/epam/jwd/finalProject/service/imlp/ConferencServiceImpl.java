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

/**
 * @author Daniil Serov
 * @see MethodConferencDaoImpl
 */
public class ConferencServiceImpl implements ConferencService {
    /**
     * Dao for this service
     */
    private final MethodConferencDaoImpl conferencDao;
    /**
     * Logger for this service
     */
    private static final Logger LOG = LogManager.getLogger(ConferencServiceImpl.class);
    /**
     * Validator for this service
     */
    private final ConferencDataValidator conferencDataValidator = new ConferencDataValidator().getInstance();

    /**
     * Constructor - creating a new object
     *
     * @param conferencDao dao for this service
     */
    public ConferencServiceImpl(MethodConferencDaoImpl conferencDao) {
        this.conferencDao = conferencDao.getInstance();
    }

    /**
     * Change status conferenc
     *
     * @param idConferenc id conferenc
     * @param nameStatus  name status
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean changeStatus(Long idConferenc, String nameStatus) throws ValidationException {
        LOG.debug("Service: Change status of conferenc started.");
        final Long idStatus = resultSection(nameStatus);
        if (!conferencDataValidator.isIdValid(idConferenc) || !conferencDataValidator.isIdValid(idStatus)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Change status of conferenc finished.");
        return conferencDao.changeStatus(idConferenc, idStatus);
    }

    /**
     * Update description conferenc
     *
     * @param id          id conferenc
     * @param description description conferenc
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
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

    /**
     * Find conferenc by name
     *
     * @param name name conferenc
     * @return List conferences
     * @throws ValidationException if there are validation problems
     */
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

    /**
     * Find conferences by status
     *
     * @return List conferences
     */
    @Override
    public List<Conferenc> findAllStatus() {
        LOG.debug("Service: Readind all conferences started .");
        try {
            return conferencDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }
        LOG.debug("Service: Readind all  conferences finished.");
        return Collections.emptyList();
    }

    /**
     * Find all conferences
     *
     * @return List conferences
     */
    @Override
    public List<Conferenc> findAll() {
        LOG.debug("Service: Readind all active conferences started .");
        try {
            return conferencDao.readAllActive();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }
        LOG.debug("Service: Readind all active conferences finished.");
        return Collections.emptyList();
    }

    /**
     * Create conferenc
     *
     * @param name        name conferenc
     * @param description description conferenc
     * @param idCategory id category
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
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

    /**
     * Find conferenc by id
     *
     * @param id id conferenc
     * @return Conferenc
     * @throws ValidationException if there are validation problems
     */
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

    /**
     * Remove conferenc by id
     *
     * @param id id conferenc
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
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

    /**
     * Check result section
     *
     * @param resultSection name result section
     * @return Long id
     */
    private Long resultSection(String resultSection) {
        Long idResult = null;

        if ((resultSection.equals("Active")) || (resultSection.equals("Активная")) || (resultSection.equals("Actif")) || (resultSection.equals("Актыўны"))) {
            idResult = (long) 1;
        }

        if ((resultSection.equals("DELETE")) || (resultSection.equals("Удаленная")) || (resultSection.equals("Distant")) || (resultSection.equals("Выдалены"))) {
            idResult = (long) 2;
        }
        return idResult;
    }
}
