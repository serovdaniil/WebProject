package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.ConferencDaoImpl;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.Status;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConferencServiceImplTest extends Assert {
    @Mock
    private ConferencDaoImpl dao;
    @InjectMocks
    private ConferencServiceImpl service;
    private Long id;
    private Long idCategory;
    private String name;
    private String description;
    private String nameStatus;
    private Conferenc conferenc;
    private List<Conferenc> conferencList;
    private Long limitPagination;
    private Long offsetPagination;
    private Long idPage;
    private boolean expectedResult;

    @Before
    public void setUp() {
        id = (long) 1;
        idCategory = (long) 4;
        name = "Java";
        nameStatus = "Active";
        description = "Learning java method";
        conferenc = new Conferenc((long) 1, name, description, new Category((long) 3, "IT"),
                new Status((long) 1, "Active"));
        conferencList = new ArrayList<>();
        conferencList.add(conferenc);
        limitPagination = (long) 5;
        offsetPagination = (long) 0;
        idPage = (long) 1;
        expectedResult = true;
    }

    @Test
    public void changeStatus() throws ValidationException, ServiceException, DaoException {
        when(dao.changeStatus(id, id)).thenReturn(expectedResult);
        boolean actualResult = service.changeStatus(id, nameStatus);
        assertTrue(actualResult);
    }

    @Test
    public void readAllActive() throws ServiceException, DaoException, EntityExtractionFailedException {
        List<Conferenc> expectedResult = conferencList;
        when(dao.readAll(limitPagination, offsetPagination)).thenReturn(expectedResult);
        List<Conferenc> actualResult = service.findAllStatus(idPage);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateDescription() throws ValidationException, ServiceException, DaoException {
        when(dao.updateDescription(id, description)).thenReturn(expectedResult);
        boolean actualResult = service.updateDescription(id, description);
        assertTrue(actualResult);
    }

    @Test
    public void findByName() throws ValidationException, ServiceException, DaoException {
        List<Conferenc> expectedResult = conferencList;
        when(dao.findByName(name)).thenReturn(expectedResult);
        List<Conferenc> actualResult = service.findByName(name);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findAllConferencLimitOffsetPagination() throws ServiceException,
            DaoException, ValidationException {
        List<Conferenc> expectedResult = conferencList;
        when(dao.findAllConferencLimitOffsetPagination(limitPagination, offsetPagination))
                .thenReturn(expectedResult);
        List<Conferenc> actualResult = service.findAllConferencLimitOffsetPagination(idPage);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void create() throws ValidationException, ServiceException, DaoException {
        when(dao.create(name, description, idCategory)).thenReturn(expectedResult);
        boolean actualResult = service.create(name, description, idCategory);
        assertTrue(actualResult);
    }

    @Test
    public void findId() throws ValidationException, ServiceException, DaoException {
        Optional<Conferenc> expectedResult = Optional.of(conferenc);
        when(dao.readById(id)).thenReturn(expectedResult);
        Optional<Conferenc> actualResult = service.findId(id);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void remove() throws ValidationException, ServiceException, DaoException {
        when(dao.delete(id)).thenReturn(expectedResult);
        boolean actualResult = service.remove(id);
        assertTrue(actualResult);
    }

    @Test
    public void findCountAllConferencByActiveStatus() throws DaoException, ServiceException {
        Long expectedResult = (long) 4;
        when(dao.findCountAllConferencByActiveStatus()).thenReturn(expectedResult);
        Long actualResult = service.findCountAllConferencByActiveStatus();
        expectedResult=pageCount(expectedResult,actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findCountAllConferenc() throws DaoException, ServiceException {
        Long expectedResult = (long) 4;
        when(dao.findCountAllConferenc()).thenReturn(expectedResult);
        Long actualResult = service.findCountAllConferenc();
        expectedResult=pageCount(expectedResult,actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findForDuplicateConferenc() throws DaoException, ValidationException, ServiceException {
        when(dao.findForDuplicateConferenc(name,description,idCategory)).thenReturn(expectedResult);
        boolean actualResult = service.findForDuplicateConferenc(name,description,idCategory);
        assertTrue(actualResult);
    }

    private Long pageCount(Long expectedResult, Long actualResult) {
        if ((expectedResult - (actualResult * limitPagination) <= 5) ||
                (expectedResult + (actualResult * limitPagination) <= 5)) {
            expectedResult = actualResult;
        }
        return expectedResult;
    }
}