package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.api.UserDao;
import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The user dao
 *
 * @author Daniil Serov
 */
public class MethodUserDaoImpl implements UserDao {
    /**
     * Logger for this dao
     */
    private static final Logger LOG = LogManager.getLogger(MethodUserDaoImpl.class);
    /**
     * Connection pool for this dao
     */
    private final ConnectionPool connectionPool;

    /**
     * Constructor - creating a new object
     *
     * @param connectionPool connectionPool for this dao
     */
    public MethodUserDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Create user
     *
     * @param email    email for new user
     * @param password password for new user
     * @return User
     */
    @Override
    public Optional<User> create(String email, String password) {
        boolean result = false;
        LOG.info("Start create user");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CREATE_USER)) {
            statement.setString(1, email);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setLong(4, 1);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End create user");
        } catch (SQLIntegrityConstraintViolationException e) {
            LOG.error("Diplicate email" + e);
            return Optional.empty();
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.CREATE_USER);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return findByEmail(email);
    }

    /**
     * Read user by id
     *
     * @param id id for user
     * @return User
     */
    @Override
    public Optional<User> readById(Long id) {
        LOG.info("Start readById user");
        Optional<User> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ID_USER)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = extractUser(resultSet);
                productOptional = Optional.of(user);
            }
            LOG.info("End readById user");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ID_USER);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return productOptional;
    }

    /**
     * Update password by login
     *
     * @param login    login for user
     * @param password password for user
     * @return User
     */
    @Override
    public Optional<User> updatePasswordByLogin(String login, String password) {
        LOG.info("START update password by login account");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PASSWORD_BY_LOGIN_ACCOUNT)) {
            statement.setString(2, login);
            statement.setString(1, password);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("END update password by login account");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_PASSWORD_BY_LOGIN_ACCOUNT);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return findPasswordByLogin(login);
    }

    /**
     * Update email
     *
     * @param id    id for user
     * @param email email for user
     * @return User
     */
    @Override
    public Optional<User> updateEmail(Long id, String email) {
        LOG.info("START update email by id account");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_EMAIL_BY_ID_ACCOUNT)) {
            statement.setLong(2, id);
            statement.setString(1, email);
            statement.executeUpdate();
            LOG.info("END update email by id account");
        } catch (SQLIntegrityConstraintViolationException e) {
            LOG.error("Diplicate email" + e);
            return Optional.empty();
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_EMAIL_BY_ID_ACCOUNT);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return readById(id);
    }

    /**
     * Update first name
     *
     * @param id        id for user
     * @param firstName firstName for user
     * @return User
     */
    @Override
    public Optional<User> updateFirstName(Long id, String firstName) {
        LOG.info("START update firstName by id account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_FIRSTNAME_BY_ID_ACCOUNT)) {
            statement.setLong(2, id);
            statement.setString(1, firstName);
            statement.executeUpdate();
            LOG.info("END update firstName by id account");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_FIRSTNAME_BY_ID_ACCOUNT);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return readById(id);
    }

    /**
     * Update last name
     *
     * @param id       id for user
     * @param lastName lastName for user
     * @return User
     */
    @Override
    public Optional<User> updateLastName(Long id, String lastName) {
        LOG.info("START update lastName by id account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_LASTNAME_BY_ID_ACCOUNT)) {
            statement.setLong(2, id);
            statement.setString(1, lastName);
            statement.executeUpdate();
            LOG.info("END update lastName by id account");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_LASTNAME_BY_ID_ACCOUNT);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return readById(id);
    }

    /**
     * Update role
     *
     * @param idAccount id for user
     * @param idRole    id role for user
     * @return User
     */
    @Override
    public Optional<User> updateRole(Long idAccount, Long idRole) {
        LOG.info("START update role by id account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ROLE_BY_ID_ACCOUNT)) {
            statement.setLong(2, idAccount);
            statement.setLong(1, idRole);
            statement.executeUpdate();
            LOG.info("END update role by id account");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_ROLE_BY_ID_ACCOUNT);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return readById(idAccount);
    }

    /**
     * Read all users
     *
     * @return List user
     */
    @Override
    public List<User> readAll() throws EntityExtractionFailedException {
        LOG.info("Start readAll account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_ACCOUNT)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<User> extractor = MethodUserDaoImpl::extractUser;
            LOG.info("End readAll account");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ALL_ACCOUNT);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    /**
     * Find user by email
     *
     * @param email email for user
     * @return User
     */
    @Override
    public Optional<User> findByEmail(String email) {
        LOG.info("Start find email by account");
        Optional<User> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_EMAIL_BY_ACCOUNT)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = extractUser(resultSet);
                productOptional = Optional.of(user);
            }
            LOG.info("End find email by account");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ID_USER);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return productOptional;
    }

    /**
     * Find password by login
     *
     * @param login login for user
     * @return User
     */
    @Override
    public Optional<User> findPasswordByLogin(String login) {
        LOG.info("Start find password by login");
        Optional<User> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_PASSWORD_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = extractUser(resultSet);
                productOptional = Optional.of(user);
            }
            LOG.info("End find password by login");
        } catch (SQLException | EntityExtractionFailedException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_PASSWORD_BY_LOGIN);
        }
        return productOptional;
    }

    /**
     * Get user
     *
     * @return User
     */
    private static User extractUser(ResultSet resultSet) throws EntityExtractionFailedException {
        try {
            return new User(resultSet.getLong("id_user"),
                    resultSet.getString("login"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    Role.of(resultSet.getString("account_role")));
        } catch (SQLException e) {
            throw new EntityExtractionFailedException();
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static MethodUserDaoImpl getInstance() {
        return MethodUserDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final MethodUserDaoImpl INSTANCE = new MethodUserDaoImpl(ConnectionPool.locking());
    }
}
