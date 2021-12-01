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
<fmt:setLocale value="${lang}" scope="session"/>
<html>
<head>
    <title>${pageTitle}<</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/loginStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<a href="${request.contextPath}controller?command=main_page&lang=en_EN">EN</a>
<a href="${request.contextPath}controller?command=main_page&lang=ru_RU">BE</a>
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
<br>
<a href="/controller?command=show_find_section_conferences_in_conferenc_by_id">sec----- conferences in category</a>
<br>
<form name="removeConferenc-form" action="/controller?command=remove_conferenc_by_id" method="post">
    <label for="id-input">ID conferenc:</label>
    <input id="id-input" type="text" name="id" value=""/>
    <input type="submit" value="Remove"/>
</form>
</body>
</html>