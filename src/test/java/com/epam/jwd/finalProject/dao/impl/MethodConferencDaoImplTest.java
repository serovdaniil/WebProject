package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.Status;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MethodConferencDaoImplTest extends Assert{
    private ConferencDaoImpl dao;
    private Long id;
    private Long idStatus;
    private Long idCategory;
    private String name;
    private String description;
    private Conferenc conferenc;
    private List<Conferenc>conferencList;

    @Before
    public void setUp(){
        dao = mock(ConferencDaoImpl.class);
        id=(long)1;
        idStatus=(long)4;
        idCategory=(long)4;
        name="Java";
        description="Learning java method";
        conferenc=new Conferenc((long)1,name,description,new Category((long)3,"IT"),
                new Status((long)1,"Active"));
        conferencList=new ArrayList<>();
        conferencList.add(conferenc);
    }

    @Test
    public void changeStatus() throws DaoException {
        boolean expectedResult = true;
        when(dao.changeStatus(id,idStatus)).thenReturn(true);
        boolean actualResult = dao.changeStatus(id,idStatus);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void create() throws DaoException {
        boolean expectedResult = true;
        when(dao.create(name,description,idCategory)).thenReturn(true);
        boolean actualResult = dao.create(name,description,idCategory);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateDescription()throws DaoException  {
        boolean expectedResult = true;
        when(dao.updateDescription(id,description)).thenReturn(true);
        boolean actualResult = dao.updateDescription(id,description);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readAll() throws EntityExtractionFailedException, DaoException  {
        List<Conferenc> expectedResult = conferencList;
        when(dao.readAll((long)1,(long)1)).thenReturn(expectedResult);
        List<Conferenc> actualResult = dao.readAll((long)1,(long)1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readAllActive() throws EntityExtractionFailedException, DaoException  {
        List<Conferenc> expectedResult = conferencList;
        when(dao.readAllActive()).thenReturn(expectedResult);
        List<Conferenc> actualResult = dao.readAllActive();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readById() throws DaoException {
        Optional<Conferenc> expectedResult= Optional.of(conferenc);
        when(dao.readById((long)1)).thenReturn(expectedResult);
        Optional<Conferenc> actualResult = dao.readById((long)1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByName()throws DaoException  {
        List<Conferenc> expectedResult = conferencList;
        when(dao.findByName(name)).thenReturn(expectedResult);
        List<Conferenc> actualResult = dao.findByName(name);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void delete() throws DaoException {
        boolean expectedResult = true;
        when(dao.delete(id)).thenReturn(true);
        boolean actualResult = dao.delete(id);
        Assert.assertEquals(actualResult, expectedResult);
    }
}