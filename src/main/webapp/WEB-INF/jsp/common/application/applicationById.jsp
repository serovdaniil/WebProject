<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.detailsForApplication" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.applicationById.textApplication" var="textMain"/>
<fmt:message bundle="${loc}" key="label.applicationById.boxConferenc" var="conferenc"/>
<fmt:message bundle="${loc}" key="label.applicationById.boxId" var="id"/>
<fmt:message bundle="${loc}" key="label.applicationById.boxNewStatus" var="newStatus"/>
<fmt:message bundle="${loc}" key="label.applicationById.boxSection" var="section"/>
<fmt:message bundle="${loc}" key="label.applicationById.boxStatus" var="status"/>
<fmt:message bundle="${loc}" key="label.applicationById.boxUser" var="user"/>
<fmt:message bundle="${loc}" key="label.applicationById.button.updateStatus" var="button"/>
<fmt:message bundle="${loc}" key="label.applicationById.status.completed" var="completed"/>
<fmt:message bundle="${loc}" key="label.applicationById.status.open" var="open"/>
<fmt:message bundle="${loc}" key="label.applicationById.status.remove" var="remove"/>
<fmt:message bundle="${loc}" key="label.applicationById.status.waiting" var="waiting"/>
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
<h2> ${textMain} ${requestScope.application.user.firstName} ${requestScope.application.user.lastName}</h2>
<form name="question-form" action="${pageContext.request.contextPath}/controller?command=update_status_result_application" method="post">
    <label for="id-input">${id}</label>
    <input id="id-input" class="container" type="text" name="id" readonly
           value="${requestScope.application.id}"/>
    <label for="nameUser-input">${user}</label>
    <input id="nameUser-input" class="container" type="text" name="name_user" readonly
           value="${requestScope.application.user.firstName} ${requestScope.application.user.lastName}"/>
    <label for="sectionConferenc-input">${section}</label>
    <input id="sectionConferenc-input" class="container" type="text" name="section_confernec" readonly
           value="${requestScope.application.sectionConferenc.name}"/>
    <label for="conferenc-input">${conferenc}</label>
    <input id="conferenc-input" class="container" type="text" name="confernec" readonly
           value="${requestScope.application.sectionConferenc.conferenc.name}"/>
    <label for="result-input">${status}</label>
    <input id="result-input" class="container" type="text" name="result" readonly
           value="${requestScope.application.result.result}"/>
    <label for="resultNew-input">${newStatus}</label>
    <select id="resultNew-input" name="resultNew">
        <option>${open}</option>
        <option>${waiting}</option>
        <option>${completed}</option>
        <option>${remove}</option></select>
    <button type="submit" class="button">${button}</button>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>