<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.detailsForQuestion" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.questionId.addNewAnswer" var="addNewAnswer"/>
<fmt:message bundle="${loc}" key="label.questionId.boxAnswer" var="answer"/>
<fmt:message bundle="${loc}" key="label.questionId.boxDate" var="date"/>
<fmt:message bundle="${loc}" key="label.questionId.boxId" var="id"/>
<fmt:message bundle="${loc}" key="label.questionId.boxNewAnswer" var="newAnswer"/>
<fmt:message bundle="${loc}" key="label.questionId.boxQuestion" var="question"/>
<fmt:message bundle="${loc}" key="label.questionId.user" var="user"/>
<fmt:message bundle="${loc}" key="label.questionId.question" var="questionMain"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/styleForObject.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${questionMain} - "${requestScope.question.question}",
${user} ${requestScope.question.user.firstName} ${requestScope.question.user.lastName}</h2>
<form name="question-form" action="/controller?command=add_answer_to_question" method="post">
    <label for="id-input">${id}</label>
    <input id="id-input" class="container" type="text" name="id" readonly
           value="${requestScope.question.id}"/>
    <label for="question-input">${question}</label>
    <input id="question-input" class="container" type="text" name="name_question" readonly
           value="${requestScope.question.question}"/>
    <label for="answerQuestion-input">${answer}</label>
    <input  id="answerQuestion-input" class="container" type="text" name="answer_question" readonly
           value="${requestScope.question.answer}"/>
    <label for="date-input">${date}</label>
    <input id="date-input" class="container" type="text" name="date_question" readonly
           value="${requestScope.question.date}"/>
    <label for="answer-input">${newAnswer}</label>
    <input id="answer-input" class="container" type="text" name="answer" min="2" max="1000" required
           pattern="^.{0,1000}$"
           value=""/>
    <button type="submit" class="button">${addNewAnswer}</button>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>