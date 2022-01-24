package com.epam.jwd.finalProject.service.factory;

import com.epam.jwd.finalProject.dao.api.UserDao;
import com.epam.jwd.finalProject.dao.impl.*;
import com.epam.jwd.finalProject.model.Entity;
import com.epam.jwd.finalProject.security.PasswordEncoder;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.imlp.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
/**
 * @author Daniil Serov
 */
public enum SimpleServiceFactory implements ServiceFactory {
    INSTANCE;

    private static final String SERVICE_NOT_FOUND = "Could not create service for %s class";

    private final Map<Class<?>, EntityService<?>> serviceByEntity = new ConcurrentHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity> EntityService<T> serviceFor(Class<T> modelClass) {
        return (EntityService<T>) serviceByEntity
                .computeIfAbsent(modelClass, createServiceFromClass());
    }

    private Function<Class<?>, EntityService<?>> createServiceFromClass() {
        return clazz -> {
            final String className = clazz.getSimpleName();
            switch (className) {
                case "Conferenc":
                    return new ConferencServiceImpl(ConferencDaoImpl.getInstance());
                case "SectionConferenc":
                    return new SectionConferencServiceImpl(SectionConferencDaoImpl.getInstance());
                case "Category":
                    return new CategoryServiceImpl(CategoryDaoImpl.getInstance());
                case "Question":
                    return new QuestionServiceImpl(QuestionDaoImpl.getInstance());
                case "Application":
                    return new ApplicationServiceImpl(ApplicationDaoImpl.getInstance());
                case "User":
                    return new UserServiceImpl((UserDaoImpl) UserDao.instance(), PasswordEncoder.getInstance());
                default:
                    throw new IllegalArgumentException(String.format(SERVICE_NOT_FOUND, className));
            }
        };
    }
}
