package com.epam.jwd.finalProject.check;

import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.impl.MethodApplicationDaoImpl;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodUserDaoImpl;
import com.epam.jwd.finalProject.model.Application;

import java.util.List;

public class CheckApplication {


    private static final MethodApplicationDaoImpl s = new MethodApplicationDaoImpl(ConnectionPool.locking());



     public static void main(String[] args) throws EntityExtractionFailedException {


        System.out.println("-----ALL_Application-----");

        List<Application> conferencs = s.readAll();

        conferencs.forEach(role -> System.out.println("found user {}" + role));


        System.out.println();
        System.out.println("-----ALL_delete_By-Application-----");

        boolean result = s.create((long) 3, (long) 9, (long) 1);

        System.out.println(result);
    }
}
