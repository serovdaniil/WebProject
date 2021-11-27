<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show user by id</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<form name="updateRole-form" action="/controller?command=read_user_by_id" method="post">
    <label for="idAccount-input">Id account:</label>
    <input id="idAccount-input" type="text" name="id" value=""/>
    <input type="submit" value="Read user"/>
</form>
<br>
        <h3>${requestScope.user}</h3>

</body>
</html>