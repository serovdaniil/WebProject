<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.link.personalInformation" var="pesronalInformationLink"/>
<fmt:message bundle="${loc}" key="label.link.allQuestions" var="allQuestionsLink"/>
<fmt:message bundle="${loc}" key="label.link.myQuestions" var="myQuestionsLink"/>
<fmt:message bundle="${loc}" key="label.link.users" var="usersLink"/>
<fmt:message bundle="${loc}" key="label.link.allApplications" var="allApplicationsLink"/>
<fmt:message bundle="${loc}" key="label.link.myApplications" var="myApplicationsLink"/>
<fmt:message bundle="${loc}" key="label.link.personalAccount" var="personalAccountLink"/>
<html>
<head>
    <title>${personalAccountLink}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/personalAccountStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<div id="block">
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <div id="container-right">
            <p class="center-pic"><img src="/jpg/pngegg.png" alt="img"/></p>
            <p class="title">Все вопросы пользователей</p>
            <p>Просмотр всех вопросов, а также добавление ответов на вопросы и удаление их.</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_questions">${allQuestionsLink}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <div id="container">
            <p class="center-pic"><img src="/jpg/761.png" alt="img"/></p>
            <p class="title"> Личная информация</p>
            <p>Редактирование основное информации своего профиля: Email, пароль, имя, фамилия.</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_personal_infomation">${pesronalInformationLink}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.USER}">
        <div id="container">
            <p class="center-pic"><img src="/jpg/pngegg4.png" alt="img"/></p>
            <p class="title">Мои заявки</p>
            <p>Просмотр моих заявок, а также удаление.</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_applications_by_account">${myApplicationsLink}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.USER}">
        <div id="container-right">
            <p class="center-pic"><img src="/jpg/pngegg.png" alt="img"/></p>
            <p class="title">Мои вопросы</p>
            <p>Просмотр моих вопросов, а также удаление.</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=find_questions_by_id_account">${myQuestionsLink}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <div id="container-right">
            <p class="center-pic"><img src="/jpg/pngegg15.png" alt="img"/></p>
            <p class="title">Пользователи</p>
            <p>Просмотр пользователей, добавление новых полномочий, удаление.</p>
            <a class="button" href="${pageContext.request.contextPath}/controller?command=show_users">${usersLink}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <div id="container">
            <p class="center-pic"><img src="/jpg/pngegg4.png" alt="img"/></p>
            <p class="title">Просмотр заявок</p>
            <p>Все заявки, а также их обновление и удаление</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_applications">${allApplicationsLink}</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <div id="container-three">
            <p class="center-pic"><img src="/jpg/pngegg4.png" alt="img"/></p>
            <p class="title">Панель администрирования конференций</p>
            <p>Операции создания, обновления, удаления конференций</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_admin_panel_conferenc">Перейти</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <div id="container-three">
            <p class="center-pic"><img src="/jpg/pngegg4.png" alt="img"/></p>
            <p class="title">Панель администрирования секций конференций</p>
            <p>Операции создания, обновления, удаления секций конференций</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_admin_panel_section_conferenc">Перейти</a>
        </div>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <div id="container-three">
            <p class="center-pic"><img src="/jpg/pngegg4.png" alt="img"/></p>
            <p class="title">Панель администрирования категорий</p>
            <p>Операции создания, обновления, удаления категорий</p>
            <a class="button"
               href="${pageContext.request.contextPath}/controller?command=show_admin_panel_category">Перейти</a>
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
