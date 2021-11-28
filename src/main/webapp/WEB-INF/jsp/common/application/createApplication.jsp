<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find application by ID status - </title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<h3>create application</h3>

<form name="createApplication-form" action="/controller?command=create_application" method="post">
    <label for="login-input">ID application:</label>
    <input id="login-input" type="text" name="id" value=""/>
    <input type="submit" value="Create"/>
</form>

<br>
<h3> ${requestScope.result}</h3>
</br>
</body>
</html>