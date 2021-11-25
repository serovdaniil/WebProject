package com.epam.jwd.finalProject.dao.exmple;

import com.epam.jwd.finalProject.dao.connection.LockingConnectionPool;
import com.epam.jwd.finalProject.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public final class MethodBikeDao extends CommonDao<User> implements DikeDao{
    public static final String UPDATE_LOGIN_BY_ID_ACCOUNT = "UPDATE user SET login=? WHERE id_user = ?";

    private static final LockingConnectionPool CONNECTION_POOL = new LockingConnectionPool();
    public MethodBikeDao() {
        super();
    }


    @Override
    public Optional<Long> findUserIdByBikeId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> read() {
        return null;
    }

    @Override
    public Optional<User> read(Long id) {
        return Optional.empty();
    }


    @Override
    public boolean delete(Long id) {
        return false;
    }


    @Override
    protected User extractResult(ResultSet rs) throws SQLException {
        return null;
    }
}
