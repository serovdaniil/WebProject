package com.epam.jwd.finalProject.dao.impl;
import com.epam.jwd.finalProject.dao.api.CategoryDao;
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

public class MethodCategoryDaoImpl implements CategoryDao {

    private static final Logger LOG = LogManager.getLogger(MethodCategoryDaoImpl.class);
    private final ConnectionPool connectionPool;

    //change connectionPool.locking()
    public MethodCategoryDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public boolean create(String name) {
        boolean result = false;
        LOG.info("Start create category");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CREATE_CATEGORY)) {
            statement.setString(1, name);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End create category");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.CREATE_APPLICATION);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    @Override
    public boolean changeName(Long id, String name) {
        LOG.info("START update name by ID Category");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_NAME_BY_ID_CATEGORY)) {
            statement.setLong(2, id);
            statement.setString(1, name);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("END update name by ID Category");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_NAME_BY_ID_CATEGORY);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    @Override
    public List<Category> readAll() throws EntityExtractionFailedException {

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_CATEGORY)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Category> extractor = MethodCategoryDaoImpl::extractCategory;

            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ALL_CATEGORY);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Category> readById(Long id) {
        LOG.info("Start readById category");
        Optional<Category> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ID_CATEGORY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Category category = extractCategory(resultSet);
                productOptional = Optional.of(category);
            }
            LOG.info("End readById category");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ID_CATEGORY);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return productOptional;
    }

    @Override
    public List<Conferenc> findConferencInIdCategory(Long id) {
        LOG.info("Start find conferenc in categori by ID");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BY_ID_CONFERENC_IN_CATEGORY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Conferenc> extractor = MethodCategoryDaoImpl::extractConferenc;
            LOG.info("End find conferenc in categori by ID");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_BY_ID_CONFERENC_IN_CATEGORY);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Conferenc> findConferencInNameCategory(String name) {
        LOG.info("Start find conferenc in categori by name");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BY_NAME_CONFERENC_IN_CATEGORY)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Conferenc> extractor = MethodCategoryDaoImpl::extractConferenc;
            LOG.info("End find conferenc in categori by name");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_BY_NAME_CONFERENC_IN_CATEGORY);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<SectionConferenc> findSectionConferencInIdCategory(Long id) {
        LOG.info("Start find section conferenc in categori by ID");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BY_ID_SECTIONCONFERENC_IN_CATEGORY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<SectionConferenc> extractor = MethodCategoryDaoImpl::extractSectionConferenc;
            LOG.info("End find section conferenc in categori by ID");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_BY_ID_SECTIONCONFERENC_IN_CATEGORY);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<SectionConferenc> findSectionConferencInNameCategory(String name) {
        LOG.info("Start find section conferenc in categori by Name");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_BY_NAME_SECTIONCONFERENC_IN_CATEGORY)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<SectionConferenc> extractor = MethodCategoryDaoImpl::extractSectionConferenc;
            LOG.info("End find section conferenc in categori by Name");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_BY_NAME_SECTIONCONFERENC_IN_CATEGORY);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean delete(Long id) {
        LOG.info("Start delete category");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CATEGORY_DELETE)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
            LOG.info("End delete category");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.CATEGORY_DELETE);
        } catch (NullPointerException e) {
            LOG.error(e);
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

    private static Conferenc extractConferenc(ResultSet resultSet) throws EntityExtractionFailedException {
        try {
            return new Conferenc(
                    resultSet.getLong("id_conferenc"),
                    resultSet.getString("name"),
                    resultSet.getString("description"),
                    new Category(resultSet.getLong("id_category"),
                            resultSet.getString("name_category")));
        } catch (SQLException e) {
            throw new EntityExtractionFailedException();
        }
    }

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
                                    resultSet.getString("name_category"))));
        } catch (SQLException e) {
            throw new EntityExtractionFailedException();
        }
    }

    public static MethodCategoryDaoImpl getInstance() {
        return MethodCategoryDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final MethodCategoryDaoImpl INSTANCE = new MethodCategoryDaoImpl(ConnectionPool.locking());
    }
}
