<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all categories</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
    <p>${sessionScope.user.id}</p>
</c:if>

<table>
    <tr>
        <th>ID</th>
        <th>USER ID</th>
        <th>USER NAME</th>
        <th>SECTION CONFERENC ID</th>
        <th>SECTION CONFERENC NAME</th>
        <th>RESULT</th>
    </tr>
    <c:forEach var="application" items="${requestScope.applications}">
        <tr>
            <td>${application.id}</td>
            <td>${application.user.id}</td>
            <td>${application.user.firstName}</td>
            <td>${application.sectionConferenc.id}</td>
            <td>${application.sectionConferenc.name}</td>
            <td>${application.result.result}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
