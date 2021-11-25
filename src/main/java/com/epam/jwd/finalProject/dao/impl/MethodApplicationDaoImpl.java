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

public class MethodApplicationDaoImpl implements ApplicationDao {

    private static final Logger LOG = LogManager.getLogger(MethodApplicationDaoImpl.class);

    private final ConnectionPool connectionPool;
    //change connectionPool.locking()
    public MethodApplicationDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

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

    @Override
    public List<Application> findBySectionConferencId(Long idSectionConferenc) {
        LOG.info("Start find id by section_conferenc");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ID_SECTION_CONFERENC_BY_CAPPLICATION)) {
            statement.setLong(1, idSectionConferenc);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = MethodApplicationDaoImpl::extractApplication;
            LOG.info("End find id by section_conferenc");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ID_SECTION_CONFERENC_BY_CAPPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Application> findBySectionConferencName(String nameSectionConferenc) {
        LOG.info("Start find name by section_conferenc");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_NAME_SECTION_CONFERENC_BY_CAPPLICATION)) {
            statement.setString(1, nameSectionConferenc);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = MethodApplicationDaoImpl::extractApplication;
            LOG.info("End find name by section_conferenc");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_NAME_SECTION_CONFERENC_BY_CAPPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

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

    @Override
    public List<Application> findByFirstName(String firstName) {
        LOG.info("Start find firstName by account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_FIRSTNAME_ACCOUNT_BY_CAPPLICATION)) {
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = MethodApplicationDaoImpl::extractApplication;
            LOG.info("End find firstName by account");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_FIRSTNAME_ACCOUNT_BY_CAPPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Application> findByLastName(String lastName) {
        LOG.info("Start find lastName by account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_LASTNAME_ACCOUNT_BY_CAPPLICATION)) {
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = MethodApplicationDaoImpl::extractApplication;
            LOG.info("End find lastName by account");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_LASTNAME_ACCOUNT_BY_CAPPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Application> findByEmail(String email) {
        LOG.info("Start find email by account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_EMAIL_ACCOUNT_BY_CAPPLICATION)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = MethodApplicationDaoImpl::extractApplication;
            LOG.info("End find email by account");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_EMAIL_ACCOUNT_BY_CAPPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Application> findByLogin(String login) {
        LOG.info("Start find login by account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_LOGIN_ACCOUNT_BY_CAPPLICATION)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = MethodApplicationDaoImpl::extractApplication;
            LOG.info("End find login by account");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_LOGIN_ACCOUNT_BY_CAPPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

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

    @Override
    public List<Application> findByConferencId(Long idConferenc) {
        LOG.info("Start find IdConferenc by account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ID_CONFERENC_BY_CAPPLICATION)) {
            statement.setLong(1, idConferenc);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = MethodApplicationDaoImpl::extractApplication;
            LOG.info("End find IdConferenc by account");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ID_CONFERENC_BY_CAPPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Application> findByConferencName(String nameConferenc) {
        LOG.info("Start find nameConferenc by account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_NAME_CONFERENC_BY_CAPPLICATION)) {
            statement.setString(1, nameConferenc);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = MethodApplicationDaoImpl::extractApplication;
            LOG.info("End find nameConferenc by account");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_NAME_CONFERENC_BY_CAPPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Application> findByCategoryId(Long idCategory) {
        LOG.info("Start find idCategory by account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ID_CATEGORY_BY_CAPPLICATION)) {
            statement.setLong(1, idCategory);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = MethodApplicationDaoImpl::extractApplication;
            LOG.info("End find idCategory by account");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ID_CATEGORY_BY_CAPPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Application> findByCategoryName(String nameCategory) {
        LOG.info("Start find nameCategory by account");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_NAME_CATEGORY_BY_CAPPLICATION)) {
            statement.setString(1, nameCategory);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Application> extractor = MethodApplicationDaoImpl::extractApplication;
            LOG.info("End find nameCategory by account");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_NAME_CATEGORY_BY_CAPPLICATION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

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
                                            resultSet.getString("name_category")))));
        } catch (SQLException e) {
            throw new EntityExtractionFailedException();
        }
    }
    public static MethodApplicationDaoImpl getInstance() {
        return MethodApplicationDaoImpl.Holder.INSTANCE;
    }

    private static class Holder {
        public static final MethodApplicationDaoImpl INSTANCE = new MethodApplicationDaoImpl(ConnectionPool.locking());
    }
}