<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applications by account</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
    <p>${sessionScope.user.id}</p>
</c:if>

<table>
    <tr>
        <th>ID</th>
        <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
            <th>USER ID</th>
            <th>USER NAME</th>
        </c:if>
        <th>SECTION CONFERENC ID</th>
        <th>SECTION CONFERENC NAME</th>
        <th>RESULT</th>
    </tr>
    <c:forEach var="application" items="${requestScope.applications}">
        <tr>
            <td>${application.id}</td>
            <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
                <td>${application.user.id}</td>
                <td>${application.user.firstName}</td>
            </c:if>
            <td>${application.sectionConferenc.id}</td>
            <td>${application.sectionConferenc.name}</td>
            <td>${application.result.result}</td>
        </tr>
    </c:forEach>
</table>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
