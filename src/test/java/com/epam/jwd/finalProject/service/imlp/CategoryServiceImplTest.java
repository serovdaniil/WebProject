package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest extends Assert {
    private CategoryServiceImpl service;
    private String name;
    private Long id;
    private SectionConferenc sectionConferenc;
    private Conferenc conferenc;
    private Category category;
    private List<SectionConferenc> sectionConferencList;
    private List<Category> categoryList;
    private List<Conferenc> conferencList;

    @Before
    public void setUp() {
        service = mock(CategoryServiceImpl.class);
        name = "IT";
        id = (long) 1;
        category = new Category((long) 1, "PE");
        sectionConferenc = new SectionConferenc((long) 1, "ART", "Name", new Conferenc((long) 2,
                "SD", "Qwerty", new Category((long) 3, "QWE")));
        conferenc = new Conferenc((long) 2, "SD", "Qwerty", new Category((long) 3, "QWE"));
        sectionConferencList = new ArrayList<>();
        sectionConferencList.add(sectionConferenc);
        categoryList = new ArrayList<>();
        categoryList.add(category);
        conferencList = new ArrayList<>();
        conferencList.add(conferenc);
    }

    @Test
    public void create() throws ValidationException {
        boolean expectedResult = true;
        when(service.create(name)).thenReturn(true);
        boolean actualResult = service.create(name);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void changeName() throws ValidationException {
        boolean expectedResult = true;
        when(service.changeName(id, name)).thenReturn(true);
        boolean actualResult = service.changeName(id, name);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findConferencInIdCategory() throws ValidationException {
        List<Conferenc> expectedResult = conferencList;
        when(service.findConferencInIdCategory(id)).thenReturn(expectedResult);
        List<Conferenc> actualResult = service.findConferencInIdCategory(id);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findSectionConferencInIdCategory() throws ValidationException {
        List<SectionConferenc> expectedResult = sectionConferencList;
        when(service.findSectionConferencInIdCategory(id)).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = service.findSectionConferencInIdCategory(id);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findAll() {
        List<Category> expectedResult = categoryList;
        when(service.findAll()).thenReturn(expectedResult);
        List<Category> actualResult = service.findAll();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findId() throws ValidationException {
        Optional<Category> expectedResult = Optional.of(category);
        when(service.findId(id)).thenReturn(expectedResult);
        Optional<Category> actualResult = service.findId(id);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void remove() throws ValidationException {
        boolean expectedResult = true;
        when(service.remove(id)).thenReturn(true);
        boolean actualResult = service.remove(id);
        Assert.assertEquals(actualResult, expectedResult);
    }
}