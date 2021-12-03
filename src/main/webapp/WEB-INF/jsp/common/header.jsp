<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
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
</head>
<style>
    <%@include file="/WEB-INF/css/headerStyle.css"%>
</style>
<ul id="menuRight">
    <li><c:choose>
        <c:when test="${not empty sessionScope.user}">
            <a class="personal" href="/controller?command=logout">${logoutLink}</a>
        </c:when>
        <c:otherwise>
            <a class="personal" href="/controller?command=show_login">${loginLink}</a>
        </c:otherwise>
    </c:choose></li>
    <c:choose> <c:when test="${not empty sessionScope.user}">
        <li><a class="personal" href="/controller?command=show_personal_account">${personalAccountLink}</a>
        </li>
    </c:when>
        <c:otherwise>
            <li><a class="personal"
                   href="/controller?command=show_create_an_account">${registrationLink}</a></li>
        </c:otherwise>
    </c:choose>
</ul>
<ul id="menu">
    <li><a href="${pageContext.request.contextPath}/controller?command=">Home</a></li>
    <li><a href="">About</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_conferences">Conferenc</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_categories">Category</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_contact">Сontacts</a></li>
</ul>
</body>
</html>