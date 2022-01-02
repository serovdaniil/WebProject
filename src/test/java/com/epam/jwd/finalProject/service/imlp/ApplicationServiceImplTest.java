package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.model.*;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationServiceImplTest extends Assert{
    private ApplicationServiceImpl service;
    private String nameResult;
    private Long idApplication;
    private Long idSectionConferenc;
    private Long idResult;
    private Long idUser;
    private Application application;
    private List<Application> applicationList;

    @Before
    public void setUp(){
        service = mock(ApplicationServiceImpl.class);
        nameResult = "Open";
        idApplication = (long) 1;
        idSectionConferenc = (long) 4;
        idUser = (long) 2;
        idResult = (long) 1;
        application = new Application((long) 1,
                new User((long) 1, "daniils3rov@yandex.ru", "daniils3rov@yandex.ru", "Serov231969",
                        "Daniil", "Serov", Role.USER),new SectionResult((long) 1, "Open"),
                new SectionConferenc((long) 1, "ART", "Name",
                new Conferenc((long) 2, "SD", "Qwerty", new Category((long) 3, "QWE"),
                        new Status((long)4,"Active")),new Status((long)1,"Active")));
         applicationList = new ArrayList<>();
        applicationList.add(application);
    }
    @Test
    public void changeStatusApplicationAfterUpdateSectionConferenc() throws ValidationException, ServiceException {
        boolean expectedResult = true;
        when(service.changeStatusApplicationAfterUpdateSectionConferenc(idSectionConferenc)).thenReturn(true);
        boolean actualResult = service.changeStatusApplicationAfterUpdateSectionConferenc(idSectionConferenc);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void create() throws ValidationException, ServiceException  {
        boolean expectedResult = true;
        when(service.create(idUser, idSectionConferenc,idResult)).thenReturn(true);
        boolean actualResult = service.create(idUser, idSectionConferenc,idResult);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateIdStatusApplication() throws ValidationException, ServiceException  {
        boolean expectedResult = true;
        when(service.updateIdStatusApplication(idApplication, nameResult)).thenReturn(true);
        boolean actualResult = service.updateIdStatusApplication(idApplication, nameResult);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findAccountIdByApplication() throws ValidationException, ServiceException  {
        List<Application> expectedResult = applicationList;
        when(service.findAccountIdByApplication(idUser)).thenReturn(expectedResult);
        List<Application> actualResult = service.findAccountIdByApplication(idUser);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByStatusResult() throws ValidationException , ServiceException {
        List<Application> expectedResult = applicationList;
        when(service.findByStatusResult(nameResult)).thenReturn(expectedResult);
        List<Application> actualResult = service.findByStatusResult(nameResult);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findAll() throws ServiceException {
        List<Application> expectedResult = applicationList;
        when(service.findAll()).thenReturn(expectedResult);
        List<Application> actualResult = service.findAll();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findId() throws ValidationException, ServiceException  {
        Optional<Application> expectedResult = Optional.of(application);
        when(service.findId(idApplication)).thenReturn(expectedResult);
        Optional<Application> actualResult = service.findId(idApplication);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void remove() throws ValidationException, ServiceException  {
        boolean expectedResult = true;
        when(service.remove(idApplication)).thenReturn(true);
        boolean actualResult = service.remove(idApplication);
        Assert.assertEquals(actualResult, expectedResult);
    }
}