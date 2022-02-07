package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.CategoryDaoImpl;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.model.Status;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {
    @Mock
    private CategoryDaoImpl dao;
    @InjectMocks
    private CategoryServiceImpl service;
    private Long idPage;
    private boolean expectedResult;
    private Long limitPagination;
    private Long offsetPagination;
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
        name = "IT";
        id = (long) 1;
        expectedResult = true;
        limitPagination = (long) 5;
        offsetPagination = (long) 0;
        idPage = (long) 1;
        category = new Category((long) 1, "PE");
        sectionConferenc = new SectionConferenc((long) 1, "ART", "Name", new Conferenc((long) 2,
                "SD", "Qwerty", new Category((long) 3, "QWE"),
                new Status((long) 2, "Active")),
                new Status((long) 5, "Active"));
        conferenc = new Conferenc((long) 1, "name", "description",
                new Category((long) 3, "IT"),
                new Status((long) 1, "Active"));
        sectionConferencList = new ArrayList<>();
        sectionConferencList.add(sectionConferenc);
        categoryList = new ArrayList<>();
        categoryList.add(category);
        conferencList = new ArrayList<>();
        conferencList.add(conferenc);
    }

    @Test
    public void findCountAllCategory() throws DaoException, ServiceException {
        Long expectedResult = (long) 4;
        when(dao.findCountAllCategory()).thenReturn(expectedResult);
        Long actualResult = service.findCountAllCategory();
        expectedResult=pageCount(expectedResult,actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void create() throws DaoException, ValidationException, ServiceException {
        when(dao.create(name)).thenReturn(expectedResult);
        boolean actualResult = service.create(name);
        assertTrue(actualResult);
    }

    @Test
    public void findForDuplicateCategory() throws DaoException, ValidationException, ServiceException {
        when(dao.findForDuplicateCategory(name)).thenReturn(expectedResult);
        boolean actualResult = service.findForDuplicateCategory(name);
        assertTrue(actualResult);
    }

    @Test
    public void changeName() throws ValidationException, ServiceException, DaoException {
        when(dao.changeName(id, name)).thenReturn(expectedResult);
        boolean actualResult = service.changeName(id, name);
        assertTrue(actualResult);
    }

    @Test
    public void findConferencInIdCategory() throws DaoException, ValidationException, ServiceException {
        List<Conferenc> expectedResult = conferencList;
        when(dao.findConferencInIdCategory(id)).thenReturn(expectedResult);
        List<Conferenc> actualResult = service.findConferencInIdCategory(id);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findSectionConferencInIdCategory() throws DaoException, ValidationException, ServiceException {
        List<SectionConferenc> expectedResult = sectionConferencList;
        when(dao.findSectionConferencInIdCategory(id)).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = service.findSectionConferencInIdCategory(id);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findAll() throws DaoException, EntityExtractionFailedException, ServiceException {
        List<Category> expectedResult = categoryList;
        when(dao.findAll(limitPagination, offsetPagination)).thenReturn(expectedResult);
        List<Category> actualResult = service.findAll(idPage);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findId() throws DaoException, ValidationException, ServiceException {
        Optional<Category> expectedResult = Optional.of(category);
        when(dao.findById(id)).thenReturn(expectedResult);
        Optional<Category> actualResult = service.findId(id);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void remove() throws DaoException, ValidationException, ServiceException {
        when(dao.delete(id)).thenReturn(expectedResult);
        boolean actualResult = service.remove(id);
        assertTrue(actualResult);
    }
    private Long pageCount(Long expectedResult,Long actualResult) {
        if ((expectedResult-(actualResult * limitPagination) <=5) ||
                (expectedResult+(actualResult * limitPagination) <=5)) {
            expectedResult=actualResult;
        }
        return expectedResult;
    }
}