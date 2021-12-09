package com.epam.jwd.finalProject.command.factory;

import com.epam.jwd.finalProject.command.admin.adminPanel.ShowAdminPanelCategoryPageCommand;
import com.epam.jwd.finalProject.command.admin.adminPanel.ShowAdminPanelConferencPageCommand;
import com.epam.jwd.finalProject.command.admin.adminPanel.ShowAdminPanelSectionConferencPageCommand;
import com.epam.jwd.finalProject.command.admin.application.*;
import com.epam.jwd.finalProject.command.admin.category.*;
import com.epam.jwd.finalProject.command.admin.conferenc.*;
import com.epam.jwd.finalProject.command.admin.question.AddAnswerToQuestionCommand;
import com.epam.jwd.finalProject.command.admin.sectionConferenc.*;
import com.epam.jwd.finalProject.command.admin.user.ReadUserByIdCommand;
import com.epam.jwd.finalProject.command.admin.user.ShowUsersPageCommand;
import com.epam.jwd.finalProject.command.admin.user.ShowReadUserByIdPageCommand;
import com.epam.jwd.finalProject.command.admin.user.UpdateRoleCommand;
import com.epam.jwd.finalProject.command.common.*;
import com.epam.jwd.finalProject.command.common.application.CreateApplicationCommand;
import com.epam.jwd.finalProject.command.common.application.FindAccountIdByApplicationCommand;
import com.epam.jwd.finalProject.command.common.application.FindApplicationByIdCommand;
import com.epam.jwd.finalProject.command.common.category.*;
import com.epam.jwd.finalProject.command.common.conferenc.FindConferencByNameCommand;
import com.epam.jwd.finalProject.command.common.conferenc.ShowConferencesPageCommand;
import com.epam.jwd.finalProject.command.common.conferenc.ShowFindConferencByNameCommand;
import com.epam.jwd.finalProject.command.admin.question.ShowQuestionPageCommand;
import com.epam.jwd.finalProject.command.common.question.CreateQuestionCommand;
import com.epam.jwd.finalProject.command.common.question.RemoveQuestionByIdCommand;
import com.epam.jwd.finalProject.command.common.question.FindQuestionByIdAccountCommand;
import com.epam.jwd.finalProject.command.common.question.FindQuestionByIdCommand;
import com.epam.jwd.finalProject.command.common.sectionConferenc.*;
import com.epam.jwd.finalProject.command.common.user.*;
import com.epam.jwd.finalProject.command.error.ShowErrorPageCommand;
import com.epam.jwd.finalProject.model.Role;

import java.util.Arrays;
import java.util.List;

import static com.epam.jwd.finalProject.model.Role.*;

public enum CommandRegistry {
    MAIN_PAGE(ShowMainPageCommand.getInstance(), "main_page", UNAUTHORIZED, USER, ADMIN),
    //Conferenc
    SHOW_CONFERENCES(ShowConferencesPageCommand.getInstance(), "show_conferences", UNAUTHORIZED, USER, ADMIN),
    FIND_CONFERENCES_BY_NAME(FindConferencByNameCommand.getInstance(), "find_conferences_by_name", UNAUTHORIZED, USER, ADMIN),
    SHOW_FIND_CONFERENCES_BY_NAME(ShowFindConferencByNameCommand.getInstance(), "show_find_conferences_by_name", UNAUTHORIZED, USER, ADMIN),
    UPDATE_DESCRIPTION_IN_CONFERENC(UpdateDescriptionInConferencCommand.getInstance(), "update_description_in_conferenc", ADMIN),
    FIND_CONFERENCES_BY_ID(FindConferencByIdCommand.getInstance(), "find_conferenc_by_id", ADMIN),
    REMOVE_CONFERENCES_BY_ID(RemoveConferencByIdCommand.getInstance(), "remove_conferenc_by_id", ADMIN),
    CREATE_CONFERENCES(CreateConferencCommand.getInstance(), "create_conferenc", ADMIN),
    // sectionConferenc
    SHOW_SECTON_CONFERENCES(ShowSectionConferencesPageCommand.getInstance(), "show_section_conferences", UNAUTHORIZED, USER, ADMIN),
    FIND_SECTION_CONFERENCES_BY_ID(FindSectionConferencByIdCommand.getInstance(), "find_section_conferenc_by_id", ADMIN),
    REMOVE_SECTION_CONFERENCES_BY_ID(RemoveSectionConferencByIdCommand.getInstance(), "remove_section_conferenc_by_id", ADMIN),
    CREATE_SECTION_CONFERENCES(CreateSectionConferencCommand.getInstance(), "create_section_conferenc", ADMIN),
    UPDATE_DESCRIPTION_IN_SECTION_CONFERENC(UpdateDescriptionInSectionConferencCommand.getInstance(), "update_description_in_section_conferenc", ADMIN),
    FIND_SECTION_CONFERENCES_BY_NAME(FindSectionConferencByNameCommand.getInstance(), "find_section_conferences_by_name", UNAUTHORIZED, USER, ADMIN),
    SHOW_FIND_SECTION_CONFERENCES_BY_NAME(ShowFindSectionConferencByNameCommand.getInstance(), "show_find_section_conferences_by_name", UNAUTHORIZED, USER, ADMIN),
    FIND_SECTION_CONFERENCES_IN_CONFERENC_BY_ID(FindSectionConferencesInConferencByIdCommand.getInstance(), "find_section_conferences_in_conferenc_by_id", UNAUTHORIZED, USER, ADMIN),
    SHOW_FIND_SECTION_CONFERENCES_IN_CONFERENC_BY_ID(ShowFindSectionConferencesInConferencByIdCommand.getInstance(), "show_find_section_conferences_in_conferenc_by_id", UNAUTHORIZED, USER, ADMIN),
    // categories
    SHOW_CATEGORIES(ShowCategoryPageCommand.getInstance(), "show_categories", UNAUTHORIZED, USER, ADMIN),
    CREATE_CATEGORY(CreateCategoryCommand.getInstance(), "create_category", ADMIN),
    CHANGE_NAME_CATEGORY(ChangeNameCategoryCommand.getInstance(), "change_name_category", ADMIN),
    SHOW_ALL_CONFERENCES_IN_CATEGORY(ShowFindConferencInIdCategoryCommand.getInstance(), "show_all_conferences_in_category", UNAUTHORIZED, USER, ADMIN),
    ALL_CONFERENCES_IN_CATEGORY(FindConferencInIdCategoryCommand.getInstance(), "all_conferences_in_category", UNAUTHORIZED, USER, ADMIN),
    SHOW_ALL_SECTION_CONFERENCES_IN_CATEGORY(ShowFindSectionConferencInCategoryByIdCommand.getInstance(), "show_all_section_conferences_in_category", UNAUTHORIZED, USER, ADMIN),
    ALL_SECTION_CONFERENCES_IN_CATEGORY(FindSectionConferencInCategoryByIdCommand.getInstance(), "all_section_conferences_in_category", UNAUTHORIZED, USER, ADMIN),
    ID_CATEGORY(FindCategoryByIdCommand.getInstance(), "id_category", ADMIN),
    REMOVE_CATEGORY(RemoveCategoryByIdCommand.getInstance(), "remove_category_by_id", ADMIN),
    //Question
    SHOW_QUESTONS(ShowQuestionPageCommand.getInstance(), "show_questions", ADMIN),
    ADDANSWERTOQUESTION(AddAnswerToQuestionCommand.getInstance(), "add_answer_to_question", ADMIN),
    CREATEQUESTION(CreateQuestionCommand.getInstance(), "create_question", USER, ADMIN),
    FIND_QUESTIONS_BY_ID_ACCOUNT(FindQuestionByIdAccountCommand.getInstance(), "find_questions_by_id_account", USER, ADMIN),
    FIND_QUESTION_BY_ID(FindQuestionByIdCommand.getInstance(), "find_question_by_id", USER, ADMIN),
    REMOVE_QUESTION_BY_ID(RemoveQuestionByIdCommand.getInstance(), "remove_question_by_id", USER, ADMIN),
    //User
    SHOW_USERS(ShowUsersPageCommand.getInstance(), "show_users", ADMIN),
    UPDATE_PASSWORD_BY_USER(UpdatePasswordCommand.getInstance(), "update_password_by_user", USER, ADMIN),
    UPDATE_EMAIL_BY_USER(UpdateEmailCommand.getInstance(), "update_email_by_user", USER, ADMIN),
    UPDATE_FIRST_NAME_BY_USER(UpdateFirstNameCommand.getInstance(), "update_first_name_by_user", USER, ADMIN),
    UPDATE_LAST_NAME_BY_USER(UpdateLastNameCommand.getInstance(), "update_last_name_by_user", USER, ADMIN),
    UPDATE_ROLE_BY_USER(UpdateRoleCommand.getInstance(), "update_role_by_user", ADMIN),
    READ_USER_BY_ID(ReadUserByIdCommand.getInstance(), "read_user_by_id", ADMIN),
    SHOW_READ_USER_BY_ID(ShowReadUserByIdPageCommand.getInstance(), "show_read_user_by_id", ADMIN),
    SHOW_PERSONAL_INFORMATION_BY_USER(ShowPerosnalInformationCommand.getInstance(), "show_personal_infomation", USER, ADMIN),
    //application
    SHOW_APPLICATION(ShowApplicationPageCommand.getInstance(), "show_applications", ADMIN),
    SHOW_APPLICATIONS_BY_ACCOUNT(FindAccountIdByApplicationCommand.getInstance(), "show_applications_by_account", USER, ADMIN),
    FIND_BY_STATUS_RESULT_APPLICATION(FindByStatusResultApplicationCommand.getInstance(), "find_by_status_result_application", ADMIN),
    SHOW_FIND_BY_STATUS_RESULT_APPLICATION(ShowFindByStatusResultApplicationPageCommand.getInstance(), "show_find_by_status_result_application", ADMIN),
    REMOVE_APPLICATION(RemoveApplicationByIdCommand.getInstance(), "remove_application", USER, ADMIN),
    CREATE_APPLICATION(CreateApplicationCommand.getInstance(), "create_application", USER, ADMIN),
    FIND_APPLICATION_BY_ID(FindApplicationByIdCommand.getInstance(), "find_application_by_id", USER, ADMIN),
    UPDATE_STATUS_RESULT_APPLICATION_BY_ID(UpdateStatusResultByIdApplicationCommand.getInstance(), "update_status_result_application", USER, ADMIN),
    //ADMIN PANEL
    SHOW_ADMIN_PANEL_CONFERENC(ShowAdminPanelConferencPageCommand.getInstance(), "show_admin_panel_conferenc", ADMIN),
    SHOW_ADMIN_PANEL_SECTIONCONFERENC(ShowAdminPanelSectionConferencPageCommand.getInstance(), "show_admin_panel_section_conferenc", ADMIN),
    SHOW_ADMIN_PANEL_CATEGORY(ShowAdminPanelCategoryPageCommand.getInstance(), "show_admin_panel_category", ADMIN),
    ABOUT(ShowAboutPageCommand.getInstance(), "show_about_page"),
    SHOW_PESONAL_ACCOUNT(ShowPersonalAccountPageCommand.getInstance(), "show_personal_account", USER, ADMIN),
    CONTACT(ShowContactPageCommand.getInstance(), "show_contact"),
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
