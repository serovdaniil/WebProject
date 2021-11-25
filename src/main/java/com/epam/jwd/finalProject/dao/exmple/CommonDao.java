package com.epam.jwd.finalProject.dao.exmple;

import com.epam.jwd.finalProject.dao.connection.LockingConnectionPool;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.Entity;

import java.sql.*;

import static java.lang.String.format;
import static java.lang.String.join;

public abstract class CommonDao <T extends Entity> implements ExEntity<T>{
    private static final LockingConnectionPool CONNECTION_POOL = new LockingConnectionPool();
    private static final String FIND_ALL = "SELECT * FROM ";
    private static final String REMOVE_BY_ID = "DELETE FROM ";
    private static final String WHERE = " WHERE ";
    private static final String FIND_COUNT = "SELECT COUNT(*) AS amount FROM ";
    private static final String AMOUNT = "amount";
    private static final String LIMIT = " LIMIT ? , ?";


    public CommonDao() {

    }

    boolean executeUpdate(String query, Object... params) throws EntityExtractionFailedException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
            System.out.println("2222");
            return true;
        } catch (SQLException e) {
            throw new EntityExtractionFailedException();
        } catch (NullPointerException e){
            System.out.println(e);
        }
        return false;
    }
    PreparedStatement createStatement(String query, Object... params) throws SQLException {
        Connection connection = CONNECTION_POOL.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        int arrayLength = params.length;
        System.out.println("3333");
        for (int i = 1; i <= arrayLength; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    protected abstract T extractResult(ResultSet rs) throws SQLException;



}
