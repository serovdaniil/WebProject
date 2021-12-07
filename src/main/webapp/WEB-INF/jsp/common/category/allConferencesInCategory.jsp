<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.conferencesInCategory" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.textMain" var="textMain"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.boxId" var="id"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.boxName" var="name"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.boxDescription" var="description"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.boxCategory" var="category"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.button.more" var="more"/>
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
        <th>${name}</th>
        <th>${description}</th>
        <th>${category}</th>
    </tr>
    <c:forEach var="conferenc" items="${requestScope.conferences}">
        <tr>
            <td>${conferenc.id}</td>
            <td>${conferenc.name}</td>
            <td>${conferenc.description}</td>
            <td>${conferenc.category.name}</td>
        </tr>
    </c:forEach>
</table>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
