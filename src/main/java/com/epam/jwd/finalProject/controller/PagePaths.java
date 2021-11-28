package com.epam.jwd.finalProject.controller;

public enum PagePaths {
    INDEX("/"),
    MAIN("/WEB-INF/jsp/common/main.jsp"),
    CONFERENCES("/WEB-INF/jsp/common/conferenc/conferences.jsp"),
    FINDCONFERENCESBYNAME("/WEB-INF/jsp/common/conferenc/findConferencesByName.jsp"),
    SECTIONCONFERENCES("/WEB-INF/jsp/common/sectionConferenc/sectionConferences.jsp"),
    FINDSECTIONCONFERENCESBYNAME("/WEB-INF/jsp/common/sectionConferenc/findSectionConferencesByName.jsp"),
    FINDSECTIONCONFERENCESINCONFERENCBYID("/WEB-INF/jsp/common/sectionConferenc/findSectionConferencesInConferencById.jsp"),
    CATEGORIES("/WEB-INF/jsp/common/category/categories.jsp"),
    ALLCONFERENCESINCATEGORY("/WEB-INF/jsp/common/category/allConferencesInCategory.jsp"),
    ALLSECTIONCONFERENCESINCATEGORY("/WEB-INF/jsp/common/category/allSectionConferencesInCategory.jsp"),
    QUESTIONS("/WEB-INF/jsp/common/question/questions.jsp"),
    USERS("/WEB-INF/jsp/admin/user/users.jsp"),
    PERSONALINFORMATION("/WEB-INF/jsp/personalAccount/personalInformation.jsp"),
    READUSERBYID("/WEB-INF/jsp/admin/user/readUserById.jsp"),
    LOGIN("/WEB-INF/jsp/common/login.jsp"),
    CREATEANACCOUNT("/WEB-INF/jsp/common/createAnAccount.jsp"),
    APPLICATIONS("/WEB-INF/jsp/admin/application/applications.jsp"),
    APPLICATIONSBYACCOUNT("/WEB-INF/jsp/common/application/applicationsByAccount.jsp"),
    APPLICATIONSBYSTATUSRESULT("/WEB-INF/jsp/admin/application/findByStatusResultApplication.jsp"),
    PERSONALACCOUNT("/WEB-INF/jsp/personalAccount/personalAccount.jsp"),
    FINDQUESTIONSBYIDACCOUNT("/WEB-INF/jsp/common/question/questionsAccountId.jsp"),
    CONTACT("/WEB-INF/jsp/personalAccount/contact.jsp"),
    ERROR("/WEB-INF/jsp/common/error/error.jsp");

    private final String path;

    PagePaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static PagePaths of(String name) {
        for (PagePaths page : values()) {
            if (page.name().equalsIgnoreCase(name)) {
                return page;
            }
        }
        return MAIN;
    }
}
