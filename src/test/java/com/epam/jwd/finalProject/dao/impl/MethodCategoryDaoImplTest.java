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

public class MethodCategoryDaoImplTest extends Assert {
    private MethodCategoryDaoImpl dao;
    private String name;
    private Long id;
    private Category category;
    private Conferenc conferenc;
    private SectionConferenc sectionConferenc;
    private List<SectionConferenc> sectionConferencs;
    private List<Conferenc> conferencs;
    private List<Category> categories;

    @Before
    public void setUp() {
        dao = mock(MethodCategoryDaoImpl.class);
        name = "IT";
        id = (long) 1;
        category = new Category((long) 1, "PE");
        categories=new ArrayList<>();
        categories.add(category);
        sectionConferenc = new SectionConferenc((long) 1, "ART", "Name", new Conferenc((long) 2,
                "SD", "Qwerty", new Category((long) 3, "QWE")));
        sectionConferencs=new ArrayList<>();
        sectionConferencs.add(sectionConferenc);
        conferencs=new ArrayList<>();
        conferenc = new Conferenc((long) 2, "SD", "Qwerty", new Category((long) 3, "QWE"));
        conferencs.add(conferenc);
    }

    @Test
    public void create() {
        boolean expectedResult = true;
        when(dao.create(name)).thenReturn(true);
        boolean actualResult = dao.create(name);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void changeName() {
        boolean expectedResult = true;
        when(dao.changeName(id, name)).thenReturn(true);
        boolean actualResult = dao.changeName(id, name);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readAll() throws EntityExtractionFailedException {
        List<Category> expectedResult = categories;
        when(dao.readAll()).thenReturn(expectedResult);
        List<Category> actualResult = dao.readAll();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readById() {
        Optional<Category> expectedResult = Optional.of(category);
        when(dao.readById((long) 1)).thenReturn(expectedResult);
        Optional<Category> actualResult = dao.readById((long) 1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findConferencInIdCategory() {
        List<Conferenc> expectedResult = conferencs;
        when(dao.findConferencInIdCategory((long) 1)).thenReturn(expectedResult);
        List<Conferenc> actualResult = dao.findConferencInIdCategory((long) 1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findConferencInNameCategory() {
        List<Conferenc> expectedResult = conferencs;
        when(dao.findConferencInIdCategory((long) 1)).thenReturn(expectedResult);
        List<Conferenc> actualResult = dao.findConferencInIdCategory((long) 1);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findSectionConferencInIdCategory() {
        List<SectionConferenc> expectedResult = sectionConferencs;
        when(dao.findSectionConferencInIdCategory((long)5)).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = dao.findSectionConferencInIdCategory((long)5);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findSectionConferencInNameCategory() {
        List<SectionConferenc> expectedResult = sectionConferencs;
        when(dao.findSectionConferencInNameCategory("asd")).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = dao.findSectionConferencInNameCategory(("asd"));
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void delete() {
        boolean expectedResult = true;
        when(dao.delete(id)).thenReturn(true);
        boolean actualResult = dao.delete(id);
        Assert.assertEquals(actualResult, expectedResult);
    }
}