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
    private CategoryDaoImpl dao;
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
        dao = mock(CategoryDaoImpl.class);
        name = "IT";
        id = (long) 1;
        category = new Category((long) 1, "PE");
        categories=new ArrayList<>();
        categories.add(category);
        sectionConferenc = new SectionConferenc((long) 1, "ART", "Name", new Conferenc((long) 2,
                "SD", "Qwerty", new Category((long) 3, "QWE"),new Status((long)2,"Active")),new Status((long)5,"Active"));
        sectionConferencs=new ArrayList<>();
        sectionConferencs.add(sectionConferenc);
        conferencs=new ArrayList<>();
        conferenc = new Conferenc((long)1,"name","description",new Category((long)3,"IT"),new Status((long)1,"Active"));
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
        when(dao.findAll()).thenReturn(expectedResult);
        List<Category> actualResult = dao.findAll();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void readById() {
        Optional<Category> expectedResult = Optional.of(category);
        when(dao.findById((long) 1)).thenReturn(expectedResult);
        Optional<Category> actualResult = dao.findById((long) 1);
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
    public void delete() {
        boolean expectedResult = true;
        when(dao.delete(id)).thenReturn(true);
        boolean actualResult = dao.delete(id);
        Assert.assertEquals(actualResult, expectedResult);
    }
}