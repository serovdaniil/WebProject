package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.SectionConferencDaoImpl;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.validator.SectionConferencDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Daniil Serov
 * @see SectionConferencDaoImpl
 */
public class SectionConferencServiceImpl implements SectionConferencService {
    /**
     * Logger for this service
     */
    private static final Logger LOG = LogManager.getLogger(SectionConferencServiceImpl.class);
    /**
     * Dao for this service
     */
    private final SectionConferencDaoImpl sectionConferencDao;
    /**
     * Validator for this service
     */
    private final SectionConferencDataValidator sectionConferencDataValidator =
            new SectionConferencDataValidator().getInstance();
    /**
     * Limit for pagination
     */
    private static final Long LIMIT = (long) 5;

    /**
     * Constructor - creating a new object
     *
     * @param sectionConferencDao dao for this service
     */
    public SectionConferencServiceImpl(SectionConferencDaoImpl sectionConferencDao) {
        this.sectionConferencDao = sectionConferencDao;
    }

    /**
     * Find section conferenc by id conferenc with pagination
     *
     * @param id         id for conferenc
     * @param pageNumber selected page
     * @return List section conferenc
     */
    @Override
    public List<SectionConferenc> findSectionConferencesInConferencByIdWithPagination(Long id, Long pageNumber)
            throws ValidationException, ServiceException {
        try {
            if (!sectionConferencDataValidator.isIdValid(pageNumber) ||
                    !sectionConferencDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            final Long offset = LIMIT * (pageNumber - 1);
            return sectionConferencDao.findSectionConferencesInConferencByIdWithPagination(id, LIMIT, offset);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find count all section conferenc
     *
     * @param id id conferenc for section conferenc
     * @return count section conferences
     */
    @Override
    public Long findCountAllSectionConferencInConferenc(Long id) throws ServiceException, ValidationException {
        try {
            if (!sectionConferencDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            final Long countSectionConferenc = sectionConferencDao.findCountAllSectionConferencInConferenc(id);
            Long pageCount = countSectionConferenc / LIMIT;
            if ((countSectionConferenc - pageCount * LIMIT) > 0) {
                pageCount++;
            }
            return pageCount;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }


    /**
     * Find count all section conferenc
     *
     * @return count section conferences
     */
    @Override
    public Long findCountAllSectionConferenc() throws ServiceException {
        try {
            final Long countSectionConferenc = sectionConferencDao.findCountAllSectionConferenc();
            Long pageCount = countSectionConferenc / LIMIT;
            if ((countSectionConferenc - pageCount * LIMIT) > 0) {
                pageCount++;
            }
            return pageCount;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Change status section conferenc by id
     *
     * @param idSectionConferenc id section conferenc
     * @param nameStatus         name status
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean changeStatus(Long idSectionConferenc,
                                String nameStatus) throws ValidationException, ServiceException {
        try {
            Long idStatus = resultSection(nameStatus);
            if (!sectionConferencDataValidator.isIdValid(idSectionConferenc) ||
                    !sectionConferencDataValidator.isIdValid(idStatus)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return sectionConferencDao.changeStatus(idSectionConferenc, idStatus);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find for duplicate section conferenc
     *
     * @param name        name for section conferenc
     * @param description description for section conferenc
     * @param idConferenc id conferenc for section conferenc
     * @return boolean result of operation
     */
    @Override
    public boolean findForDuplicateSectionConferenc(String name, String description, Long idConferenc)
            throws ValidationException, ServiceException {
        try {
            if (!sectionConferencDataValidator.isIdValid(idConferenc)
                    || !sectionConferencDataValidator.isNameValid(name)
                    || !sectionConferencDataValidator.isDescriptionValid(description)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return sectionConferencDao.findForDuplicateSectionConferenc(name, description, idConferenc);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Change status auto after update status conferenc
     *
     * @param idConferenc id conferenc
     * @return boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean changeStatusAfterUpdateConferenc(Long idConferenc)
            throws ValidationException, ServiceException {
        try {
            if (!sectionConferencDataValidator.isIdValid(idConferenc)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return sectionConferencDao.changeStatusAfterUpdateConferenc(idConferenc);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find all section conferences
     *
     * @param pageNumber selected page
     * @return List section conferences
     */
    @Override
    public List<SectionConferenc> findAll(Long pageNumber) throws ServiceException {
            try {
                final Long offset = LIMIT * (pageNumber - 1);
                return sectionConferencDao.readAll(LIMIT, offset);
            } catch (EntityExtractionFailedException e) {
                e.printStackTrace();
            }catch (DaoException e) {
                throw new ServiceException(e);
            }
            return Collections.emptyList();
        }

    /**
     * Find section conferenc by id
     *
     * @param id id section conferenc
     * @return Section conferenc
     * @throws ValidationException if there are validation problems
     */
    @Override
    public Optional<SectionConferenc> findId(Long id) throws ValidationException, ServiceException {
        try {
            if (!sectionConferencDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return sectionConferencDao.readById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Remove section conferenc by id
     *
     * @param id id section conferenc
     * @return Boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean remove(Long id) throws ValidationException, ServiceException {
        try {
            if (!sectionConferencDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return sectionConferencDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Create section conferenc by id
     *
     * @param name        name section conferenc
     * @param description description section conferenc
     * @param idConferenc id conferenc
     * @return Boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean create(String name, String description,
                          Long idConferenc) throws ValidationException, ServiceException {
        try {
            if (!sectionConferencDataValidator.isIdValid(idConferenc) ||
                    !sectionConferencDataValidator.isNameValid(name) ||
                    !sectionConferencDataValidator.isDescriptionValid(description)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return sectionConferencDao.create(name, description, idConferenc);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Update description section conferenc by id
     *
     * @param id          id section conferenc
     * @param description description conferenc
     * @return Boolean
     * @throws ValidationException if there are validation problems
     */
    @Override
    public boolean updateDescription(Long id, String description) throws ValidationException, ServiceException {
        try {
            if (!sectionConferencDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return sectionConferencDao.updateDescription(id, description);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find section conferenc by name
     *
     * @param name name section conferenc
     * @return List section conferenc
     * @throws ValidationException if there are validation problems
     */
    @Override
    public List<SectionConferenc> findByName(String name) throws ValidationException, ServiceException {
        try {
            if (!sectionConferencDataValidator.isNameValid(name)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return sectionConferencDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Find section conferenc in conferenc by id conferenc
     *
     * @param id id conferenc
     * @return List section conferenc
     * @throws ValidationException if there are validation problems
     */
    @Override
    public List<SectionConferenc> findSectionConferencesInConferencById(Long id)
            throws ValidationException, ServiceException {
        try {
            if (!sectionConferencDataValidator.isIdValid(id)) {
                LOG.error("The entered data is not correct!");
                throw new ValidationException("The entered data is not correct!");
            }
            return sectionConferencDao.findSectionConferencesInConferencById(id);
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

        if ((resultSection.equals("Active")) || (resultSection.equals("Активная")) ||
                (resultSection.equals("Actif")) || (resultSection.equals("Актыўны"))) {
            idResult = (long) 1;
        }

        if ((resultSection.equals("DELETE")) || (resultSection.equals("Удаленная")) ||
                (resultSection.equals("Distant")) || (resultSection.equals("Выдалены"))) {
            idResult = (long) 2;
        }
        return idResult;
    }
}
