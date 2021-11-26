package com.epam.jwd.finalProject.command.factory;

import com.epam.jwd.finalProject.command.admin.category.*;
import com.epam.jwd.finalProject.command.admin.conferenc.*;
import com.epam.jwd.finalProject.command.admin.sectionConferenc.*;
import com.epam.jwd.finalProject.command.common.*;
import com.epam.jwd.finalProject.command.common.category.*;
import com.epam.jwd.finalProject.command.common.conferenc.FindConferencByNameCommand;
import com.epam.jwd.finalProject.command.common.conferenc.ShowConferencesPageCommand;
import com.epam.jwd.finalProject.command.common.conferenc.ShowFindConferencByNameCommand;
import com.epam.jwd.finalProject.command.common.question.ShowQuestionPageCommand;
import com.epam.jwd.finalProject.command.common.sectionConferenc.*;
import com.epam.jwd.finalProject.command.error.ShowErrorPageCommand;
import com.epam.jwd.finalProject.model.Role;

import java.util.Arrays;
import java.util.List;

import static com.epam.jwd.finalProject.model.Role.*;

public enum CommandRegistry {
    MAIN_PAGE(ShowMainPageCommand.getInstance(), "main_page"),
    //Conferenc
    SHOW_CONFERENCES(ShowConferencesPageCommand.getInstance(), "show_conferences",ADMIN),
    FIND_CONFERENCES_BY_NAME(FindConferencByNameCommand.getInstance(), "find_conferences_by_name", USER, ADMIN),
    SHOW_FIND_CONFERENCES_BY_NAME(ShowFindConferencByNameCommand.getInstance(), "show_find_conferences_by_name"),
    SHOW_UPDATE_DECRIPTION_IN_CONFERENC(ShowUpdateDescriptionInConferencCommand.getInstance(), "show_update_description_in_conferenc"),
    UPDATE_DESCRIPTION_IN_CONFERENC(UpdateDescriptionInConferencCommand.getInstance(), "update_description_in_conferenc"),
    SHOW_FIND_CONFERENCES_BY_ID(ShowFindConferencByIdCommand.getInstance(), "show_find_conferenc_by_id"),
    FIND_CONFERENCES_BY_ID(FindConferencByIdCommand.getInstance(), "find_conferenc_by_id"),
    SHOW_REMOVE_CONFERENCES_BY_ID(ShowRemoveConferencByIdCommand.getInstance(), "show_remove_conferenc_by_id"),
    REMOVE_CONFERENCES_BY_ID(RemoveConferencByIdCommand.getInstance(), "remove_conferenc_by_id"),
    SHOW_CREATE_CONFERENC(ShowCreateConferencCommand.getInstance(), "show_create_conferenc",ADMIN),
    CREATE_CONFERENCES(CreateConferencCommand.getInstance(), "create_conferenc",ADMIN),
    // sectionConferenc
    SHOW_SECTON_CONFERENCES(ShowSectionConferencesPageCommand.getInstance(), "show_section_conferences"),
    SHOW_FIND_SECTION_CONFERENCES_BY_ID(ShowFindSectionConferencByIdCommand.getInstance(), "show_find_section_conferenc_by_id"),
    FIND_SECTION_CONFERENCES_BY_ID(FindSectionConferencByIdCommand.getInstance(), "find_section_conferenc_by_id"),
    SHOW_REMOVE_SECTION_CONFERENCES_BY_ID(ShowRemoveSectionConferencByIdCommand.getInstance(), "show_remove_section_conferenc_by_id"),
    REMOVE_SECTION_CONFERENCES_BY_ID(RemoveSectionConferencByIdCommand.getInstance(), "remove_section_conferenc_by_id"),
    SHOW_CREATE_SECTION_CONFERENC(ShowCreateSectionConferencCommand.getInstance(), "show_create_section_conferenc"),
    CREATE_SECTION_CONFERENCES(CreateSectionConferencCommand.getInstance(), "create_section_conferenc"),
    SHOW_UPDATE_DECRIPTION_IN_SECTION_CONFERENC(ShowUpdateDescriptionInSectionConferencCommand.getInstance(), "show_update_description_in_section_conferenc"),
    UPDATE_DESCRIPTION_IN_SECTION_CONFERENC(UpdateDescriptionInSectionConferencCommand.getInstance(), "update_description_in_section_conferenc"),
    FIND_SECTION_CONFERENCES_BY_NAME(FindSectionConferencByNameCommand.getInstance(), "find_section_conferences_by_name"),
    SHOW_FIND_SECTION_CONFERENCES_BY_NAME(ShowFindSectionConferencByNameCommand.getInstance(), "show_find_section_conferences_by_name"),
    FIND_SECTION_CONFERENCES_IN_CONFERENC_BY_ID(FindSectionConferencesInConferencByIdCommand.getInstance(), "find_section_conferences_in_conferenc_by_id"),
    SHOW_FIND_SECTION_CONFERENCES_IN_CONFERENC_BY_ID(ShowFindSectionConferencesInConferencByIdCommand.getInstance(), "show_find_section_conferences_in_conferenc_by_id"),
    // categories
    SHOW_CATEGORIES(ShowCategoryPageCommand.getInstance(), "show_categories"),
    SHOW_CREATE_CATEGORY(ShowCreateCategoryCommand.getInstance(), "show_create_category"),
    CREATE_CATEGORY(CreateCategoryCommand.getInstance(), "create_category"),
    SHOW_CHANGE_NAME_CATEGORY(ShowChangeNameCategoryCommand.getInstance(), "show_change_name_category"),
    CHANGE_NAME_CATEGORY(ChangeNameCategoryCommand.getInstance(), "change_name_category"),
    SHOW_ALL_CONFERENCES_IN_CATEGORY(ShowFindConferencInIdCategoryCommand.getInstance(), "show_all_conferences_in_category"),
    ALL_CONFERENCES_IN_CATEGORY(FindConferencInIdCategoryCommand.getInstance(), "all_conferences_in_category"),
    SHOW_ALL_SECTION_CONFERENCES_IN_CATEGORY(ShowFindSectionConferencInCategoryByIdCommand.getInstance(), "show_all_section_conferences_in_category"),
    ALL_SECTION_CONFERENCES_IN_CATEGORY(FindSectionConferencInCategoryByIdCommand.getInstance(), "all_section_conferences_in_category"),
    SHOW_ID_CATEGORY(ShowFindCategoryByIdCommand.getInstance(), "show_id_category"),
    ID_CATEGORY(FindCategoryByIdCommand.getInstance(), "id_category"),
    SHOW_REMOVE_CATEGORY(ShowRemoveCategoryByIdCommand.getInstance(), "show_remove_category_by_id"),
    REMOVE_CATEGORY(RemoveCategoryByIdCommand.getInstance(), "remove_category_by_id"),
    //Question
    SHOW_QUESTONS(ShowQuestionPageCommand.getInstance(), "show_questions"),

    //
    ERROR(ShowErrorPageCommand.getInstance(), "show_error"),
    SHOW_LOGIN(ShowLoginPageCommand.getInstance(), "show_login", UNAUTHORIZED),
    LOGIN(LoginCommand.getInstance(), "login", UNAUTHORIZED),
    SHOW_CREATE_AN_ACCOUNT(ShowCreateAnAccountCommand.getInstance(), "show_create_an_account", UNAUTHORIZED),
    CREATE_AN_ACCOUNT(CreateAnAccountCommand.getInstance(), "create_an_account", UNAUTHORIZED),
    LOGOUT(LogoutCommand.getInstance(), "logout", USER, ADMIN),
    DEFAULT(ShowMainPageCommand.getInstance(), "");

    private final Command command;
    private final String path;
    private final List<Role> allowedRoles;

    CommandRegistry(Command command, String path, Role... roles) {
        this.command = command;
        this.path = path;
        this.allowedRoles = roles != null && roles.length > 0 ? Arrays.asList(roles) : Role.valuesAsList();
    }

    public Command getCommand() {
        return command;
    }

    public List<Role> getAllowedRoles() {
        return allowedRoles;
    }

    static Command of(String name) {
        for (CommandRegistry constant : values()) {
            if (constant.path.equalsIgnoreCase(name)) {
                return constant.command;
            }
        }
        return DEFAULT.command;
    }
}
