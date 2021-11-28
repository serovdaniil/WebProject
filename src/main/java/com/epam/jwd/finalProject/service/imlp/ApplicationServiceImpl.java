package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodApplicationDaoImpl;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.service.api.ApplicationService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ApplicationServiceImpl implements ApplicationService {
    private final MethodApplicationDaoImpl applicationDao;

    public ApplicationServiceImpl(MethodApplicationDaoImpl applicationDao) {
        this.applicationDao = applicationDao.getInstance();
    }

    @Override
    public boolean create(Long idAccount, Long idSectionConferenc, Long idResultSection) {
        return false;
    }

    @Override
    public boolean updateIdStatusApplication(Long idApplication, Long idResultSection) {
        return false;
    }

    @Override
    public List<Application> findAccountIdByApplication(Long id) {
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
    public List<Application> findByStatusResult(Long idStatus) {
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
        try {
            return applicationDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }return Collections.emptyList();
    }

    @Override
    public Optional findId(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }
}
