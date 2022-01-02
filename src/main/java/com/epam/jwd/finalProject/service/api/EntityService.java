package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Entity;
import com.epam.jwd.finalProject.service.exception.ServiceException;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;
import java.util.Optional;
/**
 * The interface entity service
 *
 * @author Daniil Serov
 */
public interface EntityService<T extends Entity> {

    /**
     * Find all
     *
     * @return boolean result of operation
     */
    List<T> findAll() throws ServiceException;

    /**
     * Find by id
     *
     * @param id
     * @return boolean result of operation
     * @throws ValidationException
     */
    Optional<T> findId(Long id) throws ValidationException, ServiceException;

    /**
     * Remove by id
     *
     * @param id
     * @return boolean result of operation
     * @throws ValidationException
     */
    boolean remove(Long id) throws ValidationException, ServiceException;
}
