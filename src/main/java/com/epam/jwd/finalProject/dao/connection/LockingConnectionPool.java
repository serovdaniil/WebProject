package com.epam.jwd.finalProject.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @author Daniil Serov
 */

public class LockingConnectionPool implements ConnectionPool{
    private static final Logger LOG = LogManager.getLogger(LockingConnectionPool.class);
    private static AtomicBoolean isCreated = new AtomicBoolean();
    private static LockingConnectionPool instance = new LockingConnectionPool();
    private static final Lock LOCKER = new ReentrantLock(true); //михаил сказал надо сделать private static final
    private BlockingQueue<ProxyConnection> freeConnection;
    private BlockingQueue<ProxyConnection> givenAwayConnections;
    private static final int DEFAULT_POOL_SIZE = 8;

    private boolean initialized = false;

    public LockingConnectionPool() {
        initializeConnections(DEFAULT_POOL_SIZE);
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public boolean init() {
        if (!initialized) {
            initializeConnections(DEFAULT_POOL_SIZE);
            initialized = true;
            return true;
        }
        return false;
    }


    private void initializeConnections(int mount) {
        freeConnection = new LinkedBlockingDeque<>(mount);
        givenAwayConnections = new LinkedBlockingDeque<>(mount);
        LOG.info("Try to create connection pool");
        for (int i = 0; i < mount; i++) {
            try {
                Connection connection = ConnectionCreator.getConnection();
                ProxyConnection proxyConnection = new ProxyConnection(connection,this);
                freeConnection.add(proxyConnection);
            } catch (SQLException e) {
                LOG.error("coudn't create connection to data base: " + e.getMessage());
            }
        }
        if (freeConnection.size() == 0) {
            LOG.info("connections poll don't created, pool size: " + freeConnection.size());
            throw new RuntimeException("connections poll don't created");
        }
        LOG.info("Connection pool was created");
    }

    public static LockingConnectionPool getInstance() {
        if (!isCreated.get()) {
            LOCKER.lock();
            if (instance == null) {
                instance = new LockingConnectionPool();
                isCreated.set(true);
            }
            LOCKER.unlock();
        }
        return instance;
    }
    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = freeConnection.take();
            givenAwayConnections.add((ProxyConnection) connection);
        } catch (InterruptedException e) {
            LOG.error("InterruptedException in method getConnection " + e.getMessage());
            Thread.currentThread().interrupt();
        }
        return connection;
    }
    @Override
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection && givenAwayConnections.remove(connection)) {
            try {
                freeConnection.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                LOG.error("InterruptedException in method getConnection " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
    @Override
    public void destroyPool() {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            closeAnyConnection();
        }
        deregisterDrivers();
    }

    private void closeAnyConnection() {
        try {
            freeConnection.take().realClose();
        } catch (InterruptedException e) {
            LOG.error("InterruptedException in method destroyPool " + e.getMessage());
        } catch (SQLException e) {
            LOG.error("SQLException in method destroyPool " + e.getMessage());
        }
    }

    private void deregisterDrivers() {
        LOG.trace("Unregistering sql drivers");
        final Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                LOG.error("Could not deregister driver", e);
            }};
    }

}
