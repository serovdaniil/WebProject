<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.email" var="email"/>
<fmt:message bundle="${loc}" key="label.password" var="password"/>
<fmt:message bundle="${loc}" key="label.link.registration" var="registrationLink"/>
<html>
<head>
    <title>${registrationLink}</title>
</head>
<body>
<h3>${registrationLink}:</h3>
<form name="login-form" action="/controller?command=create_an_account" method="post">
    <label for="login-input">${email}:</label>
    <input id="login-input" type="text" name="email" value=""/>
    <br>
    <label for="password-input">${password}:</label>
    <input id="password-input" type="password" name="password" value=""/>
    <br/>
    <c:if test="${not empty requestScope.errorRegistrationPassMessage}">
        <b>${requestScope.errorRegistrationPassMessage}</b>
        <br>
    </c:if>
    <input type="submit" value="${registrationLink}"/>
</form>
</body>
</html>