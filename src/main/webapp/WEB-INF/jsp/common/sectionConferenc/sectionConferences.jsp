<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all SectionConferences</title>
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
        <th>DESCRIPTION</th>
        <th>CONFERENC</th>
        <%--        <th>Owner</th>--%>
    </tr>
    <c:forEach var="conferenc" items="${requestScope.sectionConferences}">
        <tr>
            <td>${conferenc.id}</td>
            <td>${conferenc.name}</td>

            <td>${conferenc.description}</td>
            <td>${conferenc.conferenc}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>