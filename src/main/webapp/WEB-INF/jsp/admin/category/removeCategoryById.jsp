<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Remove Category </title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<h3>Remove Category</h3>

<form name="removeCategory-form" action="/controller?command=remove_category_by_id" method="post">
    <label for="login-input">ID category:</label>
    <input id="login-input" type="text" name="id" value=""/>
    <input type="submit" value="Remove"/>
</form>

<br>
<h3> ${requestScope.result}</h3>
</br>
</body>
</html>
