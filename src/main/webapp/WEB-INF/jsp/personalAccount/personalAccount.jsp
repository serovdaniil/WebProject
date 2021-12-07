<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.personalAccount" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockUsers.title" var="blockUsersTitle"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockUsers.button" var="blockUsersButton"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockUsers.text" var="blockUsersText"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockQuestion.title" var="blockQuestionTitle"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockQuestion.text" var="blockQuestionText"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockQuestion.button" var="blockQuestionButton"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockApplications.button" var="blockApplicationsButton"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockApplications.text" var="blockApplicationsText"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockApplications.title" var="blockApplicationsTitle"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockMyApplication.title" var="blockMyApplicationTitle"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockMyApplication.text" var="blockMyApplicationText"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockMyApplication.button" var="blockMyApplicationButton"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockMyQuestion.title" var="blockMyQuestionTitle"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockMyQuestion.text" var="blockMyQuestionText"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockMyQuestion.button" var="blockMyQuestionButton"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockMyUser.title" var="blockMyUserTitle"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockMyUser.text" var="blockMyUserText"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockMyUser.button" var="blockMyUserButton"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockPanelCategory.text" var="blockPanelCategoryText"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockPanelCategory.title" var="blockPanelCategoryTitle"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockPanelConferenc.text" var="blockPanelConferencText"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockPanelConferenc.title" var="blockPanelConferencTitle"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockPanelSectionConferenc.text" var="blockPanelSectionConferencText"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockPanelSectionConferenc.title" var="blockPanelSectionConferencTitle"/>
<fmt:message bundle="${loc}" key="label.personalAccount.blockPanel.button" var="blockPanelButton"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/personalAccountStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<div id="block">
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <div id="container-right">
            <p class="center-pic"><img src="/jpg/questionByAccount.png" alt="img"/></p>
            <p class="title">${blockQuestionTitle}й</p>
            <p>${blockQuestionText}</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_questions">${blockQuestionButton}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <div id="container">
            <p class="center-pic"><img src="/jpg/account.png" alt="img"/></p>
            <p class="title">${blockMyUserTitle}</p>
            <p>${blockMyUserText}</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_personal_infomation">${blockMyUserButton}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.USER}">
        <div id="container">
            <p class="center-pic"><img src="/jpg/applications.png" alt="img"/></p>
            <p class="title">${blockMyQuestionTitle}</p>
            <p>${blockMyQuestionText}</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_applications_by_account">${blockMyQuestionButton}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.USER}">
        <div id="container-right">
            <p class="center-pic"><img src="/jpg/questionByAccount.png" alt="img"/></p>
            <p class="title">${blockMyQuestionTitle}</p>
            <p>${blockMyQuestionText}</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=find_questions_by_id_account">${blockMyQuestionButton}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <div id="container-right">
            <p class="center-pic"><img src="/jpg/users.png" alt="img"/></p>
            <p class="title">${blockUsersTitle}</p>
            <p>${blockUsersText}</p>
            <a class="button" href="${pageContext.request.contextPath}/controller?command=show_users">${blockUsersButton}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <div id="container">
            <p class="center-pic"><img src="/jpg/applications.png" alt="img"/></p>
            <p class="title">${blockApplicationsTitle}</p>
            <p>${blockApplicationsText}</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_applications">${blockApplicationsButton}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <div id="container-three">
            <p class="center-pic"><img src="/jpg/admin.png" alt="img"/></p>
            <p class="title">${blockPanelConferencTitle}</p>
            <p>${blockPanelConferencText}</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_admin_panel_conferenc">${blockPanelButton}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <div id="container-three">
            <p class="center-pic"><img src="/jpg/admin.png" alt="img"/></p>
            <p class="title">${blockPanelSectionConferencTitle}</p>
            <p>${blockPanelSectionConferencText}</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_admin_panel_section_conferenc">${blockPanelButton}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <div id="container-three">
            <p class="center-pic"><img src="/jpg/admin.png" alt="img"/></p>
            <p class="title">${blockPanelCategoryTitle}</p>
            <p>${blockPanelCategoryText}</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_admin_panel_category">${blockPanelButton}</a>
        </div>
    </c:if>
    <%--<c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <div id="container-center">
            <p class="center-pic"><img src="/jpg/pngegg7.png" alt="img"/></p>
            <p class="title">Поиск пользователя по id</p>
            <p>Страница для детального просмотра пользователя по id.</p>
            <a class="button" href="${pageContext.request.contextPath}/controller?command=show_read_user_by_id">Find user
                by id</a>
        </div>
    </c:if>--%>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
