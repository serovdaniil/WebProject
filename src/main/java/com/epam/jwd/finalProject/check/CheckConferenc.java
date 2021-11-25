package com.epam.jwd.finalProject.check;

import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.impl.MethodConferencDaoImpl;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodUserDaoImpl;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;

import java.util.List;

public class CheckConferenc {

    private static final MethodConferencDaoImpl s = new MethodConferencDaoImpl(ConnectionPool.locking());

    public static void main(String[] args) throws EntityExtractionFailedException {
        System.out.println("-----ALL_Conferenc-----");

        List<Conferenc> conferencs = s.readAll();

        conferencs.forEach(role -> System.out.println("found user {}" + role));

        System.out.println();
        System.out.println("-----ALL_ID_Conferenc-----");

        System.out.println(s.readById((long) 5));

        System.out.println();
        System.out.println("-----ALL_NAME_Conferenc-----");

        conferencs = s.findByName("java");

        conferencs.forEach(role -> System.out.println("found user {}" + role));

        System.out.println();
        System.out.println("-----DELETE_Conferenc-----");
        s.delete((long) 3);
        conferencs = s.readAll();

        conferencs.forEach(role -> System.out.println("found user {}" + role));

        System.out.println();
        System.out.println("-----CREATE_Conferenc-----");
        s.create("Python"," ",(long) 1);
        conferencs = s.readAll();

        conferencs.forEach(role -> System.out.println("found user {}" + role));

        System.out.println();
        System.out.println("-----ADD_DESCRIPTION_Conferenc-----");
        s.updateDescription((long)5,"фыва");
         conferencs = s.readAll();

        conferencs.forEach(role -> System.out.println("found user {}" + role));

    }
}
