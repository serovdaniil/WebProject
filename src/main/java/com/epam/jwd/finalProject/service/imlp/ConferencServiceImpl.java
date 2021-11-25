package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodConferencDaoImpl;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.api.ConferencService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ConferencServiceImpl implements ConferencService {

    private  final MethodConferencDaoImpl conferencDao;

    public ConferencServiceImpl(MethodConferencDaoImpl conferencDao) {
        this.conferencDao = conferencDao.getInstance();
    }

    @Override
    public boolean updateDescription(Long id, String description) {return conferencDao.updateDescription(id,description);}

    @Override
    public List<Conferenc> findByName(String name) {
        return conferencDao.findByName(name);
    }

    @Override
    public List<Conferenc> findAll() {
        try {
            return conferencDao.readAll();
        } catch (EntityExtractionFailedException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public boolean create(String name, String description,Long idCategory) {
        return conferencDao.create(name,description,idCategory);
    }

    @Override
    public Optional<Conferenc> findId(Long id) {
        return conferencDao.readById(id);
    }

    @Override
    public boolean remove(Long id) {
        return conferencDao.delete(id);
    }
}
