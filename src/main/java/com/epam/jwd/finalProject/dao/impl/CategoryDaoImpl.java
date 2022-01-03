package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.api.CategoryDao;
import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
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
 * The category dao
 *
 * @author Daniil Serov
 */
public class CategoryDaoImpl implements CategoryDao {
    /**
     * SQL query for this dao
     */
    private static final String CREATE_CATEGORY = "INSERT INTO category (name_category) values(?)";

    private static final String UPDATE_NAME_BY_ID_CATEGORY = "UPDATE category SET name_category= ? WHERE id_category = ?";

    private static final String FIND_ALL_CATEGORY = "SELECT * FROM category";

    private static final String FIND_ID_CATEGORY = "SELECT * FROM category WHERE id_category=?";

    private static final String FIND_BY_ID_CONFERENC_IN_CATEGORY = "SELECT * FROM conferenc JOIN category " +
            "ON category_id=id_category JOIN status ON conferenc_status_id=id_status WHERE id_category=?";

    private static final String FIND_BY_ID_SECTIONCONFERENC_IN_CATEGORY = "SELECT * FROM section_conferenc " +
            "JOIN conferenc ON conferenc_id=id_conferenc JOIN category ON category_id=id_category JOIN status " +
            "ON conferenc_status_id=id_status WHERE id_category=?";

    private static final String CATEGORY_DELETE = "DELETE FROM category WHERE id_category=?";

    private static final String FIND_FOR_DUPLICATE_CATEGORY = "SELECT * FROM category WHERE name_category=?";

    /**
     * Logger for this dao
     */
    private static final Logger LOG = LogManager.getLogger(CategoryDaoImpl.class);
    /**
     * Connection pool for this dao
     */
    private final ConnectionPool connectionPool;

    /**
     * Constructor - creating a new object
     *
     * @param connectionPool connectionPool for this dao
     */
    public CategoryDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Create category
     *
     * @param name name for new category
     * @return boolean result of operation
     */
    @Override
    public boolean create(String name) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_CATEGORY)) {
            statement.setString(1, name);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", CREATE_CATEGORY);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Find for duplicate category
     *
     * @param name name for new category
     * @return boolean result of operation
     */
    @Override
    public boolean findForDuplicateCategory(String name) throws DaoException {
        Optional<Category> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_FOR_DUPLICATE_CATEGORY)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Category category = extractCategory(resultSet);
                productOptional = Optional.of(category);
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_FOR_DUPLICATE_CATEGORY);
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
     * Change name for category
     *
     * @param id   id category
     * @param name new name for category
     * @return boolean result of operation
     */
    @Override
    public boolean changeName(Long id, String name) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_NAME_BY_ID_CATEGORY)) {
            statement.setLong(2, id);
            statement.setString(1, name);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", UPDATE_NAME_BY_ID_CATEGORY);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Find all categories
     *
     * @return List category
     */
    @Override
    public List<Category> findAll() throws EntityExtractionFailedException, DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_CATEGORY)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Category> extractor = CategoryDaoImpl::extractCategory;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ALL_CATEGORY);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    /**
     * Find category by id
     *
     * @param id id category
     * @return Category
     */
    @Override
    public Optional<Category> findById(Long id) throws DaoException {
        Optional<Category> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ID_CATEGORY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Category category = extractCategory(resultSet);
                productOptional = Optional.of(category);
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ID_CATEGORY);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return productOptional;
    }

    /**
     * Find conferences by id category
     *
     * @param id id category
     * @return List conferences
     */
    @Override
    public List<Conferenc> findConferencInIdCategory(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_CONFERENC_IN_CATEGORY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Conferenc> extractor = CategoryDaoImpl::extractConferenc;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_BY_ID_CONFERENC_IN_CATEGORY);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    /**
     * Find section conferences by id category
     *
     * @param id id category
     * @return List section conferences
     */
    @Override
    public List<SectionConferenc> findSectionConferencInIdCategory(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SECTIONCONFERENC_IN_CATEGORY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<SectionConferenc> extractor = CategoryDaoImpl::extractSectionConferenc;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_BY_ID_SECTIONCONFERENC_IN_CATEGORY);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    /**
     * Remove category by id
     *
     * @param id id category
     * @return boolean result of operation
     */
    @Override
    public boolean delete(Long id) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CATEGORY_DELETE)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", CATEGORY_DELETE);
            throw new DaoException(e);
        }
        return result;
    }

    private static Category extractCategory(ResultSet resultSet) throws EntityExtractionFailedException {
        try {
            return new Category(
                    resultSet.getLong("id_category"),
                    resultSet.getString("name_category"));
        } catch (SQLException e) {
            throw new EntityExtractionFailedException();
        }
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
    public static CategoryDaoImpl getInstance() {
        return CategoryDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final CategoryDaoImpl INSTANCE = new CategoryDaoImpl(ConnectionPool.locking());
    }
}
