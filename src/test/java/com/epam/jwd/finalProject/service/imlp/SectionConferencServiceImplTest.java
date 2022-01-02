package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;
import com.epam.jwd.finalProject.model.Status;
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


public class SectionConferencServiceImplTest extends Assert {
    private SectionConferencServiceImpl service;
    private SectionConferenc sectionConferencOne;
    private SectionConferenc sectionConferencTwo;
    private String name;
    private String description;
    private String nameStatus;
    private Long id;
    private Long idConferenc;
    private List<SectionConferenc> sectionConferencs;

    @Before
    public void setUp() throws Exception {
        service = mock(SectionConferencServiceImpl.class);
        sectionConferencOne=new SectionConferenc((long)1,"ART","Name",new Conferenc((long)2,
                "SD","Qwerty",new Category((long)3,"QWE"),
                new Status((long)2,"Active")),new Status((long)5,"Active"));
        sectionConferencTwo=new SectionConferenc((long)4,"ZXC","Poi",new Conferenc((long)5,
                "KL","ASDF",new Category((long)6,"NB"),new Status((long)2,"Active")),
                new Status((long)5,"Active"));
        name="Bike";
        description="test";
        id=(long)7;
        nameStatus="Active";
        idConferenc=(long)2;
        sectionConferencs=new ArrayList<>();
        sectionConferencs.add(sectionConferencOne);
        sectionConferencs.add(sectionConferencTwo);
    }

    @Test
    public void changeStatus() throws ValidationException, ServiceException {
        boolean expectedResult = true;
        when(service.changeStatus(id,nameStatus)).thenReturn(true);
        boolean actualResult =service.changeStatus(id,nameStatus);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void changeStatusAfterUpdateConferenc() throws ValidationException, ServiceException  {
        boolean expectedResult = true;
        when(service.changeStatusAfterUpdateConferenc(idConferenc)).thenReturn(true);
        boolean actualResult =service.changeStatusAfterUpdateConferenc(idConferenc);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findAll() throws ServiceException {
        List<SectionConferenc> expectedResult= sectionConferencs;
        when(service.findAll()).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = service.findAll();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findId() throws ValidationException, ServiceException  {
        Optional<SectionConferenc> expectedResult = Optional.of(sectionConferencOne);
        when(service.findId(id)).thenReturn(expectedResult);
        Optional<SectionConferenc> actualResult = service.findId(id);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void remove() throws ValidationException, ServiceException  {
        boolean expectedResult = true;
        when(service.remove(id)).thenReturn(true);
        boolean actualResult = service.remove(id);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void create() throws ValidationException, ServiceException  {
        boolean expectedResult = true;
        when(service.create(name,description,idConferenc)).thenReturn(true);
        boolean actualResult = service.create(name,description,idConferenc);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void updateDescription() throws ValidationException, ServiceException  {
        boolean expectedResult = true;
        when(service.updateDescription(id,description)).thenReturn(true);
        boolean actualResult = service.updateDescription(id,description);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByName() throws ValidationException, ServiceException  {
        List<SectionConferenc> expectedResult= sectionConferencs;
        when(service.findByName(name)).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = service.findByName(name);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findSectionConferencesInConferencById() throws ValidationException, ServiceException  {
        List<SectionConferenc> expectedResult= sectionConferencs;
        when(service.findSectionConferencesInConferencById(idConferenc)).thenReturn(expectedResult);
        List<SectionConferenc> actualResult = service.findSectionConferencesInConferencById(idConferenc);
        assertEquals(actualResult, expectedResult);
    }
}