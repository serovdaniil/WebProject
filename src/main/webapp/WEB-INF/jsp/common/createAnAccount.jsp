<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<h3>Please create an account:</h3>
<form name="login-form" action="/controller?command=create_an_account" method="post">
    <label for="login-input">Email:</label>
    <input id="login-input" type="text" name="email" value=""/>
    <br>
    <label for="password-input">Password:</label>
    <input id="password-input" type="password" name="password" value=""/>
    <br/>
    <c:if test="${not empty requestScope.errorRegistrationPassMessage}">
        <b>${requestScope.errorRegistrationPassMessage}</b>
        <br>
    </c:if>
    <input type="submit" value="Log in"/>
</form>
</body>
</html>