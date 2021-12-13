package com.epam.jwd.finalProject.command.factory;

import com.epam.jwd.finalProject.command.admin.function.application.FindByStatusResultApplicationCommand;
import com.epam.jwd.finalProject.command.admin.function.application.RemoveApplicationByIdCommand;
import com.epam.jwd.finalProject.command.admin.function.application.UpdateStatusResultByIdApplicationCommand;
import com.epam.jwd.finalProject.command.admin.page.adminPanel.ShowAdminPanelCategoryPageCommand;
import com.epam.jwd.finalProject.command.admin.page.adminPanel.ShowAdminPanelConferencPageCommand;
import com.epam.jwd.finalProject.command.admin.page.adminPanel.ShowAdminPanelSectionConferencPageCommand;
import com.epam.jwd.finalProject.command.admin.page.application.*;
import com.epam.jwd.finalProject.command.admin.function.category.*;
import com.epam.jwd.finalProject.command.admin.function.conferenc.*;
import com.epam.jwd.finalProject.command.admin.function.question.AddAnswerToQuestionCommand;
import com.epam.jwd.finalProject.command.admin.function.sectionConferenc.*;
import com.epam.jwd.finalProject.command.admin.function.user.ReadUserByIdCommand;
import com.epam.jwd.finalProject.command.admin.page.conferenc.ShowAllConferencesPageCommand;
import com.epam.jwd.finalProject.command.admin.page.conferenc.ShowReadConferencByIdPageCommand;
import com.epam.jwd.finalProject.command.admin.page.sectionConferenc.ShowReadSectionConferencByIdPageCommand;
import com.epam.jwd.finalProject.command.admin.page.user.ShowUsersPageCommand;
import com.epam.jwd.finalProject.command.admin.page.user.ShowReadUserByIdPageCommand;
import com.epam.jwd.finalProject.command.admin.function.user.UpdateRoleCommand;
import com.epam.jwd.finalProject.command.common.function.application.CreateApplicationCommand;
import com.epam.jwd.finalProject.command.common.function.application.FindAccountIdByApplicationCommand;
import com.epam.jwd.finalProject.command.common.function.application.FindApplicationByIdCommand;
import com.epam.jwd.finalProject.command.common.function.user.*;
import com.epam.jwd.finalProject.command.common.page.ShowAboutPageCommand;
import com.epam.jwd.finalProject.command.common.page.ShowContactPageCommand;
import com.epam.jwd.finalProject.command.common.page.ShowMainPageCommand;
import com.epam.jwd.finalProject.command.common.page.category.*;
import com.epam.jwd.finalProject.command.common.function.conferenc.FindConferencByNameCommand;
import com.epam.jwd.finalProject.command.common.page.conferenc.ShowConferencesPageCommand;
import com.epam.jwd.finalProject.command.common.page.conferenc.ShowFindConferencByNamePageCommand;
import com.epam.jwd.finalProject.command.admin.page.question.ShowQuestionPageCommand;
import com.epam.jwd.finalProject.command.common.function.category.FindConferencInIdCategoryCommand;
import com.epam.jwd.finalProject.command.common.function.category.FindSectionConferencInCategoryByIdCommand;
import com.epam.jwd.finalProject.command.common.function.question.CreateQuestionCommand;
import com.epam.jwd.finalProject.command.common.function.question.RemoveQuestionByIdCommand;
import com.epam.jwd.finalProject.command.common.function.question.FindQuestionByIdAccountCommand;
import com.epam.jwd.finalProject.command.common.function.question.FindQuestionByIdCommand;
import com.epam.jwd.finalProject.command.common.page.sectionConferenc.ShowFindSectionConferencByNamePageCommand;
import com.epam.jwd.finalProject.command.common.page.sectionConferenc.ShowFindSectionConferencesInConferencByIdPageCommand;
import com.epam.jwd.finalProject.command.admin.page.sectionConferenc.ShowSectionConferencesPageCommand;
import com.epam.jwd.finalProject.command.common.function.sectionConferenc.*;
import com.epam.jwd.finalProject.command.common.page.user.*;
import com.epam.jwd.finalProject.command.error.ShowErrorPageCommand;
import com.epam.jwd.finalProject.model.Role;

import java.util.Arrays;
import java.util.List;

import static com.epam.jwd.finalProject.model.Role.*;

/**
 * List of commands
 *
 * @author Daniil Serov
 */
public enum CommandRegistry {
    MAIN_PAGE(ShowMainPageCommand.getInstance(), "main_page", UNAUTHORIZED, USER, ADMIN, BLOKED),
    //Conferenc
    SHOW_CONFERENCES(ShowConferencesPageCommand.getInstance(), "show_conferences", UNAUTHORIZED, USER, ADMIN, BLOKED),
    FIND_CONFERENCES_BY_NAME(FindConferencByNameCommand.getInstance(), "find_conferences_by_name",ADMIN),
    SHOW_FIND_CONFERENCES_BY_NAME(ShowFindConferencByNamePageCommand.getInstance(), "show_find_conferences_by_name",ADMIN),
    UPDATE_DESCRIPTION_IN_CONFERENC(UpdateDescriptionInConferencCommand.getInstance(), "update_description_in_conferenc", ADMIN),
    FIND_CONFERENCES_BY_ID(FindConferencByIdCommand.getInstance(), "find_conferenc_by_id", ADMIN),
    REMOVE_CONFERENCES_BY_ID(RemoveConferencByIdCommand.getInstance(), "remove_conferenc_by_id", ADMIN),
    CREATE_CONFERENCES(CreateConferencCommand.getInstance(), "create_conferenc", ADMIN),
    SHOW_ALL_CONFERENCES(ShowAllConferencesPageCommand.getInstance(), "show_all_conferences", ADMIN),
    SHOW_READ_CONFERENC_BY_ID(ShowReadConferencByIdPageCommand.getInstance(), "show_read_conferences_by_id", ADMIN),
    CHANGE_STATUS_CONFERENC(ChangeStatusConferencCommand.getInstance(), "change_status_conferenc_by_id", ADMIN),
    // sectionConferenc
    SHOW_SECTON_CONFERENCES(ShowSectionConferencesPageCommand.getInstance(), "show_section_conferences",ADMIN),
    SHOW_READ_SECTON_CONFERENC_BY_ID(ShowReadSectionConferencByIdPageCommand.getInstance(), "show_read_section_conferences_by_id",ADMIN),
    FIND_SECTION_CONFERENCES_BY_ID(FindSectionConferencByIdCommand.getInstance(), "find_section_conferenc_by_id", ADMIN),
    CHANGE_STATUS_SECTION_CONFERENC_BY_ID(ChangeStatusSectionConferencCommand.getInstance(), "change_status_section_conferenc_by_id", ADMIN),
    REMOVE_SECTION_CONFERENCES_BY_ID(RemoveSectionConferencByIdCommand.getInstance(), "remove_section_conferenc_by_id", ADMIN),
    CREATE_SECTION_CONFERENCES(CreateSectionConferencCommand.getInstance(), "create_section_conferenc", ADMIN),
    UPDATE_DESCRIPTION_IN_SECTION_CONFERENC(UpdateDescriptionInSectionConferencCommand.getInstance(), "update_description_in_section_conferenc", ADMIN),
    FIND_SECTION_CONFERENCES_BY_NAME(FindSectionConferencByNameCommand.getInstance(), "find_section_conferences_by_name", ADMIN),
    SHOW_FIND_SECTION_CONFERENCES_BY_NAME(ShowFindSectionConferencByNamePageCommand.getInstance(), "show_find_section_conferences_by_name", ADMIN),
    FIND_SECTION_CONFERENCES_IN_CONFERENC_BY_ID(FindSectionConferencesInConferencByIdCommand.getInstance(), "find_section_conferences_in_conferenc_by_id", UNAUTHORIZED, USER, ADMIN, BLOKED),
    SHOW_FIND_SECTION_CONFERENCES_IN_CONFERENC_BY_ID(ShowFindSectionConferencesInConferencByIdPageCommand.getInstance(), "show_find_section_conferences_in_conferenc_by_id", UNAUTHORIZED, USER, ADMIN, BLOKED),
    // categories
    SHOW_CATEGORIES(ShowCategoryPageCommand.getInstance(), "show_categories", UNAUTHORIZED, USER, ADMIN, BLOKED),
    CREATE_CATEGORY(CreateCategoryCommand.getInstance(), "create_category", ADMIN),
    CHANGE_NAME_CATEGORY(ChangeNameCategoryCommand.getInstance(), "change_name_category", ADMIN),
    SHOW_ALL_CONFERENCES_IN_CATEGORY(ShowFindConferencInIdCategoryPageCommand.getInstance(), "show_all_conferences_in_category", UNAUTHORIZED, USER, ADMIN, BLOKED),
    ALL_CONFERENCES_IN_CATEGORY(FindConferencInIdCategoryCommand.getInstance(), "all_conferences_in_category", UNAUTHORIZED, USER, ADMIN, BLOKED),
    SHOW_ALL_SECTION_CONFERENCES_IN_CATEGORY(ShowFindSectionConferencInCategoryByIdPageCommand.getInstance(), "show_all_section_conferences_in_category", UNAUTHORIZED, USER, ADMIN, BLOKED),
    ALL_SECTION_CONFERENCES_IN_CATEGORY(FindSectionConferencInCategoryByIdCommand.getInstance(), "all_section_conferences_in_category", UNAUTHORIZED, USER, ADMIN, BLOKED),
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
    SHOW_PERSONAL_INFORMATION_BY_USER(ShowPerosnalInformationPageCommand.getInstance(), "show_personal_infomation", USER, ADMIN),
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
