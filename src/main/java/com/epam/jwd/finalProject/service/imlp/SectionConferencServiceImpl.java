package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodSectionConferencDaoImpl;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.SectionConferencService;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import com.epam.jwd.finalProject.service.validator.SectionConferencDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SectionConferencServiceImpl implements SectionConferencService {
    private static final Logger LOG = LogManager.getLogger(SectionConferencServiceImpl.class);
    private final MethodSectionConferencDaoImpl sectionConferencDao;
    private final SectionConferencDataValidator sectionConferencDataValidator = new SectionConferencDataValidator().getInstance();
    public SectionConferencServiceImpl(MethodSectionConferencDaoImpl sectionConferencDao) {
        this.sectionConferencDao = sectionConferencDao.getInstance();
    }

    @Override
    public boolean changeStatus(Long idSectionConferenc, String nameStatus) throws ValidationException {
        LOG.debug("Service: Change status conferenc  started.");
        Long idStatus=resultSection(nameStatus);
        if (!sectionConferencDataValidator.isIdValid(idSectionConferenc) ||
                !sectionConferencDataValidator.isIdValid(idStatus)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Change status conferenc finished.");
        return sectionConferencDao.changeStatus(idSectionConferenc,idStatus);
    }

    @Override
    public boolean changeStatusAfterUpdateConferenc(Long idConferenc) throws ValidationException {
        LOG.debug("Service: Change status after update status conferenc started.");
        if (!sectionConferencDataValidator.isIdValid(idConferenc)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Change status after update status conferencc finished.");
        return sectionConferencDao.changeStatusAfterUpdateConferenc(idConferenc);
    }

    @Override
    public List<SectionConferenc> findAll() {
        LOG.debug("Service: Reading all section conferences started.");
        try {
            return sectionConferencDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }
        LOG.debug("Service: Reading all section conferences finished.");
        return Collections.emptyList();
    }

    @Override
    public Optional<SectionConferenc> findId(Long id) throws ValidationException {
        LOG.debug("Service: Reading section conferenc started.");
        if (!sectionConferencDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Reading section conferenc finished.");
        return sectionConferencDao.readById(id);
    }

    @Override
    public boolean remove(Long id) throws ValidationException {
        LOG.debug("Service: Removing section conferenc started.");
        if (!sectionConferencDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Removing section conferenc finished.");
        return sectionConferencDao.delete(id);
    }

    @Override
    public boolean create(String name, String description, Long idConferenc) throws ValidationException {
        LOG.debug("Service: Creating section conferenc started.");
        LOG.info(name);
        LOG.info(description);
        LOG.info(idConferenc);
        if (!sectionConferencDataValidator.isIdValid(idConferenc) || !sectionConferencDataValidator.isNameValid(name) ||
                !sectionConferencDataValidator.isDescriptionValid(description)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Creating section conferenc finished.");
        return sectionConferencDao.create(name,description,idConferenc);
    }

    @Override
    public boolean updateDescription(Long id, String description) throws ValidationException {
        LOG.debug("Service: Updating description in section conferenc started.");
        if (!sectionConferencDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Updating description in section conferenc finished.");
        return sectionConferencDao.updateDescription(id,description);
    }

    @Override
    public List<SectionConferenc> findByName(String name) throws ValidationException {
        LOG.debug("Service: Find section conferenc of name started.");
        if (!sectionConferencDataValidator.isNameValid(name)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Find section conferenc of name finished.");
        return sectionConferencDao.findByName(name);
    }

    @Override
    public List<SectionConferenc> findSectionConferencesInConferencById(Long id) throws ValidationException {
        LOG.debug("Service: Find section conferenc of id started.");
        if (!sectionConferencDataValidator.isIdValid(id)) {
            LOG.error("The entered data is not correct!");
            throw new ValidationException("The entered data is not correct!");
        }
        LOG.debug("Service: Find section conferenc of id finished.");
        return sectionConferencDao.findSectionConferencesInConferencById(id);
    }

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
