<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.questions" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.questions.answer" var="answer"/>
<fmt:message bundle="${loc}" key="label.questions.date" var="date"/>
<fmt:message bundle="${loc}" key="label.questions.question" var="question"/>
<fmt:message bundle="${loc}" key="label.questions.id" var="id"/>
<fmt:message bundle="${loc}" key="label.questions.more" var="more"/>
<fmt:message bundle="${loc}" key="label.questions.textMain" var="textMain"/>
<fmt:message bundle="${loc}" key="label.questions.user" var="user"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${textMain}</h2>
<table>
    <tr>
        <th>${id}</th>
        <th>${question}</th>
        <th>${answer}</th>
        <th>${date}</th>
        <th>${user}</th>
        <th></th>
    </tr>
    <c:forEach var="question" items="${requestScope.questions}">
        <form name="question-form" action="${pageContext.request.contextPath}/controller?command=find_question_by_id"
              method="post">
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
                    <button type="submit" class="button">${more}</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>