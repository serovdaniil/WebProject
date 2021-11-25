<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create category by id</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
    <p>ID, ${sessionScope.user.id}</p>
</c:if>
<h3>Find Conferenc by name</h3>
<form name="createConferenc-form" action="/controller?command=create_category" method="post">
    <label for="name-input">Name Category:</label>
    <input id="name-input" type="text" name="name" value=""/>
    <input type="submit" value="Create category"/>
    <br/>
</form>
<br>
<h1> ${requestScope.result}</h1>
</body>
</html>