<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.link.contacts" var="contactsLink"/>
<fmt:message bundle="${loc}" key="label.result" var="result"/>
<fmt:message bundle="${loc}" key="label.question" var="question"/>
<fmt:message bundle="${loc}" key="label.createQuestion" var="createQuestion"/>
<fmt:message bundle="${loc}" key="label.create" var="create"/>

<html>
<head>
    <title>${contactsLink}</title>
</head>
<body>
<h3>${createQuestion}</h3>

<form name="createQuestion-form" action="/controller?command=create_question" method="post">
    <label for="name-input">${question}:</label>
    <input id="name-input" type="text" required min="1" max="245" name="name" value=""/>
    <input type="submit" value="${create}"/>
</form>
<h4> ${result}: ${requestScope.result}</h4>
</body>
</html>