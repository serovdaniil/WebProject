<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all applications</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">

    <h4>Remove application</h4>
    <form name="removeApplication-form" action="/controller?command=delete_application" method="post">
        <label for="id-input">ID section conferenc:</label>
        <input id="id-input" type="text" name="id" value=""/>
        <input type="submit" value="Remove application"/>
    </form>
    <h4> Result: ${requestScope.result}</h4>
    <a href="/controller?command=show_find_by_status_result_application">Find application by status result</a>
    <br>
</c:if>
<br>

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