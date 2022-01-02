package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.api.SectionConferencDao;
import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;
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
 * The section conferenc dao
 *
 * @author Daniil Serov
 */
public class SectionConferencDaoImpl implements SectionConferencDao {
    /**
     * SQL query for this dao
     */
    private static final String CREATE_SECTION_CONFERENC = "INSERT INTO section_conferenc " +
            "(name,description,conferenc_id,section_conferenc_status_id) values(?,?,?,1)";
    private static final String ADD_DESCRIPTION_BY_SECTION_CONFERENC = "UPDATE section_conferenc SET description = ?" +
            " WHERE id_section_conferenc = ?";
    private static final String UPDATE_STATUS_SECTION_CONFERENC = "UPDATE section_conferenc " +
            "SET section_conferenc_status_id =? WHERE id_section_conferenc = ?";
    private static final String UPDATE_STATUS_SECTION_CONFERENC_AFTER_CONFERENC = "UPDATE section_conferenc " +
            "SET section_conferenc_status_id = ? WHERE conferenc_id = ?";
    private static final String FIND_ALL_SECTION_CONFERENC = "SELECT * FROM section_conferenc JOIN status " +
            "ON section_conferenc_status_id=id_status JOIN conferenc ON conferenc_id=id_conferenc JOIN category " +
            "ON category_id=id_category ";
    private static final String FIND_ID_SECTION_CONFERENC = "SELECT * FROM section_conferenc JOIN status " +
            "ON section_conferenc_status_id=id_status JOIN conferenc " +
            "ON conferenc_id=id_conferenc JOIN category ON category_id=id_category WHERE id_section_conferenc=?";
    private static final String FIND_NAME_SECTION_CONFERENC = "SELECT * FROM section_conferenc JOIN status " +
            "ON section_conferenc_status_id=id_status JOIN conferenc " +
            "ON conferenc_id=id_conferenc JOIN category ON category_id=id_category WHERE section_conferenc.name=?";
    private static final String SECTION_CONFERENC_DELETE = "DELETE FROM section_conferenc  WHERE id_section_conferenc=?";
    private static final String FIND_SECTION_CONFERENCES_IN_CONFERENC_BY_ID_ACTIVE = "SELECT * FROM section_conferenc " +
            "JOIN conferenc ON conferenc_id=id_conferenc JOIN category ON category_id=id_category JOIN status " +
            "ON section_conferenc_status_id=id_status " +
            "WHERE section_conferenc.conferenc_id=? && section_conferenc_status_id=1";
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
    public SectionConferencDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Create section conferenc
     *
     * @param name        name for new section conferenc
     * @param description description for new section conferenc
     * @param idConferenc id conferenc for new section conferenc
     * @return boolean result of operation
     */
    @Override
    public boolean create(String name, String description, Long idConferenc) throws DaoException {
        boolean result = false;
        LOG.info("Start create and add new section_conferenc");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SECTION_CONFERENC)) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setLong(3, idConferenc);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End create and add new section_question");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", CREATE_SECTION_CONFERENC);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Change status by id section conferenc
     *
     * @param idSectionConferenc id for section conferenc
     * @param idStatus           id status for section conferenc
     * @return boolean result of operation
     */
    @Override
    public boolean changeStatus(Long idSectionConferenc, Long idStatus) throws DaoException {
        LOG.info("Start update status section conferenc");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_SECTION_CONFERENC)) {
            statement.setLong(2, idSectionConferenc);
            statement.setLong(1, idStatus);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End update status section conferenc");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}",UPDATE_STATUS_SECTION_CONFERENC);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Change status after update status conferenc by id conferenc
     *
     * @param idConferenc id for conferenc
     * @return boolean result of operation
     */
    @Override
    public boolean changeStatusAfterUpdateConferenc(Long idConferenc) throws DaoException {
        LOG.info("Start update status section conferences after update conferenc");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_SECTION_CONFERENC_AFTER_CONFERENC)) {
            statement.setLong(1, 2);
            statement.setLong(2, idConferenc);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End update status section conferences after update conferenc");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", UPDATE_STATUS_SECTION_CONFERENC_AFTER_CONFERENC);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Update description by id section conferenc
     *
     * @param id          id for section conferenc
     * @param description description for section conferenc
     * @return boolean result of operation
     */
    @Override
    public boolean updateDescription(Long id, String description) throws DaoException {
        LOG.info("Start add description by section_conferenc");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_DESCRIPTION_BY_SECTION_CONFERENC)) {
            statement.setLong(2, id);
            statement.setString(1, description);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End add description by section_conferenc");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", ADD_DESCRIPTION_BY_SECTION_CONFERENC);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Read all section conferenc
     *
     * @return List section conferenc
     */
    @Override
    public List<SectionConferenc> readAll() throws EntityExtractionFailedException, DaoException {
        LOG.info("Start readAll conferenc");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SECTION_CONFERENC)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<SectionConferenc> extractor = SectionConferencDaoImpl::extractSectionConferenc;
            LOG.info("End readAll conferenc");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ALL_SECTION_CONFERENC);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    /**
     * Find section conferenc by id
     *
     * @param id id for section conferenc
     * @return Section conferenc
     */
    @Override
    public Optional<SectionConferenc> readById(Long id) throws DaoException {
        LOG.info("Start readById section_conferenc");
        Optional<SectionConferenc> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ID_SECTION_CONFERENC)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                SectionConferenc sectionConferenc = extractSectionConferenc(resultSet);
                productOptional = Optional.of(sectionConferenc);
            }
            LOG.info("End readById section_conferenc");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ID_SECTION_CONFERENC);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return productOptional;
    }

    /**
     * Find section conferenc by name
     *
     * @param name name for section conferenc
     * @return List section conferenc
     */
    @Override
    public List<SectionConferenc> findByName(String name) throws DaoException {
        LOG.info("Start find name by section_conferenc");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_NAME_SECTION_CONFERENC)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<SectionConferenc> extractor = SectionConferencDaoImpl::extractSectionConferenc;
            LOG.info("End find name by section_conferenc");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_NAME_SECTION_CONFERENC);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    /**
     * Remove section conferenc by id
     *
     * @param id id for section conferenc
     * @return boolean result of operation
     */
    @Override
    public boolean delete(Long id) throws DaoException {
        LOG.info("Start delete conferenc");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SECTION_CONFERENC_DELETE)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
            LOG.info("End delete conferenc");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SECTION_CONFERENC_DELETE);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Find section conferenc by id conferenc
     *
     * @param id id for conferenc
     * @return List section conferenc
     */
    @Override
    public List<SectionConferenc> findSectionConferencesInConferencById(Long id) throws DaoException {
        LOG.info("Start find section_conferences in conferenc by id");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FIND_SECTION_CONFERENCES_IN_CONFERENC_BY_ID_ACTIVE)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<SectionConferenc> extractor = SectionConferencDaoImpl::extractSectionConferenc;
            LOG.info("End find section_conferences in conferenc by id");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_SECTION_CONFERENCES_IN_CONFERENC_BY_ID_ACTIVE);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    /**
     * Get section conferenc
     *
     * @return Section conferenc
     */
    private static SectionConferenc extractSectionConferenc(ResultSet resultSet) throws EntityExtractionFailedException {
        try {
            return new SectionConferenc(
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
    public static SectionConferencDaoImpl getInstance() {
        return SectionConferencDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final SectionConferencDaoImpl INSTANCE = new SectionConferencDaoImpl(ConnectionPool.locking());
    }
}
