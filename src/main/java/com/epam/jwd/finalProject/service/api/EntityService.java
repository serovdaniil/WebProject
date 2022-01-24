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
     * @param pageNumber selected page
     * @return boolean result of operation
     */
    List<T> findAll(Long pageNumber) throws ServiceException;

    /**
     * Find by id
     *
     * @param id param for find
     * @return boolean result of operation
     * @throws ValidationException exception
     */
    Optional<T> findId(Long id) throws ValidationException, ServiceException;

    /**
     * Remove by id
     *
     * @param id param for find
     * @return boolean result of operation
     * @throws ValidationException exception
     */
    boolean remove(Long id) throws ValidationException, ServiceException;
}
