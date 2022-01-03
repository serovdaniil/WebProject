package com.epam.jwd.finalProject.dao.impl;

import com.epam.jwd.finalProject.dao.api.QuestionDao;
import com.epam.jwd.finalProject.dao.connection.ConnectionPool;
import com.epam.jwd.finalProject.dao.exception.DaoException;
import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.Question;
import com.epam.jwd.finalProject.model.Role;
import com.epam.jwd.finalProject.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The question dao
 *
 * @author Daniil Serov
 */
public class QuestionDaoImpl implements QuestionDao {
    /**
     * SQL query for this dao
     */
    private static final String CREATE_QUESTION = "INSERT INTO final_task.question (question,answer,date,user_id) " +
            "values(?,?,?,?)";
    private static final String ADD_ANSWER = "UPDATE final_task.question SET final_task.question.answer = ? " +
            "WHERE final_task.question.id = ?";
    private static final String FIND_ALL_QUESTION = "SELECT * FROM final_task.question JOIN final_task.user " +
            "ON user_id=id_user " +
            "JOIN final_task.role ON role_id=id_role";
    private static final String FIND_ID_QUESTION = "SELECT * FROM final_task.question JOIN final_task.user " +
            "ON user_id=id_user " +
            "JOIN final_task.role ON role_id=id_role WHERE final_task.question.id = ?";
    private static final String FIND_ACCOUNT_ID_BY_QUESTION = "SELECT * FROM final_task.question JOIN final_task.user " +
            "ON user_id=id_user JOIN final_task.role ON role_id=id_role WHERE final_task.question.user_id = ?";
    private static final String QUESTION_DELETE = "DELETE FROM final_task.question WHERE final_task.question.id = ?";
    private static final String FIND_DUPLICATE_QUESTION = "SELECT * FROM final_task.question JOIN final_task.user " +
            "ON user_id=id_user JOIN final_task.role ON role_id=id_role WHERE final_task.question.user_id = ? " +
            "&& final_task.question.question = ?";
    /**
     * Connection pool for this dao
     */
    private final ConnectionPool connectionPool;

    /**
     * Constructor - creating a new object
     *
     * @param connectionPool connectionPool for this dao
     */
    public QuestionDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Logger for this dao
     */
    private static final Logger LOG = LogManager.getLogger(QuestionDaoImpl.class);

    /**
     * Create question
     *
     * @param name   name for new question
     * @param date   date for new question
     * @param idUser id user for new question
     * @return boolean result of operation
     */
    @Override
    public boolean create(String name, Date date, Long idUser) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_QUESTION)) {
            statement.setString(1, name);
            statement.setString(2, "");
            statement.setDate(3, (java.sql.Date) date);
            statement.setLong(4, idUser);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", CREATE_QUESTION);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Add answer by id question
     *
     * @param id     id question
     * @param answer answer for new question
     * @return boolean result of operation
     */
    @Override
    public boolean addAnswer(Long id, String answer) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_ANSWER)) {
            statement.setLong(2, id);
            statement.setString(1, answer);
            int rowCount = statement.executeUpdate();
            if (rowCount != 0) {
                result = true;
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", ADD_ANSWER);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Find for duplicate question
     *
     * @param idAccount id question
     * @param question  question for new question
     * @return boolean result of operation
     */
    @Override
    public boolean findForDuplicateQuestion(Long idAccount, String question) throws DaoException {
        Optional<Question> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_DUPLICATE_QUESTION)) {
            statement.setLong(1, idAccount);
            statement.setString(2, question);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Question questionOne = extractQuestion(resultSet);
                productOptional = Optional.of(questionOne);
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_DUPLICATE_QUESTION);
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
     * Read all questions
     *
     * @return List questions
     */
    @Override
    public List<Question> readAll() throws EntityExtractionFailedException, DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUESTION)) {
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Question> extractor = QuestionDaoImpl::extractQuestion;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ALL_QUESTION);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    /**
     * Read question by id
     *
     * @param id id question
     * @return Question
     */
    @Override
    public Optional<Question> readById(Long id) throws DaoException {
        Optional<Question> productOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ID_QUESTION)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Question question = extractQuestion(resultSet);
                productOptional = Optional.of(question);
            }
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ID_QUESTION);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return productOptional;
    }

    /**
     * Find questions by id user
     *
     * @param id id user
     * @return List questions
     */
    @Override
    public List<Question> findAccountIdByQuestion(Long id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ACCOUNT_ID_BY_QUESTION)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            ResultSetExtractor<Question> extractor = QuestionDaoImpl::extractQuestion;
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", FIND_ACCOUNT_ID_BY_QUESTION);
            throw new DaoException(e);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    /**
     * Remove question by id
     *
     * @param id id question
     * @return boolean result of operation
     */
    @Override
    public boolean delete(Long id) throws DaoException {
        boolean result = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUESTION_DELETE)) {
            statement.setLong(1, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", QUESTION_DELETE);
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * Get question
     *
     * @return Question
     */
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
                            Role.of(resultSet.getString("account_role"))));
        } catch (SQLException e) {
            throw new EntityExtractionFailedException();
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static QuestionDaoImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final QuestionDaoImpl INSTANCE = new QuestionDaoImpl(ConnectionPool.locking());
    }

}
