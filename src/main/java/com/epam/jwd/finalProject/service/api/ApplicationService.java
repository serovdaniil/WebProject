package com.epam.jwd.finalProject.service.api;

import com.epam.jwd.finalProject.model.Application;
import com.epam.jwd.finalProject.service.exception.ValidationException;

import java.util.List;

public interface ApplicationService extends EntityService {
    boolean create(Long idAccount, Long idSectionConferenc, Long idResultSection) throws ValidationException;

    boolean updateIdStatusApplication(Long idApplication, String resultSection) throws ValidationException;

    List<Application> findAccountIdByApplication(Long id) throws ValidationException;

    List<Application> findByStatusResult(String nameStatus) throws ValidationException;
}
