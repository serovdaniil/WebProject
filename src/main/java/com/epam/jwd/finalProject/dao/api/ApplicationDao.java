package com.epam.jwd.finalProject.dao.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.dao.impl.MethodApplicationDaoImpl;
import com.epam.jwd.finalProject.model.Application;

import java.util.List;
import java.util.Optional;

public interface ApplicationDao {
    boolean create(Long idAccount, Long idSectionConferenc, Long idResultSection);

    boolean updateIdStatusApplication(Long idApplication, Long idResultSection);

    List<Application> readAll() throws EntityExtractionFailedException;

    Optional<Application> readById(Long id);

    List<Application> findAccountIdByApplication(Long id);

    List<Application> findByFirstName(String firstName);

    List<Application> findByLastName(String lastName);

    List<Application> findByEmail(String email);

    List<Application> findByLogin(String login);

    List<Application> findByStatusResult(Long idStatus);

    List<Application> findByConferencId(Long idConferenc);

    List<Application> findByConferencName(String nameConferenc);

    List<Application> findBySectionConferencId(Long idSectionConferenc);

    List<Application> findBySectionConferencName(String nameSectionConferenc);

    List<Application> findByCategoryId(Long idCategory);

    List<Application> findByCategoryName(String nameCategory);

    boolean delete(Long id);

    static ApplicationDao instance() {
        return MethodApplicationDaoImpl.getInstance();
    }
}
