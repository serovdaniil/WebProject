<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.detailsForUser" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.userById.textMain" var="textMain"/>
<fmt:message bundle="${loc}" key="label.userById.textAfterMain" var="textAfterMain"/>
<fmt:message bundle="${loc}" key="label.userById.text" var="text"/>
<fmt:message bundle="${loc}" key="label.userById.boxEmail" var="email"/>
<fmt:message bundle="${loc}" key="label.userById.boxFirstName" var="firstName"/>
<fmt:message bundle="${loc}" key="label.userById.boxId" var="id"/>
<fmt:message bundle="${loc}" key="label.userById.boxLastName" var="lastName"/>
<fmt:message bundle="${loc}" key="label.userById.boxLogin" var="login"/>
<fmt:message bundle="${loc}" key="label.userById.boxNewRole" var="newRole"/>
<fmt:message bundle="${loc}" key="label.userById.boxPassword" var="password"/>
<fmt:message bundle="${loc}" key="label.userById.boxRole" var="role"/>
<fmt:message bundle="${loc}" key="label.userById.button.updateRole" var="button"/>
<fmt:message bundle="${loc}" key="label.userById.role.admin" var="admin"/>
<fmt:message bundle="${loc}" key="label.userById.role.user" var="user"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/styleForObject.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${textMain}</h2>
<p>${textAfterMain}</p>
<h2> ${text} ${requestScope.user.firstName} ${requestScope.user.lastName}</h2>
<form name="question-form" action="${pageContext.request.contextPath}/controller?command=update_role_by_user" method="post">
    <label for="id-input">${id}</label>
    <input id="id-input" class="container" type="text" name="id" readonly
           value="${requestScope.user.id}"/>
    <label for="firstName-input">${firstName}</label>
    <input id="firstName-input" class="container" type="text" name="first_name" readonly
           value="${requestScope.user.firstName} ${requestScope.application.user.lastName}"/>
    <label for="lastName-input">${lastName}</label>
    <input id="lastName-input" class="container" type="text" name="last_name" readonly
           value="${requestScope.user.lastName}"/>
    <label for="login-input">${login}</label>
    <input id="login-input" class="container" type="text" name="login" readonly
           value="${requestScope.user.login}"/>
    <label for="email-input">${email}</label>
    <input id="email-input" class="container" type="text" name="email" readonly
           value="${requestScope.user.email}"/>
    <label for="password-input">${password}</label>
    <input id="password-input" class="container" type="password" name="password" readonly
           value="${requestScope.user.password}"/>
    <label for="role-input">${role}</label>
    <input id="role-input" class="container" type="text" name="role" readonly
           value="${requestScope.user.role}"/>
    <label for="roleNew-input">${newRole}</label>
    <select id="roleNew-input" name="roleNew">
        <option>${user}</option>
        <option>${admin}</option>
    </select>
    <button type="submit" class="button">${button}</button>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>