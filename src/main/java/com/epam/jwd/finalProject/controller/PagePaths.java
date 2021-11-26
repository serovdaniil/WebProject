package com.epam.jwd.finalProject.controller;


public enum PagePaths {
    INDEX("/"),
    MAIN("/WEB-INF/jsp/common/main.jsp"),
    CONFERENCES("/WEB-INF/jsp/common/conferenc/conferences.jsp"),
    FINDCONFERENCESBYNAME("/WEB-INF/jsp/common/conferenc/findConferencesByName.jsp"),
    UPDATEDESCRIPTIONINCONFERENC("/WEB-INF/jsp/admin/conferenc/updateDescriptionInConferenc.jsp"),
    FINDCONFERENCESBYID("/WEB-INF/jsp/admin/conferenc/findConferencById.jsp"),
    REMOVECONFERENCESBYID("/WEB-INF/jsp/admin/conferenc/removeConferencById.jsp"),
    CREATECONFERENC("/WEB-INF/jsp/admin/conferenc/createConferenc.jsp"),
    SECTIONCONFERENCES("/WEB-INF/jsp/common/sectionConferenc/sectionConferences.jsp"),
    FINDSECTIONCONFERENCESBYID("/WEB-INF/jsp/admin/sectionConferenc/findSectionConferencById.jsp"),
    REMOVESECTIONCONFERENCESBYID("/WEB-INF/jsp/admin/sectionConferenc/removeSectionConferencById.jsp"),
    CREATESECTIONCONFERENC("/WEB-INF/jsp/admin/sectionConferenc/createSectionConferenc.jsp"),
    UPDATEDESCRIPTIONINSECTIONCONFERENC("/WEB-INF/jsp/admin/sectionConferenc/updateDescriptionInSectionConferenc.jsp"),
    FINDSECTIONCONFERENCESBYNAME("/WEB-INF/jsp/common/sectionConferenc/findSectionConferencesByName.jsp"),
    FINDSECTIONCONFERENCESINCONFERENCBYID("/WEB-INF/jsp/common/sectionConferenc/findSectionConferencesInConferencById.jsp"),
    CATEGORIES("/WEB-INF/jsp/common/category/categories.jsp"),
    CREATECATEGORY("/WEB-INF/jsp/admin/category/createCategory.jsp"),
    CHANGENAMECATEGORY("/WEB-INF/jsp/admin/category/changeNameCategory.jsp"),
    ALLCONFERENCESINCATEGORY("/WEB-INF/jsp/common/category/allConferencesInCategory.jsp"),
    ALLSECTIONCONFERENCESINCATEGORY("/WEB-INF/jsp/common/category/allSectionConferencesInCategory.jsp"),
    IDCATEGORY("/WEB-INF/jsp/admin/category/idCategory.jsp"),
    REMOVECATEGORYBYID("/WEB-INF/jsp/admin/category/removeCategoryById.jsp"),
    QUESTIONS("/WEB-INF/jsp/common/question/questions.jsp"),

    LOGIN("/WEB-INF/jsp/common/login.jsp"),
    CREATEANACCOUNT("/WEB-INF/jsp/common/createAnAccount.jsp"),
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
