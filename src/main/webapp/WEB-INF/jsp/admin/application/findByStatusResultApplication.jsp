<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find applications by id status result</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<h3>Find applications by id status result</h3>

<form name="idCategory-form" action="/controller?command=find_by_status_result_application" method="post">
    <label for="login-input">ID status application:</label>
    <input id="login-input" type="text" name="id" value=""/>
    <input type="submit" value="Search"/>
</form>

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
</br>
</body>
</html>
