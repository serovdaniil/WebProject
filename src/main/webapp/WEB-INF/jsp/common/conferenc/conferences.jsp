<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all conferences</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>DESCRIPTION</th>
        <th>CATEGORY</th>
        <%--        <th>Owner</th>--%>
    </tr>
    <c:forEach var="conferenc" items="${requestScope.conferences}">
        <tr>
            <td>${conferenc.id}</td>
            <td>${conferenc.name}</td>

            <td>${conferenc.description}</td>
            <td>${conferenc.category}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
