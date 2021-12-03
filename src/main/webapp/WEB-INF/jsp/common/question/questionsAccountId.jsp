<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Show all conferences</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>Ваши вопросы</h2>
<c:if test="${not empty requestScope.questions}">
        <p>Проверьте ответы на вопросы, которые Вы задавали ранее. Если у Вас остались вопросы, то задайте и наш администратор ответит, как можно скорее.</p>
        <table>
            <tr>
                <th>Уникальный номер</th>
                <th>Вопрос</th>
                <th>Ответ</th>
                <th>Дата когда был задан вопрос</th>
                <th></th>
            </tr>
            <c:forEach var="question" items="${requestScope.questions}">
                <form name="question-form" action="/controller?command=remove_question_by_id" method="post">

                    <tr>
                        <td>
                            <input id="id-input" class="container" type="text" name="id" readonly
                                   value="${question.id}"/>
                        </td>
                        <td>
                            <input id="name-input" class="container" type="text" name="name" readonly
                                   value="${question.question}"/>
                        </td>
                        <td>
                            <input id="answer-input" class="container" type="text" name="answer" readonly
                                   value="${question.answer}"/>
                        </td>
                        <td>
                            <input id="date-input" class="container" type="text" name="date" readonly
                                   value="${question.date}"/>
                        </td>
                        <td>
                            <button type="submit" class="button">Удалить</button>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </table>
</c:if>
        <p>Если остались вопросы, то будем рады на них ответить.</p>
        <form name="createQuestion-form" action="/controller?command=create_question" method="post">
            <label for="nameNewQuestionLogin-input">Вопрос:</label>
            <input id="nameNewQuestionLogin-input" type="text" required name="name" pattern="^[A-ZА-Яa-zа-я]+((\s)?((\'|\-|\.|\,)?([A-ZА-Яa-zа-я])?[^0-9]+))*$" value=""/>
            <button type="submit" class="create">Задать вопрос</button>
        </form>

<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
