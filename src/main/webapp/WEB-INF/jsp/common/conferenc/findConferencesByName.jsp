<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.conferencesByName" var="pageTitle"/>
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
<h3>Find Conferenc by name</h3>
<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>DESCRIPTION</th>
        <th>CATEGORY</th>
        <%--        <th>Owner</th>--%>
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
