<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all questions</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h4>Add answer to question</h4>
<form name="addAnswer-form" action="/controller?command=add_answer_to_question" method="post">
    <label for="id-input">ID:</label>
    <input id="id-input" type="text" name="id" value=""/>
    <br>
    <label for="answer-input">Answer:</label>
    <input id="answer-input" type="text" name="answer" value=""/>
    <input type="submit" value="Add answer"/>
    <br/>
</form>
<h4> Result: ${requestScope.result}</h4>
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
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>