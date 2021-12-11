package com.epam.jwd.finalProject.dao.impl;

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
    private MethodSectionConferencDaoImpl dao;
    private SectionConferenc sectionConferencOne;
    private SectionConferenc sectionConferencTwo;
    private String name;
    private String description;
    private Long id;
    private List<SectionConferenc> sectionConferencs;

    @Before
    public void setUp(){
        dao = mock(MethodSectionConferencDaoImpl.class);
        sectionConferencOne=new SectionConferenc((long)1,"ART","Name",new Conferenc((long)2,
                "SD","Qwerty",new Category((long)3,"QWE")));
        sectionConferencTwo=new SectionConferenc((long)4,"ZXC","Poi",new Conferenc((long)5,
                "KL","ASDF",new Category((long)6,"NB")));
        name="Bike";
        description="test";
        id=(long)7;
        sectionConferencs=new ArrayList<>();
        sectionConferencs.add(sectionConferencOne);
        sectionConferencs.add(sectionConferencTwo);
    }

    @Test
    public void create() {
        boolean expectedResult = true;
        when(dao.create(name,description,id)).thenReturn(true);
        boolean actualResult =dao.create(name,description,id);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateDescription() {
        boolean expectedResult = true;
        when(dao.updateDescription(id,description)).thenReturn(true);
        boolean actualResult =dao.updateDescription(id,description);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readAll() throws EntityExtractionFailedException {
        List<SectionConferenc> expectedResult= sectionConferencs;
        when(dao.readAll()).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = dao.readAll();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readById() {
        Optional<SectionConferenc> expectedResult= Optional.of(sectionConferencOne);
        when(dao.readById((long)1)).thenReturn(expectedResult);
        Optional<SectionConferenc> actualResult = dao.readById((long)1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByName() {
        List<SectionConferenc> expectedResult= sectionConferencs;
        when(dao.findByName(name)).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = dao.findByName(name);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void delete() {
        boolean expectedResult = true;
        when(dao.delete(id)).thenReturn(true);
        boolean actualResult =dao.delete(id);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findSectionConferencesInConferencById() {
        List<SectionConferenc> expectedResult= sectionConferencs;
        when(dao.findSectionConferencesInConferencById(id)).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = dao.findSectionConferencesInConferencById(id);
        assertEquals(actualResult, expectedResult);
    }
}