package com.epam.jwd.finalProject.check;

import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.impl.MethodApplicationDaoImpl;
import com.epam.jwd.finalProject.dao.impl.MethodUserDaoImpl;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.User;

import java.util.List;
import java.util.Optional;

public class CheckAccount {

    private static final MethodUserDaoImpl s = new MethodUserDaoImpl(ConnectionPool.locking());


    public static void main(String[] args) throws EntityExtractionFailedException {

      String conferencs = s.findPasswordByLogin("test");
        System.out.println(conferencs);

    }
}