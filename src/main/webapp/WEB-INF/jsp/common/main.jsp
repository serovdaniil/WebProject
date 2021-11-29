<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.link.home" var="homeLink"/>
<fmt:message bundle="${loc}" key="label.link.conferences" var="conferencesLink"/>
<fmt:message bundle="${loc}" key="label.link.sectionConferences" var="sectionConferencesLink"/>
<fmt:message bundle="${loc}" key="label.link.category" var="categoryLink"/>
<fmt:message bundle="${loc}" key="label.link.contacts" var="contactsLink"/>
<fmt:message bundle="${loc}" key="label.link.login" var="loginLink"/>
<fmt:message bundle="${loc}" key="label.link.logout" var="logoutLink"/>
<fmt:message bundle="${loc}" key="label.link.personalAccount" var="personalAccountLink"/>
<fmt:message bundle="${loc}" key="label.link.registration" var="registrationLink"/>

<html>
<head>
    <title>${pageTitle}<</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/welcomebackground.css"%>
    <%@include file="/WEB-INF/css/loginStyle.css"%>
</style>

<ul>
    <li><a href="${pageContext.request.contextPath}/controller?command=">${homeLink}</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_conferences">${conferencesLink}</a></li>
    <li>
        <a href="${pageContext.request.contextPath}/controller?command=show_section_conferences">${sectionConferencesLink}</a>
    </li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_categories">${categoryLink}</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_contact">${contactsLink}</a></li>
    <li><c:choose>
        <c:when test="${not empty sessionScope.user}">
            <a href="/controller?command=logout">${logoutLink}</a>
        </c:when>
        <c:otherwise>
            <a href="/controller?command=show_login">${loginLink}</a>
        </c:otherwise>
    </c:choose></li>
    <c:choose> <c:when test="${not empty sessionScope.user}">
        <li><a href="/controller?command=show_personal_account">${personalAccountLink}</a></li>
    </c:when>
        <c:otherwise>
            <li><a href="/controller?command=show_create_an_account">${registrationLink}</a></li>
        </c:otherwise>
    </c:choose>
</ul>
<br>
<h1>${helloMessage}</h1>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
    <p>${sessionScope.user.role}</p>
</c:if>

<a href="/controller?command=show_find_conferences_by_name">find Conferences by name</a>
<br>
<br>
<a href="/controller?command=show_all_conferences_in_category">conferences in category</a>
<br>
<a href="/controller?command=show_all_section_conferences_in_category">section conferences in category</a>
<br>
</body>
</html>
