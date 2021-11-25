<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find conferenc by ID - </title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<h3>Find Conferenc by name</h3>

<form name="findConferencByName-form" action="/controller?command=find_conferenc_by_id" method="post">
    <label for="login-input">ID conferenc:</label>
    <input id="login-input" type="text" name="id" value=""/>
    <input type="submit" value="Search"/>
</form>

<br>
<h3> ${requestScope.conferences}</h3>
</br>
</body>
</html>