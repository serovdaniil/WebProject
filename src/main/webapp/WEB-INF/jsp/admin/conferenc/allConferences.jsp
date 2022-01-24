<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.conferences" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.useful.text.status.main" var="textMain"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.boxId" var="id"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.boxName" var="name"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.boxDescription" var="description"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.boxCategory" var="category"/>
<fmt:message bundle="${loc}" key="label.conferencesInCategory.button.more" var="more"/>
<fmt:message bundle="${loc}" key="label.useful.text.status" var="status"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
    <%@include file="/WEB-INF/css/pagination.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${textMain}</h2>
<table>
    <tr>
        <th>${id}</th>
        <th>${name}</th>
        <th>${description}</th>
        <th>${category}</th>
        <th>${status}</th>
    </tr>
    <c:forEach var="conferenc" items="${requestScope.conferences}">
        <form name="conferenc-form" action="${pageContext.request.contextPath}/controller?command=find_conferenc_by_id"
              method="post">
            <tr>
                <td><input id="conferencId-input" class="container" type="text" name="id" readonly
                           value="${conferenc.id}"/></td>
                <td><input id="conferencName-input" class="container" type="text" name="name" readonly
                           value="${conferenc.name}"/></td>
                <td><input id="conferencDescription-input" class="container" type="text" name="desription" readonly
                           value="${conferenc.description}"/></td>
                <td><input id="conferencCategoryName-input" class="container" type="text" name="category" readonly
                           value="${conferenc.category.name}"/></td>
                <td><input id="conferencStatusName-input" class="container" type="text" name="status" readonly
                           value="${conferenc.status.status}"/></td>
                <td>
                    <button type="submit" class="button">${more}</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<div id="container">
    <ul class="pagination">
        <c:forEach var="pageNum" begin="1" end="${requestScope.maxPagesCount}">
            <li>
                <a href="${pageContext.request.contextPath}/controller?command=show_all_conferences&page=${pageNum}">
                        ${pageNum}</a>
            </li>
        </c:forEach>
    </ul>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
