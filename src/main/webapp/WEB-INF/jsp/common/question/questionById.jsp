<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Show all conferences</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/styleForObject.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>Вопрос: ${requestScope.question.question}, от пользователя ${requestScope.question.user.firstName} ${requestScope.question.user.lastName}</h2>
<form name="question-form" action="/controller?command=add_answer_to_question" method="post">
    <label for="id-input">Уникальный номер:</label>
    <input id="id-input" class="container" type="text" name="id" readonly
           value="${requestScope.question.id}"/>
    <label for="question-input">Вопрос:</label>
    <input id="question-input" class="container" type="text" name="name_question" readonly
           value="${requestScope.question.question}"/>
    <label for="answerQuestion-input">Ответ:</label>
    <input  id="answerQuestion-input" class="container" type="text" name="answer_question" readonly
           value="${requestScope.question.answer}"/>
    <label for="date-input">Дата вопроса:</label>
    <input id="date-input" class="container" type="text" name="date_question" readonly
           value="${requestScope.question.date}"/>
    <label for="answer-input">Ответ для обнолвения вопроса:</label>
    <input id="answer-input" class="container" type="text" name="answer" required
           pattern="^[A-ZА-Яa-zа-я]+((\s)?((\'|\-|\.|\,)?([A-ZА-Яa-zа-я])+))*$"
           value=""/>
    <button type="submit" class="button">Добавить ответ</button>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>