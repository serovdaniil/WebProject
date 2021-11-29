package com.epam.jwd.finalProject.service.factory;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.epam.jwd.finalProject.dao.api.UserDao;
import com.epam.jwd.finalProject.dao.impl.*;
import com.epam.jwd.finalProject.model.Entity;
import com.epam.jwd.finalProject.service.ProxyEntityService;
import com.epam.jwd.finalProject.service.api.EntityService;
import com.epam.jwd.finalProject.service.imlp.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

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
                    return ProxyEntityService.of(new ConferencServiceImpl(MethodConferencDaoImpl.getInstance()));
                case "SectionConferenc":
                    return ProxyEntityService.of(new SectionConferencServiceImpl(MethodSectionConferencDaoImpl.getInstance()));
                case "Category":
                    return ProxyEntityService.of(new CategoryServiceImpl(MethodCategoryDaoImpl.getInstance()));
                case "Question":
                    return ProxyEntityService.of(new QuestionServiceImpl(MethodQuestionDaoImpl.getInstance()));
                case "Application":
                    return ProxyEntityService.of(new ApplicationServiceImpl(MethodApplicationDaoImpl.getInstance()));

                case "User":
                    return new UserServiceImpl((MethodUserDaoImpl) UserDao.instance(), BCrypt.withDefaults(), BCrypt.verifyer());
                default:
                    throw new IllegalArgumentException(String.format(SERVICE_NOT_FOUND, className));
            }
        };
    }
}
