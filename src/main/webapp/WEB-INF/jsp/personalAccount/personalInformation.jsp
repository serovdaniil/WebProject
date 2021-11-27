<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal information</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
    <p>${sessionScope.user.password}</p>
</c:if>
<h2>Personal information</h2>

<form name="updateFirstName-form" action="/controller?command=update_first_name_by_user" method="post">
    <label for="firstName-input">First name:</label>
    <input id="firstName-input" type="text" name="firstName" value="${sessionScope.user.firstName}" />
    <input type="submit" value="Update first name"/>
</form>
<br>
<form name="updateLastName-form" action="/controller?command=update_last_name_by_user" method="post">
    <label for="lastName-input">Last name:</label>
    <input id="lastName-input" type="text" name="lastName" value="${sessionScope.user.lastName}" />
    <input type="submit" value="Update last name"/>
</form>
<br>
<form name="updatePassword-form" action="/controller?command=update_password_by_user" method="post">
    <label for="password-input">Password:</label>
    <input id="password-input" type="password" name="password" value="${sessionScope.user.password}"/>
    <input type="submit" value="Update password"/>
</form>
<br>
<form name="updateEmail-form" action="/controller?command=update_email_by_user" method="post">
    <label for="email-input">Email:</label>
    <input id="email-input" type="text" name="email" value="${sessionScope.user.email}" />
    <input type="submit" value="Update email"/>
</form>
<br>

<h3> ${requestScope.user}</h3>
</br>
</body>
</html>