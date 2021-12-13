package com.epam.jwd.finalProject.dao.connection.transaction;

import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.connection.LockingConnectionPool;

import java.sql.Connection;

public final class TransactionConnectionPool implements ConnectionPool {
    private final ConnectionPool connectionPool;
    private final TransactionManager transactionManager;

    public TransactionConnectionPool(ConnectionPool connectionPool, TransactionManager transactionManager) {
        this.connectionPool = connectionPool;
        this.transactionManager = transactionManager;
    }

    @Override
    public boolean isInitialized() {
        return connectionPool.isInitialized();
    }

    @Override
    public boolean init() {
        return connectionPool.init();
    }

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void releaseConnection(Connection connection) {

    }

    @Override
    public void destroyPool() {
        connectionPool.destroyPool();
    }

    public static ConnectionPool getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder  {
        private static final ConnectionPool INSTANCE = new TransactionConnectionPool(LockingConnectionPool.getInstance(), TransactionManager.instance());
    }

}