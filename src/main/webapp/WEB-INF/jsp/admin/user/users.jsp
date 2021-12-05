<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show user</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<br>
<table>
    <tr>
        <th>ID</th>
        <th>LOGIN</th>
        <th>EMAIL</th>
        <th>FIRST NAME</th>
        <th>LAST NAME</th>
        <th>ROLE</th>
        <th></th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <form name="application-form" action="${pageContext.request.contextPath}/controller?command=read_user_by_id" method="post">
            <tr>
                <td>
                    <input class="container" type="text" name="id" readonly value="${user.id}"/>
                </td>
                <td>
                    <input class="container" type="text" name="login" readonly value="${user.login}"/>
                </td>
                <td>
                    <input class="container" type="text" name="email" readonly value="${user.email}"/>
                </td>
                <td>
                    <input class="container" type="text" name="firstName" readonly value="${user.firstName}"/>
                </td>
                <td>
                    <input class="container" type="text" name="lastName" readonly value="${user.lastName}"/>
                </td>
                <td>
                    <input class="container" type="text" name="lastName" readonly value="${user.role}"/>
                </td>
                <td>
                    <button type="submit" >Подробнее</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>