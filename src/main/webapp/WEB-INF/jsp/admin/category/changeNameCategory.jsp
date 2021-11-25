<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change name category by id</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
    <p>ID, ${sessionScope.user.id}</p>
</c:if>
<h3>Change name category by id</h3>
<form name="changeNameCategory-form" action="/controller?command=change_name_category" method="post">
    <label for="id-input">ID Category:</label>
    <input id="id-input" type="text" name="id" value=""/>
    <label for="name-input">Change name Category:</label>
    <input id="name-input" type="text" name="name" value=""/>
    <input type="submit" value="Change name category"/>
    <br/>
</form>
<br>
<h1> ${requestScope.result}</h1>
</body>
</html>
