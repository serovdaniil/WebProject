package com.epam.jwd.finalProject.service.factory;

import com.epam.jwd.finalProject.model.*;
import com.epam.jwd.finalProject.service.api.*;

public interface ServiceFactory {

    <T extends Entity> EntityService<T> serviceFor(Class<T> modelClass);

    default UserService userService() {
        return (UserService) serviceFor(User.class);
    }

    default ConferencService conferencService() {
        return (ConferencService) serviceFor(Conferenc.class);
    }

    default CategoryService categoryService() {
        return (CategoryService) serviceFor(Category.class);
    }
    default QuestionService questionService() {
        return (QuestionService) serviceFor(Question.class);
    }

    default SectionConferencService sectionConferencService() {
        return (SectionConferencService) serviceFor(SectionConferenc.class);
    }

    static SimpleServiceFactory simple() {
        return SimpleServiceFactory.INSTANCE;
    }
}
