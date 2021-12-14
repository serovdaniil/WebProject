package com.epam.jwd.finalProject.dao.impl;

public class SqlQuery {

    // MethodApplicationDaoImpl
    protected static final String FIND_ALL_APPLICATION = "SELECT * FROM application JOIN final_task.user ON user_id=id_user " +
            "JOIN final_task.role ON role_id=id_role JOIN section_result " +
            "ON section_result_id=id_section_result JOIN section_conferenc ON section_id=id_section_conferenc JOIN status ON section_conferenc_status_id=id_status " +
            "JOIN conferenc ON conferenc_id=id_conferenc JOIN category ON category_id=id_category";

    protected static final String FIND_ID_APPLICATION = "SELECT * FROM application JOIN final_task.user " +
            "ON user_id=id_user JOIN final_task.role ON role_id=id_role JOIN section_result " +
            "ON section_result_id=id_section_result JOIN section_conferenc ON section_id=id_section_conferenc JOIN status ON section_conferenc_status_id=id_status " +
            "JOIN conferenc ON conferenc_id=id_conferenc JOIN category ON category_id=id_category WHERE id_application=?";

    protected static final String FIND_ID_ACCOUNT_BY_CAPPLICATION = "SELECT * FROM application JOIN final_task.user " +
            "ON user_id=id_user JOIN final_task.role ON role_id=id_role JOIN section_result " +
            "ON section_result_id=id_section_result JOIN section_conferenc ON section_id=id_section_conferenc JOIN status ON section_conferenc_status_id=id_status " +
            "JOIN conferenc ON conferenc_id=id_conferenc JOIN category ON category_id=id_category WHERE id_user=?";

    protected static final String FIND_ID_SECTION_RESULT_BY_CAPPLICATION = "SELECT * FROM application JOIN final_task.user " +
            "ON user_id=id_user JOIN final_task.role ON role_id=id_role JOIN section_result " +
            "ON section_result_id=id_section_result JOIN section_conferenc ON section_id=id_section_conferenc JOIN status ON section_conferenc_status_id=id_status " +
            "JOIN conferenc ON conferenc_id=id_conferenc JOIN category ON category_id=id_category WHERE id_section_result=?";

    protected static final String APPLICATION_DELETE = "DELETE FROM application WHERE id_application=?";

    protected static final String UPDATE_ID_RESULT_APPLICATION = "UPDATE application SET section_result_id = ? WHERE id_application = ?";

    protected static final String CHANGE_STATUS_AFTER_UPDATE_SECTION_CONFERENC = "UPDATE application SET section_result_id = 4 WHERE section_id = ?";

    protected static final String CREATE_APPLICATION = "INSERT INTO application (user_id,section_id,section_result_id) values(?,?,?)";

    // MethodCategoryDaoImpl

    protected static final String CREATE_CATEGORY = "INSERT INTO category (name_category) values(?)";

    protected static final String UPDATE_NAME_BY_ID_CATEGORY = "UPDATE category SET name_category= ? WHERE id_category = ?";

    protected static final String FIND_ALL_CATEGORY = "SELECT * FROM category";

    protected static final String FIND_ID_CATEGORY = "SELECT * FROM category WHERE id_category=?";

    protected static final String FIND_BY_ID_CONFERENC_IN_CATEGORY = "SELECT * FROM conferenc JOIN category " +
            "ON category_id=id_category JOIN status ON conferenc_status_id=id_status WHERE id_category=?";

    protected static final String FIND_BY_ID_SECTIONCONFERENC_IN_CATEGORY = "SELECT * FROM section_conferenc JOIN conferenc " +
            "ON conferenc_id=id_conferenc JOIN category ON category_id=id_category JOIN status ON conferenc_status_id=id_status WHERE id_category=?";

    protected static final String CATEGORY_DELETE = "DELETE FROM category WHERE id_category=?";

    // MethodUserDaoImpl
    protected static final String CREATE_USER = "INSERT INTO user (login,email,password, role_id) values(?,?,?,?)";

    protected static final String UPDATE_PASSWORD_BY_LOGIN_ACCOUNT = "UPDATE user JOIN role ON role_id=id_role SET password=? WHERE login = ?";

    protected static final String UPDATE_EMAIL_BY_ID_ACCOUNT = "UPDATE user JOIN role ON role_id=id_role  " +
            "SET email=? WHERE id_user = ?";

    protected static final String UPDATE_FIRSTNAME_BY_ID_ACCOUNT = "UPDATE user JOIN role ON role_id=id_role  " +
            "SET first_name=? WHERE id_user = ?";

    protected static final String UPDATE_LASTNAME_BY_ID_ACCOUNT = "UPDATE user JOIN role ON role_id=id_role  " +
            "SET last_name=? WHERE id_user = ?";

    protected static final String UPDATE_ROLE_BY_ID_ACCOUNT = "UPDATE user JOIN role ON role_id=id_role SET role_id=? WHERE id_user = ?";

    protected static final String FIND_ALL_ACCOUNT = "SELECT * FROM final_task.user JOIN final_task.role ON role_id=id_role";

    protected static final String FIND_ID_USER = "SELECT * FROM user JOIN role ON role_id=id_role WHERE id_user=?";

    protected static final String FIND_EMAIL_BY_ACCOUNT = "SELECT * FROM user JOIN role ON role_id=id_role WHERE email=?";

    protected static final String FIND_PASSWORD_BY_LOGIN = "SELECT * FROM user JOIN role ON role_id=id_role WHERE login=?";

    // MethodSectionConferencDaoImpl

    protected static final String CREATE_SECTION_CONFERENC = "INSERT INTO section_conferenc (name,description,conferenc_id,section_conferenc_status_id) values(?,?,?,1)";
    protected static final String ADD_DESCRIPTION_BY_SECTION_CONFERENC = "UPDATE section_conferenc SET description = ?" +
            " WHERE id_section_conferenc = ?";
    protected static final String UPDATE_STATUS_SECTION_CONFERENC = "UPDATE section_conferenc SET section_conferenc_status_id =?" +
            " WHERE id_section_conferenc = ?";
    protected static final String UPDATE_STATUS_SECTION_CONFERENC_AFTER_CONFERENC = "UPDATE section_conferenc SET section_conferenc_status_id = ?" +
            " WHERE conferenc_id = ?";
    protected static final String FIND_ALL_SECTION_CONFERENC = "SELECT * FROM section_conferenc JOIN status ON section_conferenc_status_id=id_status JOIN conferenc " +
            "ON conferenc_id=id_conferenc JOIN category ON category_id=id_category ";
    protected static final String FIND_ID_SECTION_CONFERENC = "SELECT * FROM section_conferenc JOIN status ON section_conferenc_status_id=id_status JOIN conferenc " +
            "ON conferenc_id=id_conferenc JOIN category ON category_id=id_category WHERE id_section_conferenc=?";
    protected static final String FIND_NAME_SECTION_CONFERENC = "SELECT * FROM section_conferenc JOIN status ON section_conferenc_status_id=id_status JOIN conferenc " +
            "ON conferenc_id=id_conferenc JOIN category ON category_id=id_category WHERE section_conferenc.name=?";
    protected static final String SECTION_CONFERENC_DELETE = "DELETE FROM section_conferenc  WHERE id_section_conferenc=?";
    protected static final String FIND_SECTION_CONFERENCES_IN_CONFERENC_BY_ID_ACTIVE = "SELECT * FROM section_conferenc JOIN conferenc " +
            "ON conferenc_id=id_conferenc JOIN category ON category_id=id_category JOIN status ON section_conferenc_status_id=id_status WHERE section_conferenc.conferenc_id=? && section_conferenc_status_id=1";

    // MethodQuestionDaoImpl

    protected static final String CREATE_QUESTION = "INSERT INTO final_task.question (question,answer,date,user_id) values(?,?,?,?)";
    protected static final String ADD_ANSWER = "UPDATE final_task.question SET final_task.question.answer = ? WHERE final_task.question.id = ?";
    protected static final String FIND_ALL_QUESTION = "SELECT * FROM final_task.question JOIN final_task.user ON user_id=id_user " +
            "JOIN final_task.role ON role_id=id_role";
    protected static final String FIND_ID_QUESTION = "SELECT * FROM final_task.question JOIN final_task.user ON user_id=id_user " +
            "JOIN final_task.role ON role_id=id_role WHERE final_task.question.id = ?";
    protected static final String FIND_ACCOUNT_ID_BY_QUESTION = "SELECT * FROM final_task.question JOIN final_task.user ON user_id=id_user " +
            "JOIN final_task.role ON role_id=id_role WHERE final_task.question.user_id = ?";
    protected static final String QUESTION_DELETE = "DELETE FROM final_task.question WHERE final_task.question.id = ?";

    // MethodConferencDaoImpl

    protected static final String CREATE_CONFERENC = "INSERT INTO conferenc (name,description,category_id,conferenc_status_id) values(?,?,?,1)";
    protected static final String ADD_DESCRIPTION_BY_CONFERENC = "UPDATE conferenc SET description = ?" +
            " WHERE id_conferenc = ?";
    protected static final String UPDATE_STATUS_CONFERENC = "UPDATE conferenc SET conferenc_status_id = ?" +
            " WHERE id_conferenc = ?";
    protected static final String FIND_ALL_CONFERENC_ACTIVE = "SELECT * FROM conferenc JOIN category " +
            "ON category_id=id_category JOIN status ON conferenc_status_id=id_status WHERE conferenc_status_id=1";
    protected static final String FIND_ALL_CONFERENC = "SELECT * FROM conferenc JOIN category " +
            "ON category_id=id_category JOIN status ON conferenc_status_id=id_status";
    protected static final String FIND_ID_CONFERENC = "SELECT * FROM conferenc JOIN category " +
            "ON category_id=id_category JOIN status ON conferenc_status_id=id_status WHERE id_conferenc=?";
    protected static final String FIND_NAME_CONFERENC = "SELECT * FROM conferenc JOIN category " +
            "ON category_id=id_category JOIN status ON conferenc_status_id=id_status WHERE conferenc.name=?";
    protected static final String CONFERENC_DELETE = "DELETE FROM conferenc WHERE id_conferenc=?";
}
