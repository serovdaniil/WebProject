<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.useful.readConferenc" var="textMain"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.boxId" var="id"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.boxName" var="name"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.boxDescription" var="description"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.boxCategory" var="category"/>
<fmt:message bundle="${loc}" key="label.useful.text.status" var="status"/>
<fmt:message bundle="${loc}" key="label.useful.text.status.active" var="activeStatus"/>
<fmt:message bundle="${loc}" key="label.useful.text.status.delete" var="deleteStatus"/>
<fmt:message bundle="${loc}" key="label.useful.text.status" var="status"/>
<fmt:message bundle="${loc}" key="label.useful.button.status.updateStatus" var="button"/>
<fmt:message bundle="${loc}" key="label.useful.readConferenc.newStatus" var="newStatus"/>
<html>
<head>
    <title>${textMain}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/styleForObject.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${textMain}</h2>
<form name="question-form" action="${pageContext.request.contextPath}/controller?command=change_status_conferenc_by_id"
      method="post">
    <label for="conferencId-input">${id}</label>
    <input id="conferencId-input" class="container" type="text" name="id" readonly
           value="${requestScope.conferenc.id}"/>
    <label for="conferencName-input">${name}</label>
    <input id="conferencName-input" class="container" type="text" name="name" readonly
           value="${requestScope.conferenc.name}"/>
    <label for="conferencDescription-input">${description}</label>
    <input id="conferencDescription-input" class="container" type="text" name="description" readonly
           value="${requestScope.conferenc.description}"/>
    <label for="conferencCategoryName-input">${category}</label>
    <input id="conferencCategoryName-input" class="container" type="text" name="category" readonly
           value="${requestScope.conferenc.category.name}"/>
    <label for="conferencStatusName-input">${status}</label>
    <input id="conferencStatusName-input" class="container" type="text" name="status" readonly
           value="${requestScope.conferenc.status.status}"/>
    <label for="roleNew-input">${newStatus}</label>
    <select id="roleNew-input" name="newStatus">
        <option>${activeStatus}</option>
        <option>${deleteStatus}</option>
    </select>
    <button type="submit" class="button">${button}</button>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>