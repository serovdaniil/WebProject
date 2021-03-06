<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.questionsForUser" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.questionAccountId.boxAnswer" var="answer"/>
<fmt:message bundle="${loc}" key="label.questionAccountId.boxDate" var="date"/>
<fmt:message bundle="${loc}" key="label.questionAccountId.boxId" var="id"/>
<fmt:message bundle="${loc}" key="label.questionAccountId.boxQuestion" var="question"/>
<fmt:message bundle="${loc}" key="label.questionAccountId.textMain" var="textMain"/>
<fmt:message bundle="${loc}" key="label.questionAccountId.text" var="text"/>
<fmt:message bundle="${loc}" key="label.questionAccountId.taxtAfterTable" var="afterText"/>
<fmt:message bundle="${loc}" key="label.questionAccountId.button.create" var="create"/>
<fmt:message bundle="${loc}" key="label.questionAccountId.button.remove" var="remove"/>
<fmt:message bundle="${loc}" key="label.questionAccountId.boxNewQuestion" var="newQuestion"/>
<fmt:message bundle="${loc}" key="label.message.duplicateQuestion" var="message"/>
<fmt:message bundle="${loc}" key="label.message.exception.common" var="exceptionCommon"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
    <%@include file="/WEB-INF/css/pagination.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${textMain}</h2>
<c:if test="${not empty requestScope.errorDuplicatePassMessage}">
    <p><b>${message}</b></p>
    <br>
</c:if>
<c:if test="${not empty requestScope.questions}">
    <p>${text}</p>
    <table>
        <tr>
            <th>${id}</th>
            <th>${question}</th>
            <th>${answer}</th>
            <th>${date}</th>
            <th></th>
        </tr>
        <c:forEach var="question" items="${requestScope.questions}">
            <form name="question-form"
                  action="${pageContext.request.contextPath}/controller?command=remove_question_by_id" method="post">
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
                        <button type="submit" class="button">${remove}</button>
                    </td>
                </tr>
            </form>
        </c:forEach>
    </table>
</c:if>
<p>${afterText}</p>
<form name="createQuestion-form" action="${pageContext.request.contextPath}/controller?command=create_question"
      method="post">
    <label for="nameNewQuestionLogin-input">${newQuestion}</label>
    <input id="nameNewQuestionLogin-input" type="text" required min="2" max="1000" name="name"
           pattern="^.{2,1000}$" value=""  oninput="validateName(this)"/>
    <button type="submit" class="create">${create}</button>
</form>
<div id="container">
    <ul class="pagination">
        <c:forEach var="pageNum" begin="1" end="${requestScope.maxPagesCount}">
            <li>
                <a href="${pageContext.request.contextPath}/controller?command=find_questions_by_id_account&page=${pageNum}">
                        ${pageNum}</a>
            </li>
        </c:forEach>
    </ul>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
<script>
    var regexName=/^.{2,1000}$/;
    function validateName(input) {
        if (!regexName.test(input.value)) {
            input.setCustomValidity("${exceptionCommon}");
        }else{input.setCustomValidity("");}
    }
</script>