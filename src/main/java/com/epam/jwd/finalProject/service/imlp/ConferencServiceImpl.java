package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.ConferencDaoImpl;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.api.ConferencService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.validator.ConferencDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Daniil Serov
 * @see ConferencDaoImpl
 */
public class ConferencServiceImpl implements ConferencService {
    /**
     * Dao for this service
     */
    private final ConferencDaoImpl conferencDao;
    /**
     * Logger for this service
     */
    private static final Logger LOG = LogManager.getLogger(ConferencServiceImpl.class);
    /**
     * Validator for this service
     */
    private final ConferencDataValidator conferencDataValidator = new ConferencDataValidator().getInstance();
    /**
     * Limit for pagination
     */
    private static final Long LIMIT = (long) 5;

    /**
     * Constructor - creating a new object
     *
     * @param conferencDao dao for this service
     */
    public ConferencServiceImpl(ConferencDaoImpl conferencDao) {
        this.conferencDao = conferencDao;
    }

    /**
     * Find all Conferenc with pagination
     *
     * @param pageNumber selected page
     * @return List Conferenc
     * @throws ValidationException if there are validation problems
     */
    @Override
    public List<Conferenc> findAllConferencLimitOffsetPagination(Long pageNumber)
            throws ValidationException, ServiceException {
        try {
            if (!conferencDataValidator.isIdValid(pageNumber)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            final Long offset = LIMIT * (pageNumber - 1);
            return conferencDao.findAllConferencLimitOffsetPagination(LIMIT, offset);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find count all conferenc
     *
     * @return count conferences
     */
    @Override
    public Long findCountAllConferencByActiveStatus() throws ServiceException {
        try {
            final Long countConferenc = conferencDao.findCountAllConferencByActiveStatus();
            Long pageCount = countConferenc / LIMIT;
            if ((countConferenc - pageCount * LIMIT) > 0) {
                pageCount++;
            }
            return pageCount;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find count all conferenc
     *
     * @return count conferences
     */
    @Override
    public Long findCountAllConferenc() throws ServiceException {
        try {
            final Long countConferenc = conferencDao.findCountAllConferenc();
            Long pageCount = countConferenc / LIMIT;
            if ((countConferenc - pageCount * LIMIT) > 0) {
                pageCount++;
            }
            return pageCount;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
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
    public boolean changeStatus(Long idConferenc, String nameStatus)
            throws ValidationException, ServiceException {
        try {
            final Long idStatus = resultSection(nameStatus);
            if (!conferencDataValidator.isIdValid(idConferenc) || !conferencDataValidator.isIdValid(idStatus)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return conferencDao.changeStatus(idConferenc, idStatus);
        } catch (
                DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find for duplicate conferenc
     *
     * @param name        name for new conferenc
     * @param description description for new conferenc
     * @param idCategory  id category for new conferenc
     * @return boolean result of operation
     */
    @Override
    public boolean findForDuplicateConferenc(String name, String description, Long idCategory)
            throws ValidationException, ServiceException {
        try {
            if (!conferencDataValidator.isNameValid(name) || !conferencDataValidator.isDescriptionValid(description)
                    || !conferencDataValidator.isIdValid(idCategory)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return conferencDao.findForDuplicateConferenc(name, description, idCategory);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
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
    public boolean updateDescription(Long id, String description)
            throws ValidationException, ServiceException {
        try {
            if (!conferencDataValidator.isIdValid(id) || !conferencDataValidator.isDescriptionValid(description)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return conferencDao.updateDescription(id, description);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find conferenc by name
     *
     * @param name name conferenc
     * @return List conferences
     * @throws ValidationException if there are validation problems
     */
    @Override
    public List<Conferenc> findByName(String name) throws ValidationException, ServiceException {
        try {
            if (!conferencDataValidator.isNameValid(name)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return conferencDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find conferences by status
     *
     * @param pageNumber selected page
     * @return List conferences
     */
    @Override
    public List<Conferenc> findAllStatus(Long pageNumber) throws ServiceException {
        try {
            try {
                final Long offset = LIMIT * (pageNumber - 1);
                return conferencDao.readAll(LIMIT,offset);
            } catch (EntityExtractionFailedException e) {
                e.printStackTrace();
            }
            return Collections.emptyList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Create conferenc
     *
     * @param name        name conferenc
     * @param description description conferenc
     * @param idCategory  id category
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean create(String name, String description, Long idCategory)
            throws ValidationException, ServiceException {
        try {
            if (!conferencDataValidator.isNameValid(name) ||
                    !conferencDataValidator.isDescriptionValid(description) ||
                    !conferencDataValidator.isIdValid(idCategory)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return conferencDao.create(name, description, idCategory);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find conferenc by id
     *
     * @param id id conferenc
     * @return Conferenc
     * @throws ValidationException if there are validation problems
     */
    @Override
    public Optional<Conferenc> findId(Long id) throws ValidationException, ServiceException {
        try {
            if (!conferencDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return conferencDao.readById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Remove conferenc by id
     *
     * @param id id conferenc
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean remove(Long id) throws ValidationException, ServiceException {
        try {
            if (!conferencDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return conferencDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Check result section
     *
     * @param resultSection name result section
     * @return Long id
     */
    private Long resultSection(String resultSection) {
        Long idResult = null;

        if ((resultSection.equals("Active")) || (resultSection.equals("????????????????")) || (resultSection.equals("Actif"))
                || (resultSection.equals("??????????????"))) {
            idResult = (long) 1;
        }

        if ((resultSection.equals("DELETE")) || (resultSection.equals("??????????????????")) || (resultSection.equals("Distant"))
                || (resultSection.equals("????????????????"))) {
            idResult = (long) 2;
        }
        return idResult;
    }
}
