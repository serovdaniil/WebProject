<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.sectionConferences" var="text"/>
<fmt:message bundle="${loc}" key="label.sectionConferencInConferenc.conferenc" var="conferenc"/>
<fmt:message bundle="${loc}" key="label.sectionConferencInConferenc.description" var="description"/>
<fmt:message bundle="${loc}" key="label.sectionConferencInConferenc.name" var="name"/>
<fmt:message bundle="${loc}" key="label.sectionConferencInConferenc.id" var="id"/>
<fmt:message bundle="${loc}" key="label.useful.text.status" var="status"/>
<fmt:message bundle="${loc}" key="label.useful.button.more" var="button"/>
<html>
<head>
    <title>${text}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${text}</h2>
<table>
    <tr>
        <th>${id}</th>
        <th>${name}</th>
        <th>${description}</th>
        <th>${conferenc}</th>
        <th>${status}</th>
        <th></th>
    </tr>
    <c:forEach var="sectionConferenc" items="${requestScope.sectionConferences}">
        <form name="sectionConferenc-form"
              action="${pageContext.request.contextPath}/controller?command=find_section_conferenc_by_id"
              method="post">
            <tr>
                <td><input id="sectionConferencId-input" class="container" type="text" name="id" readonly
                           value="${sectionConferenc.id}"/></td>
                <td><input id="secctionConferencName-input" class="container" type="text" name="name" readonly
                           value="${sectionConferenc.name}"/></td>
                <td><input id="sectionConferencDescription-input" class="container" type="text" name="desription"
                           readonly
                           value="${sectionConferenc.description}"/></td>
                <td><input id="conferencName-input" class="container" type="text" name="conferencName" readonly
                           value="${sectionConferenc.conferenc.name}"/></td>
                <td><input id="status-input" class="container" type="text" name="status" readonly
                           value="${sectionConferenc.status.status}"/></td>
                <td>
                    <button type="submit" class="button">${button}</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>