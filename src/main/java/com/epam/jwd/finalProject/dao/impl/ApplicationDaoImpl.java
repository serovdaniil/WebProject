package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.api.ApplicationDao;
import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.connection.LockingConnectionPool;
import com.epam.jwd.finalProject.dao.exception.DaoException;
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
public class ApplicationDaoImpl implements ApplicationDao {
    /**
     * SQL query for this dao
     */
    private static final String FIND_ALL_APPLICATION = "SELECT * FROM application JOIN final_task.user ON user_id=id_user " +
            "JOIN final_task.role ON role_id=id_role JOIN section_result " +
            "ON section_result_id=id_section_result JOIN section_conferenc ON section_id=id_section_conferenc " +
            "JOIN status ON section_conferenc_status_id=id_status " +
            "JOIN conferenc ON conferenc_id=id_conferenc JOIN category ON category_id=id_category";

    private static final String FIND_ID_APPLICATION = "SELECT * FROM application JOIN final_task.user " +
            "ON user_id=id_user JOIN final_task.role ON role_id=id_role JOIN section_result " +
            "ON section_result_id=id_section_result JOIN section_conferenc ON section_id=id_section_conferenc " +
            "JOIN status ON section_conferenc_status_id=id_status " +
            "JOIN conferenc ON conferenc_id=id_conferenc JOIN category ON category_id=id_category WHERE id_application=?";

    private static final String FIND_ID_ACCOUNT_BY_CAPPLICATION = "SELECT * FROM application JOIN final_task.user " +
            "ON user_id=id_user JOIN final_task.role ON role_id=id_role JOIN section_result " +
            "ON section_result_id=id_section_result JOIN section_conferenc ON section_id=id_section_conferenc " +
            "JOIN status ON section_conferenc_status_id=id_status " +
            "JOIN conferenc ON conferenc_id=id_conferenc JOIN category ON category_id=id_category WHERE id_user=?";

    private static final String FIND_ID_SECTION_RESULT_BY_CAPPLICATION = "SELECT * FROM application JOIN final_task.user " +
            "ON user_id=id_user JOIN final_task.role ON role_id=id_role JOIN section_result " +
            "ON section_result_id=id_section_result JOIN section_conferenc ON section_id=id_section_conferenc " +
            "JOIN status ON section_conferenc_status_id=id_status " +
            "JOIN conferenc ON conferenc_id=id_conferenc JOIN category ON category_id=id_category WHERE id_section_result=?";

    private static final String APPLICATION_DELETE = "DELETE FROM application WHERE id_application=?";

    private static final String UPDATE_ID_RESULT_APPLICATION = "UPDATE application SET section_result_id = ? " +
            "WHERE id_application = ?";

    private static final String CHANGE_STATUS_AFTER_UPDATE_SECTION_CONFERENC = "UPDATE application SET section_result_id = 4 " +
            "WHERE section_id = ?";

    private static final String CREATE_APPLICATION = "INSERT INTO application (user_id,section_id,section_result_id) values(?,?,?)";

    private static final String FIND_FOR_DUPLICATE_APPLICATION = "SELECT * FROM application JOIN final_task.user ON user_id=id_user " +
            "JOIN final_task.role ON role_id=id_role JOIN section_result " +
            "ON section_result_id=id_section_result JOIN section_conferenc ON section_id=id_section_conferenc " +
            "JOIN status ON section_conferenc_status_id=id_status " +
            "JOIN conferenc ON conferenc_id=id_conferenc JOIN category ON category_id=id_category WHERE user_id=? " +
            "&& section_id=? && id_section_result=?";

    /**
     * Logger for this dao
     */
    private static final Logger LOG = LogManager.getLogger(ApplicationDaoImpl.class);
    /**
     * Connection pool for this dao
     */
    private final ConnectionPool connectionPool;

    /**
     * Constructor - creating a new object
     *
     * @param connectionPool connectionPool for this dao
     */
    public ApplicationDaoImpl(ConnectionPool connectionPool) {
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
    public boolean create(Long idAccount, Long idSectionConferenc, Long idResultSection) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_APPLICATION)) {
            statement.setLong(1, idAccount);
            statement.setLong(2, idSectionConferenc);
            statement.setLong(3, idResultSection);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", CREATE_APPLICATION);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Find for duplicate application
     *
     * @param idAccount          id account
     * @param idSectionConferenc id section conferenc for application
     * @param idResultSection    id result section
     * @return boolean result of operation
     */
    @Override
    public boolean findForDuplicateApplication(Long idAccount, Long idSectionConferenc,
                                               Long idResultSection) throws DaoException {
        Optional<Application> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_FOR_DUPLICATE_APPLICATION)) {
            statement.setLong(1, idAccount);
            statement.setLong(2, idSectionConferenc);
            statement.setLong(3, idResultSection);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Application application = extractApplication(resultSet);
                productOptional = Optional.of(application);
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_FOR_DUPLICATE_APPLICATION);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        if (productOptional.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Change status application after section conferenc
     *
     * @param idSectionConferenc id section conferenc for application
     * @return boolean result of operation
     */
    @Override
    public boolean changeStatusApplicationAfterUpdateSectionConferenc(Long idSectionConferenc) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_STATUS_AFTER_UPDATE_SECTION_CONFERENC)) {
            statement.setLong(1, idSectionConferenc);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", CHANGE_STATUS_AFTER_UPDATE_SECTION_CONFERENC);
            throw new DaoException(e);
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
    public boolean updateIdStatusApplication(Long idApplication, Long idResultSection) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ID_RESULT_APPLICATION)) {
            statement.setLong(2, idApplication);
            statement.setLong(1, idResultSection);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", UPDATE_ID_RESULT_APPLICATION);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Read all application
     *
     * @return List application
     */
    @Override
    public List<Application> readAll() throws EntityExtractionFailedException, DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_APPLICATION)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = ApplicationDaoImpl::extractApplication;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ALL_APPLICATION);
            throw new DaoException(e);
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
    public Optional<Application> readById(Long id) throws DaoException {
        Optional<Application> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ID_APPLICATION)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Application application = extractApplication(resultSet);
                productOptional = Optional.of(application);
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ID_APPLICATION);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
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
    public List<Application> findAccountIdByApplication(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ID_ACCOUNT_BY_CAPPLICATION)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = ApplicationDaoImpl::extractApplication;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ID_ACCOUNT_BY_CAPPLICATION);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
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
    public List<Application> findByStatusResult(Long idStatus) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ID_SECTION_RESULT_BY_CAPPLICATION)) {
            statement.setLong(1, idStatus);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = ApplicationDaoImpl::extractApplication;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ID_SECTION_RESULT_BY_CAPPLICATION);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
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
    public boolean delete(Long id) throws DaoException {
        boolean result = false;
        try (Connection connection = LockingConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(APPLICATION_DELETE)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", APPLICATION_DELETE);
            throw new DaoException(e);
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
    public static ApplicationDaoImpl getInstance() {
        return ApplicationDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ApplicationDaoImpl INSTANCE = new ApplicationDaoImpl(ConnectionPool.locking());
    }
}