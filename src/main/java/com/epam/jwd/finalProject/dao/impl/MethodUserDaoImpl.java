package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.api.UserDao;
import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MethodUserDaoImpl implements UserDao {

    private static final Logger LOG = LogManager.getLogger(MethodUserDaoImpl.class);

    private final ConnectionPool connectionPool;

    //change connectionPool.locking()
    public MethodUserDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

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
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.CREATE_USER);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return findByEmail(email);
    }

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

    @Override
    public boolean updatePasswordByLogin(String login, String password) {
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
        return result;
    }

    @Override
    public boolean updateLogin(Long id, String login) {
        LOG.info("START update login by id account");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_LOGIN_BY_ID_ACCOUNT)) {
            statement.setLong(2, id);
            statement.setString(1, login);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("END update login by id account");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_LOGIN_BY_ID_ACCOUNT);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    @Override
    public boolean updateEmail(Long id, String email) {
        LOG.info("START update email by id account");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_EMAIL_BY_ID_ACCOUNT)) {
            statement.setLong(2, id);
            statement.setString(1, email);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("END update email by id account");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_EMAIL_BY_ID_ACCOUNT);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    @Override
    public boolean updateFirstName(Long id, String firstName) {
        LOG.info("START update firstName by id account");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_FIRSTNAME_BY_ID_ACCOUNT)) {
            statement.setLong(2, id);
            statement.setString(1, firstName);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("END update firstName by id account");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_FIRSTNAME_BY_ID_ACCOUNT);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    @Override
    public boolean updateLastName(Long id, String lastName) {
        LOG.info("START update lastName by id account");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_LASTNAME_BY_ID_ACCOUNT)) {
            statement.setLong(2, id);
            statement.setString(1, lastName);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("END update lastName by id account");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_LASTNAME_BY_ID_ACCOUNT);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    @Override
    public boolean updateRole(Long idAccount, Long idRole) {
        LOG.info("START update role by id account");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ROLE_BY_ID_ACCOUNT)) {
            statement.setLong(2, idAccount);
            statement.setLong(1, idRole);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("END update role by id account");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_ROLE_BY_ID_ACCOUNT);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

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

    @Override
    public List<User> findByFirstName(String firstName) {
        LOG.info("Start find firstName by account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FIRSTNAME_BY_ACCOUNT)) {
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<User> extractor = MethodUserDaoImpl::extractUser;
            LOG.info("End find firstName by account");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_FIRSTNAME_BY_ACCOUNT);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<User> findByLastName(String lastName) {
        LOG.info("Start find lastName by account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_LASTNAME_BY_ACCOUNT)) {
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<User> extractor = MethodUserDaoImpl::extractUser;
            LOG.info("End find lastName by account");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_LASTNAME_BY_ACCOUNT);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

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

                LOG.info(user.getPassword());
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

    @Override
    public String findPasswordByLogin(String login) {
        LOG.info("Start readAll account");
        String password = "Not found";
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_PASSWORD_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                password = resultSet.getString("password");
            }
            LOG.info("End readAll account");
            return password;
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_PASSWORD_BY_LOGIN);
        }
        return password;
    }

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

    public static MethodUserDaoImpl getInstance() {
        return MethodUserDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final MethodUserDaoImpl INSTANCE = new MethodUserDaoImpl(ConnectionPool.locking());
    }
}
