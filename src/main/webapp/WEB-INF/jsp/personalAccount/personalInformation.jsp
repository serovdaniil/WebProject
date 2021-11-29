<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.link.personalInformation" var="pesronalInformationLink"/>
<fmt:message bundle="${loc}" key="label.result" var="result"/>
<fmt:message bundle="${loc}" key="label.update" var="update"/>
<fmt:message bundle="${loc}" key="label.firstName" var="firstName"/>
<fmt:message bundle="${loc}" key="label.lastName" var="lastName"/>
<fmt:message bundle="${loc}" key="label.email" var="email"/>
<fmt:message bundle="${loc}" key="label.password" var="password"/>
<fmt:message bundle="${loc}" key="label.updateLastName" var="updateLastName"/>
<fmt:message bundle="${loc}" key="label.updateEmail" var="updateEmail"/>
<fmt:message bundle="${loc}" key="label.updatePassword" var="updatePassword"/>

<html>
<head>
    <title>${pesronalInformationLink}</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<h2>${pesronalInformationLink}</h2>

<form name="updateFirstName-form" action="/controller?command=update_first_name_by_user" method="post">
    <label for="firstName-input">${firstName}:</label>
    <input id="firstName-input" type="text" name="firstName" value="${sessionScope.user.firstName}" />
    <input type="submit" value="${update} ${firstName}"/>
</form>
<br>
<form name="updateLastName-form" action="${pageContext.request.contextPath}/controller?command=update_last_name_by_user" method="post">
    <label for="lastName-input">${lastName}:</label>
    <input id="lastName-input" type="text" name="lastName" value="${sessionScope.user.lastName}" />
    <input type="submit" value="${updateLastName}"/>
</form>
<br>
<form name="updatePassword-form" action="${pageContext.request.contextPath}/controller?command=update_password_by_user" method="post">
    <label for="password-input">${password}:</label>
    <input id="password-input" type="password" name="password" value="${sessionScope.user.password}"/>
    <input type="submit" value="${updatePassword}"/>
</form>
<br>
<form name="updateEmail-form" action="${pageContext.request.contextPath}/controller?command=update_email_by_user" method="post">
    <label for="email-input">${email}:</label>
    <input id="email-input" type="text" name="email" value="${sessionScope.user.email}" />
    <input type="submit" value="${updateEmail}"/>
</form>
<br>

<h3> ${result}: ${requestScope.user}</h3>
</br>
</body>
</html>