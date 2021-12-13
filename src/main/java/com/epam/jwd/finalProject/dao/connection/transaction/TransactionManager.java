package com.epam.jwd.finalProject.dao.connection.transaction;

import java.util.Optional;

public interface TransactionManager {
    void initTransaction();

    void commitTransaction();

    boolean isTransactionActive();

    Optional<TransactionId> getTransactionId();

    static TransactionManager instance() {
        return ThreadLocalTransactionManager.INSTANCE;
    }
}