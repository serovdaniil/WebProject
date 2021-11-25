package com.epam.jwd.finalProject.check;

import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.impl.MethodQuestionDaoImpl;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodUserDaoImpl;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.model.Role;
import com.epam.jwd.finalProject.model.User;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;

public class CheckQuestion {
    private static Logger logger;

    private static final MethodQuestionDaoImpl s = new MethodQuestionDaoImpl(ConnectionPool.locking());

    public static void main(String[] args) throws EntityExtractionFailedException {
        System.out.println("-----ALL_Question-----");

        List<Question> questions = s.readAll();



        questions.forEach(role -> System.out.println("found user {}" + role));

        System.out.println("-----ID_Question-----");

        System.out.println(s.readById((long) 2));

        System.out.println("-----ALL_ACCOUNT_ID_BY_Question-----");

        questions = s.findAccountIdByQuestion((long) 1);

        questions.forEach(role -> System.out.println("found user {}" + role));

        System.out.println("-----ALL_QUESTION_BY_ACCOUNT_FIRSTNAME-----");

        questions = s.findByFirstName("alla");

        questions.forEach(role -> System.out.println("found user {}" + role));

        System.out.println("-----ALL_QUESTION_BY_ACCOUNT_LASTNAME-----");

        questions = s.findByLastName("Ivanov");

        questions.forEach(role -> System.out.println("found user {}" + role));

        System.out.println("-----ALL_QUESTION_BY_ACCOUNT_EMAIL-----");

        questions = s.findByEmail("serov@gmail.com");

        questions.forEach(role -> System.out.println("found user {}" + role));

        System.out.println("-----ALL_QUESTION_BY_ACCOUNT_LOGIN-----");

        questions = s.findByLogin("serov");

        questions.forEach(role -> System.out.println("found user {}" + role));

        System.out.println("-----ALL_QUESTION_BY_ACCOUNT_ANSWER-----");

        questions = s.findByAnswer("фвпфывпуцкп");

        questions.forEach(role -> System.out.println("found user {}" + role));
        System.out.println();
       System.out.println("-----QUESTION_REMOVE-----");

        boolean result = s.delete((long)13);
        questions = s.readAll();
        questions.forEach(role -> System.out.println("found user {}" + role));
        System.out.println();
        System.out.println("-----CREATE-----");

        System.out.println("Результат добавления вопроса: " + result);
        questions = s.readAll();

        questions.forEach(role -> System.out.println("found user {}" + role));

        System.out.println();
        System.out.println("-----ADD_ANSWER-----");

       result = s.addAnswer((long) 3,"Minsk");
        System.out.println("Результат добавления овтета на вопрос: " + result);
        System.out.println(s.readById((long)3));

        System.out.println();
        System.out.println("-----UPDATE_QUESTION-----");
        System.out.println(s.readById((long)14));
        result = s.updateQuestion((long) 14,"I love Venecia");
        System.out.println("Результат добавления овтета на вопрос: " + result);
        System.out.println(s.readById((long)14));


    }
}
