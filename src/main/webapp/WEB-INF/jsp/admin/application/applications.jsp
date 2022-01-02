<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.applications" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.applications.textMain" var="textMain"/>
<fmt:message bundle="${loc}" key="label.applications.textAfterMain" var="textAfterMain"/>
<fmt:message bundle="${loc}" key="label.applications.text" var="text"/>
<fmt:message bundle="${loc}" key="label.applications.boxIdApplication" var="idApplication"/>
<fmt:message bundle="${loc}" key="label.applications.boxIdconferenc" var="conferenc"/>
<fmt:message bundle="${loc}" key="label.applications.boxIdSection" var="section"/>
<fmt:message bundle="${loc}" key="label.applications.boxIdStatusApplication" var="idStatusApplication"/>
<fmt:message bundle="${loc}" key="label.applications.boxIdUser" var="idUser"/>
<fmt:message bundle="${loc}" key="label.applications.button.more" var="buttonMore"/>
<fmt:message bundle="${loc}" key="label.applications.link.filterStatus" var="linkFilterStatus"/>
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
<p>${textAfterMain}</p>
<p>${text}</p>
<a class="create"
   href="${pageContext.request.contextPath}/controller?command=show_find_by_status_result_application">${linkFilterStatus}</a>
<table>
    <tr>
        <th>${idApplication}</th>
        <th>${idUser}</th>
        <th>${section}</th>
        <th>${conferenc}</th>
        <th>${idStatusApplication}</th>
        <th></th>
    </tr>
    <c:forEach var="application" items="${requestScope.applications}">
        <form name="application-form"
              action="${pageContext.request.contextPath}/controller?command=find_application_by_id" method="post">
            <tr>
                <td>
                    <input class="container" type="text" name="id" readonly
                           value="${application.id}"/>
                </td>
                <td>
                    <input class="container" type="text" name="idUser" readonly
                           value="${application.user.id}"/>
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
                    <input class="container" type="text" name="result" readonly
                           value="${application.result.result}"/>
                </td>
                <td>
                    <button type="submit" >${buttonMore}</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>