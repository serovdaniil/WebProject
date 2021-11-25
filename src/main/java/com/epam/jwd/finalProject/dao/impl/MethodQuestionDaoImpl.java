package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.api.QuestionDao;
import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.connection.LockingConnectionPool;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.model.Role;
import com.epam.jwd.finalProject.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MethodQuestionDaoImpl implements QuestionDao {

    private final ConnectionPool connectionPool;
    //change connectionPool.locking()
    public MethodQuestionDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    private static final Logger LOG = LogManager.getLogger(MethodQuestionDaoImpl.class);

    @Override
    public boolean create(Question question) {
        boolean result = false;
        LOG.info("Start create and add new question");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CREATE_QUESTION)) {
            statement.setString(1, question.getQuestion());
            statement.setString(2, question.getAnswer());
            statement.setDate(3, (Date) question.getDate());
            statement.setLong(4, question.getUser().getId());
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End create and add new question");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.CREATE_QUESTION);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    @Override
    public boolean addAnswer(Long id, String answer) {
        LOG.info("Start add answer by question");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.ADD_ANSWER)) {
            statement.setLong(2, id);
            statement.setString(1, answer);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End add answer by question");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.ADD_ANSWER);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    @Override
    public boolean updateQuestion(Long id, String question) {
        LOG.info("Start update name question");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_QUESTION)) {
            statement.setLong(2, id);
            statement.setString(1, question);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
            LOG.info("End update name question");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.UPDATE_QUESTION);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    @Override
    public List<Question> readAll() throws EntityExtractionFailedException {
        LOG.info("Start readAll question");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ALL_QUESTION)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Question> extractor = MethodQuestionDaoImpl::extractQuestion;
            LOG.info("End readAll question");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ALL_QUESTION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Question> readById(Long id) {
        LOG.info("Start readById question");
        Optional<Question> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ID_QUESTION)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Question question = extractQuestion(resultSet);
                productOptional = (Optional<Question>) Optional.of(question);
            }
            LOG.info("End readById question");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ID_QUESTION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return productOptional;
    }

    @Override
    public List<Question> findAccountIdByQuestion(Long id) {
        LOG.info("Start find account with Id by question");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ACCOUNT_ID_BY_QUESTION)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Question> extractor = MethodQuestionDaoImpl::extractQuestion;
            LOG.info("End find account with Id by question");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_ACCOUNT_ID_BY_QUESTION);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Question> findByFirstName(String firstName) {
        LOG.info("Start find account by name to the question");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_QUESTION_BY_ACCOUNT_FIRSTNAME)) {
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Question> extractor = MethodQuestionDaoImpl::extractQuestion;
            LOG.info("End find account by name to the question");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_QUESTION_BY_ACCOUNT_FIRSTNAME);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Question> findByLastName(String lastName) {
        LOG.info("Start find account by last name to the question");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_QUESTION_BY_ACCOUNT_LASTNAME)) {
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Question> extractor = MethodQuestionDaoImpl::extractQuestion;
            LOG.info("End find account by last name to the question");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_QUESTION_BY_ACCOUNT_LASTNAME);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Question> findByEmail(String email) {
        LOG.info("Start find account by email to the question");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_QUESTION_BY_ACCOUNT_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Question> extractor = MethodQuestionDaoImpl::extractQuestion;
            LOG.info("End find account by email to the question");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_QUESTION_BY_ACCOUNT_EMAIL);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Question> findByLogin(String login) {
        LOG.info("Start find account by login to the question");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_QUESTION_BY_ACCOUNT_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Question> extractor = MethodQuestionDaoImpl::extractQuestion;
            LOG.info("End find account by login to the question");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_QUESTION_BY_ACCOUNT_LOGIN);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Question> findByAnswer(String answer) {
        LOG.info("Start find answer by question");
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_QUESTION_BY_ACCOUNT_ANSWER)) {
            statement.setString(1, answer);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Question> extractor = MethodQuestionDaoImpl::extractQuestion;
            LOG.info("End find answer by question");
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.FIND_QUESTION_BY_ACCOUNT_ANSWER);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return Collections.emptyList();
    }

    @Override
    public boolean delete(Long id) {
        LOG.info("Start delete question");
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.QUESTION_DELETE)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
            LOG.info("End delete question");
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", SqlQuery.QUESTION_DELETE);
        } catch (NullPointerException e) {
            LOG.error(e);
        }
        return result;
    }

    private static Question extractQuestion(ResultSet resultSet) throws EntityExtractionFailedException {
        try {
            return new Question(
                    resultSet.getLong("id"),
                    resultSet.getString("question"),
                    resultSet.getString("answer"),
                    resultSet.getDate("date"),
                    new User(resultSet.getLong("user_id"),
                            resultSet.getString("login"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            Role.of(resultSet.getString("account_role")))
                    );
        } catch (SQLException e) {
            throw new EntityExtractionFailedException();
        }
    }
    public static MethodQuestionDaoImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final MethodQuestionDaoImpl INSTANCE = new MethodQuestionDaoImpl(ConnectionPool.locking());
    }

}
