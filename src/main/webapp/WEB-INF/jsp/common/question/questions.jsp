<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all questions</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>Список вопросов пользователей</h2>

<table>
    <tr>
        <th>Уникальный номер</th>
        <th>Вопрос</th>
        <th>Ответ</th>
        <th>Дата создания вопроса</th>
        <th>Пользователь</th>
        <th></th>
    </tr>
    <c:forEach var="question" items="${requestScope.questions}">
        <form name="question-form" action="/controller?command=find_question_by_id" method="post">
            <tr>
                <td>
                    <input class="container" type="text" name="id" readonly value="${question.id}"/>
                </td>
                <td>
                    <input class="container" type="text" name="question" readonly value="${question.question}"/>
                </td>

                <td>
                    <input class="container" type="text" name="answer" readonly value="${question.answer}"/>
                </td>
                <td>
                    <input class="container" type="text" name="date" readonly value="${question.date}"/>
                </td>
                <td>
                    <input class="container" type="text" name="name" readonly
                           value="${question.user.firstName} ${question.user.lastName}"/>
                </td>
                <td>
                    <button type="submit" class="button">Подробнее</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>