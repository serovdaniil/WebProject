package com.epam.jwd.finalProject.dao.connection;

import com.epam.jwd.finalProject.dao.connection.transaction.TransactionConnectionPool;

import java.sql.Connection;

public interface ConnectionPool {
    static ConnectionPool instance() {
        return TransactionConnectionPool.getInstance();
    }

    static ConnectionPool locking() {
        return LockingConnectionPool.getInstance();
    }
    boolean isInitialized();

    boolean init();

    Connection getConnection();

    void releaseConnection(Connection connection);

    void destroyPool();

}
