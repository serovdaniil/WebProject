package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.api.ConferencDao;
import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.Status;
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
 * The conferenc dao
 *
 * @author Daniil Serov
 */
public class MethodConferencDaoImpl implements ConferencDao {
    /**
     * Logger for this dao
     */
    private static final Logger LOG = LogManager.getLogger(MethodConferencDaoImpl.class);
    /**
     * Connection pool for this dao
     */
    private final ConnectionPool connectionPool;

    /**
     * Constructor - creating a new object
     *
     * @param connectionPool connectionPool for this dao
     */
    public MethodConferencDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Create conferenc
     *
     * @param name        name for new conferenc
     * @param description description for new conferenc
     * @param idCategory  id category for new conferenc
     * @return boolean result of operation
     */
    @Override
    public boolean create(String name, String description, Long idCategory) {
        boolean result = false;
        LOG.info("Start create and add new conferenc");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CREATE_CONFERENC)) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setLong(3, idCategory);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End create and add new question");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.CREATE_CONFERENC);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    /**
     * Change status conferenc by id
     *
     * @param idConferenc id conferenc
     * @param idStatus    status conferenc
     * @return boolean result of operation
     */
    @Override
    public boolean changeStatus(Long idConferenc, Long idStatus) {
        LOG.info("Start update status conferenc");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_STATUS_CONFERENC)) {
            statement.setLong(2, idConferenc);
            statement.setLong(1, idStatus);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End update status conferenc");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_STATUS_CONFERENC);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    /**
     * Update description for conferenc
     *
     * @param id          id conferenc
     * @param description new description for conferenc
     * @return boolean result of operation
     */
    @Override
    public boolean updateDescription(Long id, String description) {
        LOG.info("Start add description by conferenc");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_DESCRIPTION_BY_CONFERENC)) {
            statement.setLong(2, id);
            statement.setString(1, description);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End add description by conferenc");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.ADD_DESCRIPTION_BY_CONFERENC);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    /**
     * Read all conferenc
     *
     * @return List Conferenc
     */
    @Override
    public List<Conferenc> readAll() throws EntityExtractionFailedException {
        LOG.info("Start readAll conferenc");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_CONFERENC)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Conferenc> extractor = MethodConferencDaoImpl::extractConferenc;
            LOG.info("End readAll conferenc");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ALL_CONFERENC);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    /**
     * Read all active status conferenc
     *
     * @return List Conferenc
     */
    @Override
    public List<Conferenc> readAllActive() throws EntityExtractionFailedException {
        LOG.info("Start readAll conferenc active");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_CONFERENC_ACTIVE)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Conferenc> extractor = MethodConferencDaoImpl::extractConferenc;
            LOG.info("End readAll conferenc active");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ALL_CONFERENC_ACTIVE);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    /**
     * Read conferenc by id
     *
     * @param id id conferenc
     * @return Conferenc
     */
    @Override
    public Optional<Conferenc> readById(Long id) {
        LOG.info("Start readById conferenc");
        Optional<Conferenc> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ID_CONFERENC)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Conferenc conferenc = extractConferenc(resultSet);
                productOptional = Optional.of(conferenc);
            }
            LOG.info("End readById conferenc");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ID_CONFERENC);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return productOptional;
    }

    /**
     * Find conferences by name
     *
     * @param name name conferenc
     * @return List Conferenc
     */
    @Override
    public List<Conferenc> findByName(String name) {
        LOG.info("Start find name by conferenc");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_NAME_CONFERENC)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Conferenc> extractor = MethodConferencDaoImpl::extractConferenc;
            LOG.info("End find name by conferenc");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_NAME_CONFERENC);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    /**
     * Remove conferenc by id
     *
     * @param id id conferenc
     * @return boolean result of operation
     */
    @Override
    public boolean delete(Long id) {
        LOG.info("Start delete conferenc");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CONFERENC_DELETE)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
            LOG.info("End delete conferenc");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.CONFERENC_DELETE);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    /**
     * Get conferenc
     *
     * @return Conferenc
     */
    private static Conferenc extractConferenc(ResultSet resultSet) throws EntityExtractionFailedException {
        try {
            return new Conferenc(
                    resultSet.getLong("id_conferenc"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    new Category(resultSet.getLong("id_category"),
                            resultSet.getString("name_category")),
                    new Status(resultSet.getLong("id_status"),
                            resultSet.getString("name_status")));
        } catch (SQLException e) {
            throw new EntityExtractionFailedException();
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static MethodConferencDaoImpl getInstance() {
        return MethodConferencDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final MethodConferencDaoImpl INSTANCE = new MethodConferencDaoImpl(ConnectionPool.locking());
    }
}
