package com.epam.jwd.finalProject.dao.impl;
import com.epam.jwd.finalProject.dao.api.ConferencDao;
import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MethodConferencDaoImpl implements ConferencDao {

    private static final Logger LOG = LogManager.getLogger(MethodConferencDaoImpl.class);

    private final ConnectionPool connectionPool;
    //change connectionPool.locking()
    public MethodConferencDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean create(String name,String description,Long idCategory) {
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
    public static MethodConferencDaoImpl getInstance() {
        return MethodConferencDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final MethodConferencDaoImpl INSTANCE = new MethodConferencDaoImpl(ConnectionPool.locking());
    }
}
