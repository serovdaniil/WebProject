package com.epam.jwd.finalProject.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Daniil Serov
 */


public class ConnectionCreator {
    private static final Logger LOG = LogManager.getLogger(LockingConnectionPool.class);
    private static final Properties properties = new Properties();
    private static final String DATABASE_PROPERTIES = "database.properties";
    private static final String PROPERTY_URL = "db.url";
    private static final String PROPERTY_PASSWORD = "db.password";
    private static final String PROPERTY_USER = "db.user";
    private static final String PROPERTY_DRIVER = "db.driver";
    private static String DATABASE_URL;
    private static String DATABASE_PASSWORD;
    private static String DATABASE_USER;
    private static String DATABASE_DRIVER;

    static {
        try (InputStream inputStream = ConnectionCreator.class.getClassLoader()
                .getResourceAsStream(DATABASE_PROPERTIES)) {
            properties.load(inputStream);
            DATABASE_URL = properties.getProperty(PROPERTY_URL);
            DATABASE_PASSWORD = properties.getProperty(PROPERTY_PASSWORD);
            DATABASE_USER = properties.getProperty(PROPERTY_USER);
            DATABASE_DRIVER = properties.getProperty(PROPERTY_DRIVER);
            Class.forName(DATABASE_DRIVER);
        } catch (FileNotFoundException e) {
            LOG.error("FileNotFoundException " + e.getMessage());
            throw new RuntimeException();
        } catch (IOException ex) {
            LOG.error("IOException " + ex.getMessage());
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            LOG.error("driver: " + properties.getProperty(PROPERTY_DRIVER) + " not found");
            throw new RuntimeException();
        }
    }

    protected ConnectionCreator() {
    }

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

}
