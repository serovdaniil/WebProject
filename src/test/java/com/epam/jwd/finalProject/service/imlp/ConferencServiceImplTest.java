package com.epam.jwd.finalProject.service.imlp;

import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.exception.ValidationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConferencServiceImplTest extends Assert {
    private ConferencServiceImpl service;
    private Long id;
    private Long idCategory;
    private String name;
    private String description;
    private Conferenc conferenc;
    private List<Conferenc> conferencList;

    @Before
    public void setUp(){
        service = mock(ConferencServiceImpl.class);
        id=(long)1;
        idCategory=(long)4;
        name="Java";
        description="Learning java method";
        conferenc=new Conferenc((long)1,name,description,new Category((long)3,"IT"));
        conferencList=new ArrayList<>();
        conferencList.add(conferenc);
    }

    @Test
    public void updateDescription() throws ValidationException {
        boolean expectedResult = true;
        when(service.updateDescription(id,description)).thenReturn(true);
        boolean actualResult = service.updateDescription(id,description);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findByName() throws ValidationException {
        List<Conferenc> expectedResult = conferencList;
        when(service.findByName(name)).thenReturn(expectedResult);
        List<Conferenc> actualResult = service.findByName(name);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findAll() {
        List<Conferenc> expectedResult = conferencList;
        when(service.findAll()).thenReturn(expectedResult);
        List<Conferenc> actualResult = service.findAll();
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void create() throws ValidationException {
        boolean expectedResult = true;
        when(service.create(name,description,idCategory)).thenReturn(true);
        boolean actualResult = service.create(name,description,idCategory);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void findId() throws ValidationException {
        Optional<Conferenc> expectedResult= Optional.of(conferenc);
        when(service.findId(id)).thenReturn(expectedResult);
        Optional<Conferenc> actualResult = service.findId((long)1);
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