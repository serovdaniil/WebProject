package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MethodSectionConferencDaoImplTest extends Assert {
    private SectionConferencDaoImpl dao;
    private SectionConferenc sectionConferencOne;
    private SectionConferenc sectionConferencTwo;
    private String name;
    private String description;
    private Long id;
    private Long idConferenc;
    private Long idStatus;
    private List<SectionConferenc> sectionConferencs;

    @Before
    public void setUp(){
        dao = mock(SectionConferencDaoImpl.class);
        sectionConferencOne=new SectionConferenc((long)1,"ART","Name",new Conferenc((long)2,
                "SD","Qwerty",new Category((long)3,"QWE"),new Status((long)2,"Active")),
                new Status((long)5,"Active"));
        sectionConferencTwo=new SectionConferenc((long)4,"ZXC","Poi",new Conferenc((long)5,
                "KL","ASDF",new Category((long)6,"NB"),new Status((long)2,"Active")),
                new Status((long)5,"Active"));
        name="Bike";
        description="test";
        id=(long)7;
        idConferenc=(long)4;
        idStatus=(long)1;
        sectionConferencs=new ArrayList<>();
        sectionConferencs.add(sectionConferencOne);
        sectionConferencs.add(sectionConferencTwo);
    }

    @Test
    public void changeStatus()throws DaoException {
        boolean expectedResult = true;
        when(dao.changeStatus(id,idStatus)).thenReturn(true);
        boolean actualResult =dao.changeStatus(id,idStatus);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void changeStatusAfterUpdateConferenc()throws DaoException  {
        boolean expectedResult = true;
        when(dao.changeStatusAfterUpdateConferenc(idConferenc)).thenReturn(true);
        boolean actualResult =dao.changeStatusAfterUpdateConferenc(idConferenc);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void create()throws DaoException  {
        boolean expectedResult = true;
        when(dao.create(name,description,id)).thenReturn(true);
        boolean actualResult =dao.create(name,description,id);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateDescription() throws DaoException {
        boolean expectedResult = true;
        when(dao.updateDescription(id,description)).thenReturn(true);
        boolean actualResult =dao.updateDescription(id,description);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readAll() throws EntityExtractionFailedException, DaoException  {
        List<SectionConferenc> expectedResult= sectionConferencs;
        when(dao.readAll((long)1,(long)1)).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = dao.readAll((long)1,(long)1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readById()throws DaoException  {
        Optional<SectionConferenc> expectedResult= Optional.of(sectionConferencOne);
        when(dao.readById((long)1)).thenReturn(expectedResult);
        Optional<SectionConferenc> actualResult = dao.readById((long)1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByName()throws DaoException  {
        List<SectionConferenc> expectedResult= sectionConferencs;
        when(dao.findByName(name)).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = dao.findByName(name);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void delete()throws DaoException  {
        boolean expectedResult = true;
        when(dao.delete(id)).thenReturn(true);
        boolean actualResult =dao.delete(id);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findSectionConferencesInConferencById()throws DaoException  {
        List<SectionConferenc> expectedResult= sectionConferencs;
        when(dao.findSectionConferencesInConferencById(id)).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = dao.findSectionConferencesInConferencById(id);
        assertEquals(actualResult, expectedResult);
    }
}