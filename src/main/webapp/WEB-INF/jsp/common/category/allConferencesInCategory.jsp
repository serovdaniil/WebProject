<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all conferences in category</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<h3>avdsvas</h3>
<form name="findConferencesInCategory-form" action="/controller?command=all_conferences_in_category" method="post">
    <label for="login-input">Name:</label>
    <input id="login-input" type="text" name="id" value=""/>
    <input type="submit" value="Search"/>
</form>
<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>Description</th>
        <th>CategoryName</th>
    </tr>
    <c:forEach var="conferenc" items="${requestScope.conferences}">
        <tr>
            <td>${conferenc.id}</td>
            <td>${conferenc.name}</td>
            <td>${conferenc.description}</td>
            <td>${conferenc.category.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
