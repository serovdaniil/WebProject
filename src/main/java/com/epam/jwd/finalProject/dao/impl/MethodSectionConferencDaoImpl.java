package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.api.SectionConferencDao;
import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.Category;
import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.model.SectionConferenc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MethodSectionConferencDaoImpl implements SectionConferencDao {

    private static final Logger LOG = LogManager.getLogger(MethodConferencDaoImpl.class);
    private final ConnectionPool connectionPool;
    //change connectionPool.locking()
    public MethodSectionConferencDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean create(String name, String discription, Long idConferenc) {
        boolean result = false;
        LOG.info("Start create and add new section_conferenc");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CREATE_SECTION_CONFERENC)) {
            statement.setString(1, name);
            statement.setString(2, discription);
            statement.setLong(3, idConferenc);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End create and add new section_question");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.CREATE_SECTION_CONFERENC);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    @Override
    public boolean updateDescription(Long id, String description) {
        LOG.info("Start add description by section_conferenc");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_DESCRIPTION_BY_SECTION_CONFERENC)) {
            statement.setLong(2, id);
            statement.setString(1, description);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End add description by section_conferenc");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.ADD_DESCRIPTION_BY_SECTION_CONFERENC);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    @Override
    public List<SectionConferenc> readAll() throws EntityExtractionFailedException {
        LOG.info("Start readAll conferenc");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_SECTION_CONFERENC)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<SectionConferenc> extractor = MethodSectionConferencDaoImpl::extractSectionConferenc;
            LOG.info("End readAll conferenc");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ALL_SECTION_CONFERENC);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<SectionConferenc> readById(Long id) {
        LOG.info("Start readById section_conferenc");
        Optional<SectionConferenc> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ID_SECTION_CONFERENC)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                SectionConferenc sectionConferenc = extractSectionConferenc(resultSet);
                productOptional = Optional.of(sectionConferenc);
            }
            LOG.info("End readById section_conferenc");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ID_SECTION_CONFERENC);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return productOptional;
    }

    @Override
    public List<SectionConferenc> findByName(String name) {
        LOG.info("Start find name by section_conferenc");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_NAME_SECTION_CONFERENC)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<SectionConferenc> extractor = MethodSectionConferencDaoImpl::extractSectionConferenc;
            LOG.info("End find name by section_conferenc");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_NAME_SECTION_CONFERENC);
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
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SECTION_CONFERENC_DELETE)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
            LOG.info("End delete conferenc");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.SECTION_CONFERENC_DELETE);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
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

    @Override
    public List<SectionConferenc> findSectionConferencesInConferencById(Long id) {
        LOG.info("Start find section_conferences in conferenc by id");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_SECTION_CONFERENCES_IN_CONFERENC_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<SectionConferenc> extractor = MethodSectionConferencDaoImpl::extractSectionConferenc;
            LOG.info("End find section_conferences in conferenc by id");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_NAME_SECTION_CONFERENC);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    public static MethodSectionConferencDaoImpl getInstance() {
        return MethodSectionConferencDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final MethodSectionConferencDaoImpl INSTANCE = new MethodSectionConferencDaoImpl(ConnectionPool.locking());
    }
}
