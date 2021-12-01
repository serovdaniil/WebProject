package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.dao.exception.EntityExtractionFailedException;
import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;
import java.util.Optional;

public interface ApplicationService extends EntityService {
    boolean create(Long idAccount, Long idSectionConferenc, Long idResultSection) throws ValidationException;

    boolean updateIdStatusApplication(Long idApplication, Long idResultSection);

    List<Application> findAccountIdByApplication(Long id) throws ValidationException;

    List<Application> findByFirstName(String firstName);

    List<Application> findByLastName(String lastName);

    List<Application> findByEmail(String email);

    List<Application> findByLogin(String login);

    List<Application> findByStatusResult(Long idStatus) throws ValidationException;

    List<Application> findByConferencId(Long idConferenc);

    List<Application> findByConferencName(String nameConferenc);

    List<Application> findBySectionConferencId(Long idSectionConferenc);

    List<Application> findBySectionConferencName(String nameSectionConferenc);

    List<Application> findByCategoryId(Long idCategory);

    List<Application> findByCategoryName(String nameCategory);
}
