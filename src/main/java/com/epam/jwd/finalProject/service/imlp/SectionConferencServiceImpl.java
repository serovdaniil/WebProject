package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodSectionConferencDaoImpl;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.api.SectionConferencService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SectionConferencServiceImpl implements SectionConferencService {

    private final MethodSectionConferencDaoImpl sectionConferencDao;

    public SectionConferencServiceImpl(MethodSectionConferencDaoImpl sectionConferencDao) {
        this.sectionConferencDao = sectionConferencDao;
    }

    @Override
    public List<SectionConferenc> findAll() {
        try {
            return sectionConferencDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<SectionConferenc> findId(Long id) {
        return sectionConferencDao.readById(id);
    }

    @Override
    public boolean remove(Long id) {
        return sectionConferencDao.delete(id);
    }

    @Override
    public boolean create(String name, String description, Long idConferenc) {
        return sectionConferencDao.create(name,description,idConferenc);
    }

    @Override
    public boolean updateDescription(Long id, String description) {
        return sectionConferencDao.updateDescription(id,description);
    }

    @Override
    public List<SectionConferenc> findByName(String name) {
        return sectionConferencDao.findByName(name);
    }

    @Override
    public List<SectionConferenc> findSectionConferencesInConferencById(Long id) {
        return sectionConferencDao.findSectionConferencesInConferencById(id);
    }
}
