<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.applicationsForUser" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.applicationByAccount.textMain" var="textMain"/>
<fmt:message bundle="${loc}" key="label.applicationByAccount.text" var="text"/>
<fmt:message bundle="${loc}" key="label.applicationByAccount.boxId" var="id"/>
<fmt:message bundle="${loc}" key="label.applicationByAccount.boxNameConferenc" var="nameConferenc"/>
<fmt:message bundle="${loc}" key="label.applicationByAccount.boxNameSection" var="nameSection"/>
<fmt:message bundle="${loc}" key="label.applicationByAccount.boxResult" var="result"/>
<fmt:message bundle="${loc}" key="label.applicationByAccount.button.remove" var="remove"/>
<fmt:message bundle="${loc}" key="label.message.duplicateApplication" var="message"/>
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
<p>${text}</p>
<c:if test="${not empty requestScope.errorDuplicatePassMessage}">
    <p><b>${message}</b></p>
    <br>
</c:if>
<table>
    <tr>
        <th>${id}</th>
        <th>${nameSection}</th>
        <th>${nameConferenc}</th>
        <th>${result}</th>
        <th></th>
    </tr>
    <c:forEach var="application" items="${requestScope.applications}">
        <form name="application-form" action="${pageContext.request.contextPath}/controller?command=remove_application" method="post">
            <tr>
                <td>
                    <input class="container" type="text" name="id" readonly value="${application.id}"/>
                </td>
                <td>
                    <input class="container" type="text" name="name" readonly
                           value="${application.sectionConferenc.name}"/>
                </td>
                <td>
                    <input class="container" type="text" name="sectionConferec" readonly
                           value="${application.sectionConferenc.conferenc.name}"/>
                </td>
                <td>
                    <input class="container" type="text" name="result" readonly
                           value="${application.result.result}"/>
                </td>
                <td>
                    <button type="submit" class="button">${remove}</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
