package com.epam.jwd.finalProject.check;

import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.impl.MethodCategoryDaoImpl;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodUserDaoImpl;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;

import java.util.List;

public class CheckCategory {

    private static final MethodCategoryDaoImpl s = new MethodCategoryDaoImpl(ConnectionPool.locking());

    public static void main(String[] args) throws EntityExtractionFailedException {
        List<SectionConferenc> conferencs = s.findSectionConferencInIdCategory((long)1);

        conferencs.forEach(role -> System.out.println("found user {}" + role));

    }
}
