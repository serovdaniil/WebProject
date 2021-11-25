package com.epam.jwd.finalProject.dao.exmple;

import com.epam.jwd.finalProject.model.User;

import java.util.Optional;

public interface DikeDao extends ExEntity<User>{


    Optional<Long> findUserIdByBikeId(Long id);

}
