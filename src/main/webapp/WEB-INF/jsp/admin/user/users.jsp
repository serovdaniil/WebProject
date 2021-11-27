<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show user</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<form name="updateRole-form" action="/controller?command=update_role_by_user" method="post">
    <label for="idAccount-input">Id account:</label>
    <input id="idAccount-input" type="text" name="id" value=""/>
    <br>
    <label for="role-input">Name role:</label>
    <input id="role-input" type="text" name="role" value="" />
    <input type="submit" value="Update role"/>
</form>
<br>
<table>
    <tr>
        <th>ID</th>
        <th>LOGIN</th>
        <th>EMAIL</th>
        <th>PASSWORD</th>
        <th>FIRST NAME</th>
        <th>LAST NAME</th>
        <th>ROLE</th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.password}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.role}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>