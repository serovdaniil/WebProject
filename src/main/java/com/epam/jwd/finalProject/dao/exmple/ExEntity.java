package com.epam.jwd.finalProject.dao.exmple;

import com.epam.jwd.finalProject.model.Entity;

import java.util.List;
import java.util.Optional;

public interface ExEntity<T extends Entity> {


    List<T> read();

    Optional<T> read(Long id);



    boolean delete(Long id);
}
