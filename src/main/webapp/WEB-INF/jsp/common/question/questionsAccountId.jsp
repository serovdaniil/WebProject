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
        <th>QUESTION</th>
        <th>ANSWER</th>
        <th>DATE</th>
        <th>USER</th>
        <th></th>
    </tr>
    <c:forEach var="question" items="${requestScope.questions}">
        <tr>
            <td>${question.id}</td>
            <td>${question.question}</td>

            <td>${question.answer}</td>
            <td>${question.date}</td>
            <td>${question.user.firstName} ${question.user.lastName}</td>
            <td></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
