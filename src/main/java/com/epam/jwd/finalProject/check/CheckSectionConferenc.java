package com.epam.jwd.finalProject.check;

import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.impl.MethodSectionConferencDaoImpl;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodUserDaoImpl;
import com.epam.jwd.finalProject.model.SectionConferenc;

import java.util.List;

public class CheckSectionConferenc {

    private static final MethodSectionConferencDaoImpl s = new MethodSectionConferencDaoImpl(ConnectionPool.locking());

    public static void main(String[] args) throws EntityExtractionFailedException {
        System.out.println("-----ALL_Conferenc-----");

        List<SectionConferenc> conferencs = s.readAll();

        conferencs.forEach(role -> System.out.println("found user {}" + role));

        System.out.println();
        System.out.println("-----ALL_ID_Conferenc-----");

        System.out.println(s.readById((long) 2));

        System.out.println();
        System.out.println("-----ALL_NAME_Conferenc-----");

        conferencs = s.findByName("Types");

        conferencs.forEach(role -> System.out.println("found user {}" + role));

        System.out.println();
        System.out.println("-----DELETE_Conferenc-----");
        s.delete((long) 1);
        conferencs = s.readAll();

        conferencs.forEach(role -> System.out.println("found user {}" + role));

        System.out.println();
        System.out.println("-----CREATE_Conferenc-----");
        s.create("Number", "",(long) 5);
       // conferencs = s.readAll();

        conferencs.forEach(role -> System.out.println("found user {}" + role));

        System.out.println();
        System.out.println("-----ADD_DESCRIPTION_Conferenc-----");
        s.updateDescription((long)3,"фыва");
         conferencs = s.readAll();

        conferencs.forEach(role -> System.out.println("found user {}" + role));

    }
}
