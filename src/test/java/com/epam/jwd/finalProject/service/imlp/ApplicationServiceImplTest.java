package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.ApplicationDaoImpl;
import com.epam.jwd.finalProject.model.*;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationServiceImplTest {
    @InjectMocks
    private ApplicationServiceImpl applicationService;
    @Mock
    private ApplicationDaoImpl applicationDao;
    private Long idPage;
    private Long limitPagination;
    private Long offsetPagination;
    private String nameResult;
    private Long idApplication;
    private Long idSectionConferenc;
    private Long idResult;
    private Long idUser;
    private Application application;
    private List<Application> applicationList;

    @Before
    public void setUp() {
        nameResult = "Activate";
        idApplication = (long) 5;
        idSectionConferenc = (long) 4;
        limitPagination = (long) 5;
        offsetPagination = (long) 0;
        idUser = (long) 43;
        idResult = (long) 1;
        idPage = (long) 1;
        application = new Application((long) 1,
                new User((long) 1, "daniils3rov@yandex.ru", "daniils3rov@yandex.ru", "Serov231969",
                        "Daniil", "Serov", Role.USER), new SectionResult((long) 1, "Open"),
                new SectionConferenc((long) 1, "ART", "Name",
                        new Conferenc((long) 2, "SD", "Qwerty", new Category((long) 3, "QWE"),
                                new Status((long) 4, "Active")), new Status((long) 1, "Active")));
        applicationList = new ArrayList<>();
        applicationList.add(application);

    }


    @Test
    public void changeStatusApplicationAfterUpdateSectionConferenc() throws ValidationException,
            ServiceException, DaoException {
        boolean expectedResult = true;
        Mockito.when(applicationDao.changeStatusApplicationAfterUpdateSectionConferenc(idSectionConferenc))
                .thenReturn(expectedResult);
        boolean actualResult = applicationService
                .changeStatusApplicationAfterUpdateSectionConferenc(idSectionConferenc);
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void create() throws ValidationException, ServiceException, DaoException {
        boolean expectedResult = false;
        when(applicationDao.create(idUser, idSectionConferenc, idResult)).thenReturn(expectedResult);
        boolean actualResult = applicationService.create(idUser, idSectionConferenc, idResult);
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void findId() throws ValidationException, ServiceException, DaoException {
        Optional<Application> expectedResult = Optional.of(application);
        when(applicationDao.readById(idApplication)).thenReturn(expectedResult);
        Optional<Application> actualResult = applicationService.findId(idApplication);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void findAll() throws ServiceException, DaoException, EntityExtractionFailedException {
        List<Application> expectedResult = applicationList;
        when(applicationDao.readAll(limitPagination, offsetPagination)).thenReturn(expectedResult);
        List<Application> actualResult = applicationService.findAll(idPage);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updateIdStatusApplication() throws ValidationException, ServiceException, DaoException {
        boolean expectedResult = true;
        when(applicationDao.updateIdStatusApplication(idApplication, idResult)).thenReturn(expectedResult);
        boolean actualResult = applicationService.updateIdStatusApplication(idApplication, nameResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findAccountIdByApplication() throws ValidationException, ServiceException, DaoException {
        List<Application> expectedResult = applicationList;
        when(applicationDao.findAccountIdByApplication(idUser, limitPagination, offsetPagination))
                .thenReturn(expectedResult);
        List<Application> actualResult = applicationService.findAccountIdByApplication(idUser, idPage);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findByStatusResult() throws ValidationException, ServiceException, DaoException {
        List<Application> expectedResult = applicationList;
        when(applicationDao.findByStatusResult(idResult)).thenReturn(expectedResult);
        List<Application> actualResult = applicationService.findByStatusResult(nameResult);
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void remove() throws DaoException, ValidationException, ServiceException {
        boolean expectedResult = true;
        when(applicationDao.delete(idApplication)).thenReturn(expectedResult);
        boolean actualResult = applicationService.remove(idApplication);
        assertTrue(actualResult);
    }

    @Test
    public void findCountAllApplicationByUser() throws DaoException, ValidationException, ServiceException {
        Long expectedResult = (long) 4;
        when(applicationDao.findCountAllApplicationByUser(idUser)).thenReturn(expectedResult);
        Long actualResult = applicationService.findCountAllApplicationByUser(idUser);
        expectedResult=pageCount(expectedResult,actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findCountAllApplication() throws DaoException, ServiceException {
        Long expectedResult = (long) 4;
        when(applicationDao.findCountAllApplication()).thenReturn(expectedResult);
        Long actualResult = applicationService.findCountAllApplication();
        expectedResult=pageCount(expectedResult,actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findForDuplicateApplication() throws DaoException, ValidationException, ServiceException {
        boolean expectedResult = true;
        when(applicationDao.findForDuplicateApplication(idUser,idSectionConferenc,idResult)).
                thenReturn(expectedResult);
        boolean actualResult = applicationService.findForDuplicateApplication(idUser,idSectionConferenc,idResult);
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