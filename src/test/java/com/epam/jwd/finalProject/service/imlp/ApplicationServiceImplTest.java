package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.impl.ApplicationDaoImpl;
import com.epam.jwd.finalProject.model.*;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ApplicationServiceImplTest {
    @Mock
    private ApplicationDaoImpl applicationDao;
    @Spy
    @InjectMocks
    private ApplicationServiceImpl applicationService = new ApplicationServiceImpl(applicationDao);
    private String nameResult;
    private Long idApplication;
    private Long idSectionConferenc;
    private Long idResult;
    private Long idUser;
    private Application application;
    private List<Application> applicationList;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        nameResult = "Activate";
        idApplication = (long) 5;
        idSectionConferenc = (long) 4;
        idUser = (long) 43;
        idResult = (long) 1;
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
    public void changeStatusApplicationAfterUpdateSectionConferenc() throws ValidationException, ServiceException, DaoException {
        boolean expectedResult = true;
        Long idSectionConferenc = (long) 1000;
        Mockito.when(applicationDao.changeStatusApplicationAfterUpdateSectionConferenc(idSectionConferenc)).thenReturn(expectedResult);
        boolean actualResult = applicationService.changeStatusApplicationAfterUpdateSectionConferenc(idSectionConferenc);
        System.out.println(actualResult);
        assertEquals(expectedResult, actualResult);

    }

    @Test
    public void create() throws ValidationException, ServiceException, DaoException {
        boolean expectedResult = false;
        when(applicationDao.create(idUser, idSectionConferenc, idResult)).thenReturn(expectedResult);
        boolean actualResult = applicationDao.create(idUser, idSectionConferenc, idResult);
        assertEquals(actualResult, expectedResult);

    }

    @Test
    public void findId() throws ValidationException, ServiceException, DaoException {
        Optional<Application> expectedResult = Optional.of(application);
        when(applicationDao.readById(idApplication)).thenReturn(expectedResult);
        Optional<Application> actualResult = applicationDao.readById(idApplication);
        System.out.println(expectedResult + "+" + actualResult);
        assertEquals(expectedResult, actualResult);
    }

/**
 *     @Test
 *     public void findAll() throws ServiceException{
 *         List<Application> expectedResult = applicationList;
 *         when(applicationDao.readAll(any(Long.class))).thenReturn(applicationList);
 *         List<Application> actualResult = applicationService.findAll((long) 1);
 *         assertEquals(expectedResult, actualResult);
 *     }
 *
 * @Test public void updateIdStatusApplication() throws ValidationException, ServiceException, DaoException {
 * boolean expectedResult = true;
 * when(applicationDao.updateIdStatusApplication(idApplication, idResult)).thenReturn(true);
 * boolean actualResult = applicationService.updateIdStatusApplication(idApplication, nameResult);
 * assertEquals(actualResult, false);
 * }

 @Test public void findAccountIdByApplication() throws ValidationException, ServiceException, DaoException {
 List<Application> expectedResult = applicationList;
 when(applicationDao.findAccountIdByApplication(idUser, idUser, idUser)).thenReturn(expectedResult);
 List<Application> actualResult = applicationService.findAccountIdByApplication(idUser, idUser);
 assertEquals(actualResult, expectedResult);
 }
 **/
    /**
     * @Test public void findByStatusResult() throws ValidationException, ServiceException, DaoException {
     * List<Application> expectedResult = applicationList;
     * when(applicationDao.findByStatusResult(idResult)).thenReturn(expectedResult);
     * List<Application> actualResult = applicationService.findByStatusResult(nameResult);
     * assertEquals(actualResult, expectedResult);
     * }
     **/


/**


 @Test public void remove() throws ValidationException, ServiceException {
 boolean expectedResult = true;
 when(dao.remove(idApplication)).thenReturn(true);
 boolean actualResult = dao.remove(idApplication);
 Assert.assertEquals(actualResult, expectedResult);
 }**/
}