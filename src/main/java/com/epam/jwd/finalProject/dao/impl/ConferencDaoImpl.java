package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.api.ConferencDao;
import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.exception.DaoException;
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
public class ConferencDaoImpl implements ConferencDao {
    /**
     * SQL query for this dao
     */
    private static final String CREATE_CONFERENC = "INSERT INTO conferenc " +
            "(name,description,category_id,conferenc_status_id) values(?,?,?,1)";
    private static final String ADD_DESCRIPTION_BY_CONFERENC = "UPDATE conferenc SET description = ?" +
            " WHERE id_conferenc = ?";
    private static final String UPDATE_STATUS_CONFERENC = "UPDATE conferenc SET conferenc_status_id = ?" +
            " WHERE id_conferenc = ?";
    private static final String FIND_ALL_CONFERENC_ACTIVE = "SELECT * FROM conferenc JOIN category " +
            "ON category_id=id_category JOIN status ON conferenc_status_id=id_status WHERE conferenc_status_id=1";
    private static final String FIND_ALL_CONFERENC = "SELECT * FROM conferenc JOIN category " +
            "ON category_id=id_category JOIN status ON conferenc_status_id=id_status";
    private static final String FIND_ID_CONFERENC = "SELECT * FROM conferenc JOIN category " +
            "ON category_id=id_category JOIN status ON conferenc_status_id=id_status WHERE id_conferenc=?";
    private static final String FIND_NAME_CONFERENC = "SELECT * FROM conferenc JOIN category " +
            "ON category_id=id_category JOIN status ON conferenc_status_id=id_status WHERE conferenc.name=?";
    private static final String CONFERENC_DELETE = "DELETE FROM conferenc WHERE id_conferenc=?";
    private static final String FIND_FOR_DIPLICATE_CONFERENC = "SELECT * FROM conferenc JOIN category " +
            "ON category_id=id_category JOIN status ON conferenc_status_id=id_status WHERE name=? " +
            "&& description=? && category_id=? && conferenc_status_id=1";

    /**
     * Logger for this dao
     */
    private static final Logger LOG = LogManager.getLogger(ConferencDaoImpl.class);
    /**
     * Connection pool for this dao
     */
    private final ConnectionPool connectionPool;

    /**
     * Constructor - creating a new object
     *
     * @param connectionPool connectionPool for this dao
     */
    public ConferencDaoImpl(ConnectionPool connectionPool) {
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
    public boolean create(String name, String description, Long idCategory) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_CONFERENC)) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setLong(3, idCategory);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", CREATE_CONFERENC);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Find for duplicate conferenc
     *
     * @param name        name for new conferenc
     * @param description description for new conferenc
     * @param idCategory  id category for new conferenc
     * @return boolean result of operation
     */
    @Override
    public boolean findForDuplicateConferenc(String name, String description, Long idCategory) throws DaoException {
        Optional<Conferenc> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_FOR_DIPLICATE_CONFERENC)) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setLong(3, idCategory);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Conferenc conferenc = extractConferenc(resultSet);
                productOptional = Optional.of(conferenc);
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_FOR_DIPLICATE_CONFERENC);
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
     * Change status conferenc by id
     *
     * @param idConferenc id conferenc
     * @param idStatus    status conferenc
     * @return boolean result of operation
     */
    @Override
    public boolean changeStatus(Long idConferenc, Long idStatus) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_CONFERENC)) {
            statement.setLong(2, idConferenc);
            statement.setLong(1, idStatus);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", UPDATE_STATUS_CONFERENC);
            throw new DaoException(e);
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
    public boolean updateDescription(Long id, String description) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_DESCRIPTION_BY_CONFERENC)) {
            statement.setLong(2, id);
            statement.setString(1, description);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", ADD_DESCRIPTION_BY_CONFERENC);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Read all conferenc
     *
     * @return List Conferenc
     */
    @Override
    public List<Conferenc> readAll() throws EntityExtractionFailedException, DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_CONFERENC)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Conferenc> extractor = ConferencDaoImpl::extractConferenc;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ALL_CONFERENC);
            throw new DaoException(e);
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
    public List<Conferenc> readAllActive() throws EntityExtractionFailedException, DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_CONFERENC_ACTIVE)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Conferenc> extractor = ConferencDaoImpl::extractConferenc;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ALL_CONFERENC_ACTIVE);
            throw new DaoException(e);
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
    public Optional<Conferenc> readById(Long id) throws DaoException {
        Optional<Conferenc> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ID_CONFERENC)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Conferenc conferenc = extractConferenc(resultSet);
                productOptional = Optional.of(conferenc);
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ID_CONFERENC);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
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
    public List<Conferenc> findByName(String name) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_NAME_CONFERENC)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Conferenc> extractor = ConferencDaoImpl::extractConferenc;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_NAME_CONFERENC);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
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
    public boolean delete(Long id) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CONFERENC_DELETE)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", CONFERENC_DELETE);
            throw new DaoException(e);
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
    public static ConferencDaoImpl getInstance() {
        return ConferencDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final ConferencDaoImpl INSTANCE = new ConferencDaoImpl(ConnectionPool.locking());
    }
}
