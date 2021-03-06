<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jwds" uri="jwd.epam.com" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.header.home" var="main"/>
<fmt:message bundle="${loc}" key="label.header.welcome" var="welcome"/>
<fmt:message bundle="${loc}" key="label.header.conferences" var="conferences"/>
<fmt:message bundle="${loc}" key="label.header.about" var="about"/>
<fmt:message bundle="${loc}" key="label.header.categories" var="category"/>
<fmt:message bundle="${loc}" key="label.header.contacts" var="contacts"/>
<fmt:message bundle="${loc}" key="label.header.registration" var="registration"/>
<fmt:message bundle="${loc}" key="label.header.personalAccount" var="personalAccount"/>
<fmt:message bundle="${loc}" key="label.header.login" var="login"/>
<fmt:message bundle="${loc}" key="label.header.logout" var="logout"/>

<script src="js/language-switch.js" type="text/javascript"></script>
<script src="js/print-cash-account-in-header.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/account-drop-down-menu.css">
<html>
<head>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/headerStyle.css"%>
</style>
<ul id="menu">
    <li><a href="${pageContext.request.contextPath}/controller?command=">${main}</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_about_page">${about}</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_conferences_with_pagination&page=1">${conferences}</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_categories&page=1">${category}</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_contact">${contacts}</a></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li><a class="dropdown-item" onclick="setLocaleAndReloadPage('ru_RU')" href="#">
        RU
    </a></li>
    <li><a class="dropdown-item" onclick="setLocaleAndReloadPage('en_US')" href="">
        EN
    </a></li>
    <li><a class="dropdown-item" onclick="setLocaleAndReloadPage('fr_FR')" href="#">
        FR
    </a></li>
    <li><a class="dropdown-item" onclick="setLocaleAndReloadPage('be_BE')" href="">
        BE
    </a></li>
    <li></li>
    <li></li>
    <li><c:choose>
        <c:when test="${not empty sessionScope.user}">
            <a class="personal" href="${pageContext.request.contextPath}/controller?command=logout">${logout}</a>
        </c:when>
        <c:otherwise>
            <a class="personal" href="${pageContext.request.contextPath}/controller?command=show_login">${login}</a>
        </c:otherwise>
    </c:choose></li>
    <c:choose> <c:when test="${not empty sessionScope.user}">
        <li><a class="personal"
               href="${pageContext.request.contextPath}/controller?command=show_personal_account">${personalAccount}</a>
        </li>
    </c:when>
        <c:otherwise>
            <li><a class="personal"
                   href="${pageContext.request.contextPath}/controller?command=show_create_an_account">${registration}</a>
            </li>
        </c:otherwise>
    </c:choose>
</ul>
</body>
</html>