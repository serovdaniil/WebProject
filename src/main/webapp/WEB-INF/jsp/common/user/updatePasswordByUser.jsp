<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find conferenc by ID - </title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
    <p>${sessionScope.user.password}</p>
</c:if>
<h3>Update password by user</h3>

<form name="findConferencByName-form" action="/controller?command=update_password_by_user" method="post">
    <label for="login-input">password:</label>
    <input id="login-input" type="text" name="password" value=""/>
    <input type="submit" value="Update"/>
</form>

<br>
<h3> ${requestScope.user}</h3>
</br>
</body>
</html>