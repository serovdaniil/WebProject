<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.filterApplication" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.filterApplication.textMain" var="textMain"/>
<fmt:message bundle="${loc}" key="label.filterApplication.boxIdStatus" var="idStatus"/>
<fmt:message bundle="${loc}" key="label.filterApplication.boxConferenc" var="conferenc"/>
<fmt:message bundle="${loc}" key="label.filterApplication.boxIdApplication" var="idApplication"/>
<fmt:message bundle="${loc}" key="label.filterApplication.boxIdUser" var="idUser"/>
<fmt:message bundle="${loc}" key="label.filterApplication.boxSection" var="section"/>
<fmt:message bundle="${loc}" key="label.filterApplication.boxStatusApplication" var="statusApplication"/>
<fmt:message bundle="${loc}" key="label.filterApplication.button.searchStatus" var="buttonSearchStatus"/>
<fmt:message bundle="${loc}" key="label.filterApplication.button.more" var="buttonMore"/>
<fmt:message bundle="${loc}" key="label.filterApplication.status.open" var="open"/>
<fmt:message bundle="${loc}" key="label.filterApplication.status.completed" var="completed"/>
<fmt:message bundle="${loc}" key="label.filterApplication.status.remove" var="remove"/>
<fmt:message bundle="${loc}" key="label.filterApplication.status.waiting" var="waiting"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/styleForObject.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${textMain}</h2>
<form name="idCategory-form" action="${pageContext.request.contextPath}/controller?command=find_by_status_result_application" method="post">
    <label for="login-input">${idStatus}</label>
    <select id="login-input" name="resultNew">
        <option>${open}</option>
        <option>${waiting}</option>
        <option>${completed}</option>
        <option>${remove}</option>
    </select>
    <button type="submit">${buttonSearchStatus}</button>
</form>
<table>
    <tr>
        <th>${idApplication}</th>
        <th>${idUser}</th>
        <th>${section}</th>
        <th>${conferenc}</th>
        <th>${statusApplication}</th>
        <th></th>
    </tr>
    <c:forEach var="application" items="${requestScope.applications}">
        <form name="application-form" action="${pageContext.request.contextPath}/controller?command=find_application_by_id" method="post">
            <tr>
                <td>
                    <input class="container" type="text" name="id" readonly value="${application.id}"/>
                </td>
                <td>
                    <input class="container" type="text" name="idUser" readonly value="${application.user.id}"/>
                </td>
                <td>
                    <input class="container" type="text" name="sectionName" readonly
                           value="${application.sectionConferenc.name}"/>
                </td>
                <td>
                    <input class="container" type="text" name="sectionName" readonly
                           value="${application.sectionConferenc.conferenc.name}"/>
                </td>
                <td>
                    <input class="container" type="text" name="result" readonly value="${application.result.result}"/>
                </td>
                <td>
                    <button type="submit" >${buttonMore}</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
</br>
</body>
</html>
