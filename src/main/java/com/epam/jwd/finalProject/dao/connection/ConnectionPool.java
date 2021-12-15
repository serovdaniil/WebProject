package com.epam.jwd.finalProject.dao.connection;

import java.sql.Connection;
/**
 * The interface locking connection pool
 *
 * @author Daniil Serov
 */
public interface ConnectionPool {

    static ConnectionPool locking() {
        return LockingConnectionPool.getInstance();
    }

    boolean isInitialized();

    boolean init();

    Connection getConnection();

    void releaseConnection(Connection connection);

    void destroyPool();

}
