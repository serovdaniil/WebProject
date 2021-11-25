<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all categories</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<h3>avdsvas</h3>
<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
    </tr>
    <c:forEach var="category" items="${requestScope.categories}">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>