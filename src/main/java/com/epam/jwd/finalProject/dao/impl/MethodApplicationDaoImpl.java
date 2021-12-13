package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.api.ApplicationDao;
import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.connection.LockingConnectionPool;
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

/**
 * The application dao
 *
 * @author Daniil Serov
 */
public class MethodApplicationDaoImpl implements ApplicationDao {
    /**
     * Logger for this dao
     */
    private static final Logger LOG = LogManager.getLogger(MethodApplicationDaoImpl.class);
    /**
     * Connection pool for this dao
     */
    private final ConnectionPool connectionPool;

    /**
     * Constructor - creating a new object
     *
     * @param connectionPool connectionPool for this dao
     */
    public MethodApplicationDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Create application
     *
     * @param idAccount          id account
     * @param idSectionConferenc id section conferenc for application
     * @param idResultSection    id resulr section
     * @return boolean result of operation
     */
    @Override
    public boolean create(Long idAccount, Long idSectionConferenc, Long idResultSection) {
        boolean result = false;
        LOG.info("Start create application");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CREATE_APPLICATION)) {
            statement.setLong(1, idAccount);
            statement.setLong(2, idSectionConferenc);
            statement.setLong(3, idResultSection);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End create application");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.CREATE_APPLICATION);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    /**
     * Change status application after section conferenc
     *
     * @param idSectionConferenc id section conferenc for application
     * @return boolean result of operation
     */
    @Override
    public boolean changeStatusApplicationAfterUpdateSectionConferenc(Long idSectionConferenc) {
        LOG.info("START update result application");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CHANGE_STATUS_AFTER_UPDATE_SECTION_CONFERENC)) {
            statement.setLong(1, idSectionConferenc);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("END update result application");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.CHANGE_STATUS_AFTER_UPDATE_SECTION_CONFERENC);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    /**
     * Update status application by id
     *
     * @param idApplication   id application
     * @param idResultSection result section
     * @return boolean result of operation
     */
    @Override
    public boolean updateIdStatusApplication(Long idApplication, Long idResultSection) {
        LOG.info("START update result application");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ID_RESULT_APPLICATION)) {
            statement.setLong(2, idApplication);
            statement.setLong(1, idResultSection);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("END update result application");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_ID_RESULT_APPLICATION);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    /**
     * Read all application
     *
     * @return List application
     */
    @Override
    public List<Application> readAll() throws EntityExtractionFailedException {
        LOG.info("Start readAll application");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_APPLICATION)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = MethodApplicationDaoImpl::extractApplication;
            LOG.info("End readAll application");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ALL_APPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    /**
     * Read apllication by id
     *
     * @param id id application
     * @return Application
     */
    @Override
    public Optional<Application> readById(Long id) {
        LOG.info("Start readById application");
        Optional<Application> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ID_APPLICATION)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Application application = extractApplication(resultSet);
                productOptional = Optional.of(application);
            }
            LOG.info("End readById application");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ID_APPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return productOptional;
    }

    /**
     * Find application by id account
     *
     * @param id id application
     * @return List application
     */
    @Override
    public List<Application> findAccountIdByApplication(Long id) {
        LOG.info("Start find id by account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ID_ACCOUNT_BY_CAPPLICATION)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = MethodApplicationDaoImpl::extractApplication;
            LOG.info("End find id by account");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ID_ACCOUNT_BY_CAPPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    /**
     * Find application by status result
     *
     * @param idStatus id status application
     * @return List application
     */
    @Override
    public List<Application> findByStatusResult(Long idStatus) {
        LOG.info("Start find statusResult by account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ID_SECTION_RESULT_BY_CAPPLICATION)) {
            statement.setLong(1, idStatus);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = MethodApplicationDaoImpl::extractApplication;
            LOG.info("End find statusResult by account");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ID_SECTION_RESULT_BY_CAPPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    /**
     * Remove application by id
     *
     * @param id id application
     * @return boolean result of operation
     */
    @Override
    public boolean delete(Long id) {
        LOG.info("Start delete application");
        boolean result = false;
        try (Connection connection = LockingConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.APPLICATION_DELETE)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
            LOG.info("End delete application");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.APPLICATION_DELETE);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    /**
     * Get application
     *
     * @return Application
     */
    private static Application extractApplication(ResultSet resultSet) throws EntityExtractionFailedException {
        try {
            return new Application(
                    resultSet.getLong("id_application"),
                    new User(resultSet.getLong("user_id"),
                            resultSet.getString("login"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            Role.of(resultSet.getString("account_role"))),
                    new SectionResult(resultSet.getLong("id_section_result"),
                            resultSet.getString("result")),
                    new SectionConferenc(
                            resultSet.getLong("id_section_conferenc"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            new Conferenc(resultSet.getLong("id_conferenc"),
                                    resultSet.getString("conferenc.name"),
                                    resultSet.getString("conferenc.description"),
                                    new Category(resultSet.getLong("id_category"),
                                            resultSet.getString("name_category")),
                                    new Status(resultSet.getLong("id_status"),
                                            resultSet.getString("name_status"))),
                            new Status(resultSet.getLong("id_status"),
                                    resultSet.getString("name_status"))));
        } catch (SQLException e) {
            throw new EntityExtractionFailedException();
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static MethodApplicationDaoImpl getInstance() {
        return MethodApplicationDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final MethodApplicationDaoImpl INSTANCE = new MethodApplicationDaoImpl(ConnectionPool.locking());
    }
}