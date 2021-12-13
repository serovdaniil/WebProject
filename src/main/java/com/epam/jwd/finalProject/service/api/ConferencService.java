package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Conferenc;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;
/**
 * The interface conferenc service
 *
 * @author Daniil Serov
 */
public interface ConferencService extends EntityService<Conferenc> {

    /**
     * Create conferenc
     *
     * @param name name for new conferenc
     * @param description description for new conferenc
     * @param idCategory category for conferenc
     * @return boolean result of operation
     */
    boolean create(String name, String description,Long idCategory) throws ValidationException;

    /**
     * Update description for conferenc
     *
     * @param id id conferenc
     * @param description description for new conferenc
     * @return boolean result of operation
     */
    boolean updateDescription(Long id, String description) throws ValidationException;

    /**
     * Find conferences by name
     *
     * @param name name conferenc
     * @return List conferenc
     */
    List<Conferenc> findByName(String name) throws ValidationException;

    /**
     * Find all status
     *
     * @return List conferenc
     */
    List<Conferenc> findAllStatus();

    /**
     * Change status for conferenc
     *
     * @param idConferenc id conferenc
     * @param nameStatus name status for conferenc
     * @return boolean result of operation
     */
    boolean changeStatus(Long idConferenc,String nameStatus) throws ValidationException;
}
