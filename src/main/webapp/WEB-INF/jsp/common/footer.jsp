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
<body>
<style>
    <%@include file="/WEB-INF/css/footerStyle.css"%>
</style>
<div id="footer">
    <p class="footer">ITFurute.com</p>
    <p class="footer">@the author Daniil Serov</p>
</div>
</body>
</html>