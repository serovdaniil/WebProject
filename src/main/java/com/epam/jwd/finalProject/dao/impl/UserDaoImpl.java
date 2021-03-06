package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.api.UserDao;
import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.exception.DaoException;
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
public class UserDaoImpl implements UserDao {
    /**
     * SQL query for this dao
     */
    private static final String CREATE_USER = "INSERT INTO user (login,email,password, role_id) values(?,?,?,?)";

    private static final String UPDATE_PASSWORD_BY_LOGIN_ACCOUNT = "UPDATE user JOIN role ON role_id=id_role " +
            "SET password=? WHERE login = ?";

    private static final String UPDATE_EMAIL_BY_ID_ACCOUNT = "UPDATE user JOIN role ON role_id=id_role  " +
            "SET email=? WHERE id_user = ?";

    private static final String UPDATE_FIRSTNAME_BY_ID_ACCOUNT = "UPDATE user JOIN role ON role_id=id_role  " +
            "SET first_name=? WHERE id_user = ?";

    private static final String UPDATE_LASTNAME_BY_ID_ACCOUNT = "UPDATE user JOIN role ON role_id=id_role  " +
            "SET last_name=? WHERE id_user = ?";

    private static final String UPDATE_ROLE_BY_ID_ACCOUNT = "UPDATE user JOIN role ON role_id=id_role " +
            "SET role_id=? WHERE id_user = ?";

    private static final String FIND_ALL_ACCOUNT = "SELECT * FROM final_task.user JOIN final_task.role " +
            "ON role_id=id_role LIMIT ? OFFSET ?";

    private static final String FIND_ID_USER = "SELECT * FROM user JOIN role ON role_id=id_role WHERE id_user=?";

    private static final String FIND_EMAIL_BY_ACCOUNT = "SELECT * FROM user JOIN role " +
            "ON role_id=id_role WHERE email=?";

    private static final String FIND_PASSWORD_BY_LOGIN = "SELECT * FROM user JOIN role " +
            "ON role_id=id_role WHERE login=?";

    private static final String FIND_COUNT_ALL_USER = "SELECT COUNT(id_user) FROM user JOIN role " +
            "ON role_id=id_role";

    /**
     * Logger for this dao
     */
    private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class);
    /**
     * Connection pool for this dao
     */
    private final ConnectionPool connectionPool;

    /**
     * Constructor - creating a new object
     *
     * @param connectionPool connectionPool for this dao
     */
    public UserDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Find count all users
     *
     * @return count users
     */
    @Override
    public Long findCountAllUser() throws DaoException {
        Long result = (long) 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_COUNT_ALL_USER)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_COUNT_ALL_USER);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Create user
     *
     * @param email    email for new user
     * @param password password for new user
     * @return User
     */
    @Override
    public Optional<User> create(String email, String password) throws DaoException {
        boolean result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
            statement.setString(1, email);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setLong(4, 1);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            LOG.error("Diplicate email" + e);
            return Optional.empty();
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", CREATE_USER);
            throw new DaoException(e);
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
    public Optional<User> readById(Long id) throws DaoException {
        Optional<User> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ID_USER)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = extractUser(resultSet);
                productOptional = Optional.of(user);
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ID_USER);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
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
    public Optional<User> updatePasswordByLogin(String login, String password) throws DaoException {
        boolean result;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD_BY_LOGIN_ACCOUNT)) {
            statement.setString(2, login);
            statement.setString(1, password);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", UPDATE_PASSWORD_BY_LOGIN_ACCOUNT);
            throw new DaoException(e);
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
    public Optional<User> updateEmail(Long id, String email) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMAIL_BY_ID_ACCOUNT)) {
            statement.setLong(2, id);
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            LOG.error("Diplicate email" + e);
            return Optional.empty();
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", UPDATE_EMAIL_BY_ID_ACCOUNT);
            throw new DaoException(e);
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
    public Optional<User> updateFirstName(Long id, String firstName) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_FIRSTNAME_BY_ID_ACCOUNT)) {
            statement.setLong(2, id);
            statement.setString(1, firstName);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", UPDATE_FIRSTNAME_BY_ID_ACCOUNT);
            throw new DaoException(e);
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
    public Optional<User> updateLastName(Long id, String lastName) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_LASTNAME_BY_ID_ACCOUNT)) {
            statement.setLong(2, id);
            statement.setString(1, lastName);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", UPDATE_LASTNAME_BY_ID_ACCOUNT);
            throw new DaoException(e);
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
    public Optional<User> updateRole(Long idAccount, Long idRole) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ROLE_BY_ID_ACCOUNT)) {
            statement.setLong(2, idAccount);
            statement.setLong(1, idRole);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", UPDATE_ROLE_BY_ID_ACCOUNT);
            throw new DaoException(e);
        }
        return readById(idAccount);
    }

    /**
     * Read all users
     *
     * @param limit  number of rows in the selection
     * @param offset offset from the beginning of the selectio
     * @return List user
     */
    @Override
    public List<User> readAll(Long limit, Long offset) throws EntityExtractionFailedException, DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACCOUNT)) {
            statement.setLong(1, limit);
            statement.setLong(2, offset);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<User> extractor = UserDaoImpl::extractUser;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ALL_ACCOUNT);
            throw new DaoException(e);
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
    public Optional<User> findByEmail(String email) throws DaoException {
        Optional<User> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_EMAIL_BY_ACCOUNT)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = extractUser(resultSet);
                productOptional = Optional.of(user);
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ID_USER);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
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
    public Optional<User> findPasswordByLogin(String login) throws DaoException {
        Optional<User> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PASSWORD_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = extractUser(resultSet);
                productOptional = Optional.of(user);
            }
        } catch (SQLException | EntityExtractionFailedException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_PASSWORD_BY_LOGIN);
            throw new DaoException(e);
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
    public static UserDaoImpl getInstance() {
        return UserDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final UserDaoImpl INSTANCE = new UserDaoImpl(ConnectionPool.locking());
    }
}
