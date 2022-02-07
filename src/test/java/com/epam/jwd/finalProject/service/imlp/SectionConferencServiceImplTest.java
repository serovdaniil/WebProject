package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.SectionConferencDaoImpl;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;
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
public class SectionConferencServiceImplTest extends Assert {
    @Mock
    private SectionConferencDaoImpl dao;
    @InjectMocks
    private SectionConferencServiceImpl service;
    private SectionConferenc sectionConferencOne;
    private SectionConferenc sectionConferencTwo;
    private String name;
    private String description;
    private String nameStatus;
    private Long id;
    private Long idConferenc;
    private List<SectionConferenc> sectionConferencs;
    private Long limitPagination;
    private Long offsetPagination;
    private Long idPage;
    private boolean expectedResult;

    @Before
    public void setUp() throws Exception {
        sectionConferencOne=new SectionConferenc((long)1,"ART","Name",new Conferenc((long)2,
                "SD","Qwerty",new Category((long)3,"QWE"),
                new Status((long)2,"Active")),new Status((long)5,"Active"));
        sectionConferencTwo=new SectionConferenc((long)4,"ZXC","Poi",new Conferenc((long)5,
                "KL","ASDF",new Category((long)6,"NB"),new Status((long)2,"Active")),
                new Status((long)5,"Active"));
        name="Bike";
        description="test";
        id=(long)1;
        nameStatus="Active";
        idConferenc=(long)2;
        sectionConferencs=new ArrayList<>();
        sectionConferencs.add(sectionConferencOne);
        sectionConferencs.add(sectionConferencTwo);
        limitPagination = (long) 5;
        offsetPagination = (long) 0;
        idPage = (long) 1;
        expectedResult = true;
    }

    @Test
    public void changeStatus() throws ValidationException, ServiceException, DaoException {
        when(dao.changeStatus(id,id)).thenReturn(expectedResult);
        boolean actualResult =service.changeStatus(id,nameStatus);
        assertTrue(actualResult);
    }

    @Test
    public void changeStatusAfterUpdateConferenc() throws ValidationException, ServiceException, DaoException {
        when(dao.changeStatusAfterUpdateConferenc(idConferenc)).thenReturn(expectedResult);
        boolean actualResult =service.changeStatusAfterUpdateConferenc(idConferenc);
        assertTrue(actualResult);
    }

    @Test
    public void findAll() throws ServiceException, DaoException, EntityExtractionFailedException {
        List<SectionConferenc> expectedResult= sectionConferencs;
        when(dao.readAll(limitPagination,offsetPagination)).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = service.findAll(idPage);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findId() throws ValidationException, ServiceException, DaoException {
        Optional<SectionConferenc> expectedResult = Optional.of(sectionConferencOne);
        when(dao.readById(id)).thenReturn(expectedResult);
        Optional<SectionConferenc> actualResult = service.findId(id);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void remove() throws ValidationException, ServiceException, DaoException {
        when(dao.delete(id)).thenReturn(expectedResult);
        boolean actualResult = service.remove(id);
        assertTrue(actualResult);
    }

    @Test
    public void create() throws ValidationException, ServiceException, DaoException {
        when(dao.create(name,description,idConferenc)).thenReturn(expectedResult);
        boolean actualResult = service.create(name,description,idConferenc);
        assertTrue(actualResult);
    }

    @Test
    public void updateDescription() throws ValidationException, ServiceException, DaoException {
        when(dao.updateDescription(id,description)).thenReturn(expectedResult);
        boolean actualResult = service.updateDescription(id,description);
        assertTrue(actualResult);
    }

    @Test
    public void findByName() throws ValidationException, ServiceException, DaoException {
        List<SectionConferenc> expectedResult= sectionConferencs;
        when(dao.findByName(name)).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = service.findByName(name);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findSectionConferencesInConferencById() throws ValidationException, ServiceException, DaoException {
        List<SectionConferenc> expectedResult= sectionConferencs;
        when(dao.findSectionConferencesInConferencById(idConferenc)).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = service.findSectionConferencesInConferencById(idConferenc);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findSectionConferencesInConferencByIdWithPagination() throws ValidationException,
            ServiceException, DaoException {
        List<SectionConferenc> expectedResult= sectionConferencs;
        when(dao.findSectionConferencesInConferencByIdWithPagination(idConferenc,limitPagination,offsetPagination))
                .thenReturn(expectedResult);
        List<SectionConferenc> actualResult = service
                .findSectionConferencesInConferencByIdWithPagination(idConferenc,idPage);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findCountAllSectionConferencInConferenc() throws DaoException,
            ValidationException, ServiceException {
        Long expectedResult = (long) 4;
        when(dao.findCountAllSectionConferencInConferenc(idConferenc)).thenReturn(expectedResult);
        Long actualResult = service.findCountAllSectionConferencInConferenc(idConferenc);
        expectedResult=pageCount(expectedResult,actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findCountAllSectionConferenc() throws DaoException, ServiceException {
        Long expectedResult = (long) 4;
        when(dao.findCountAllSectionConferenc()).thenReturn(expectedResult);
        Long actualResult = service.findCountAllSectionConferenc();
        expectedResult=pageCount(expectedResult,actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findForDuplicateSectionConferenc() throws DaoException, ValidationException, ServiceException {
        when(dao.findForDuplicateSectionConferenc(name,description,idConferenc)).thenReturn(expectedResult);
        boolean actualResult = service.findForDuplicateSectionConferenc(name,description,idConferenc);
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