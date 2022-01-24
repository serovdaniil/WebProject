<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.conferences" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.conferences.boxCategory" var="category"/>
<fmt:message bundle="${loc}" key="label.conferences.boxDescription" var="description"/>
<fmt:message bundle="${loc}" key="label.conferences.boxId" var="id"/>
<fmt:message bundle="${loc}" key="label.conferences.boxName" var="name"/>
<fmt:message bundle="${loc}" key="label.conferences.more" var="more"/>
<fmt:message bundle="${loc}" key="label.conferences.textMain" var="textMain"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/dataListStyle.css"%>
    <%@include file="/WEB-INF/css/pagination.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${textMain}</h2>
<ul class="list3a">
    <c:forEach var="conferenc" items="${requestScope.conferences}">
        <form name="conferenc-form"
              action="${pageContext.request.contextPath}/controller?command=show_section_conferences_in_conferenc_pagination_by_first_page&page=1"
              method="post">
            <li><label for="id-input">${id}</label>
                <input id="id-input" class="container" type="text" name="id" readonly
                       value="${conferenc.id}"/>
                <br>
                <label for="name-input">${name}</label>
                <input id="name-input" class="container" name="name" readonly value="${conferenc.name}"/>
                <br>
                <label for="category-input">${category}</label>
                <input id="category-input" class="container" type="text" name="categoryName" readonly
                       value="${conferenc.category.name}"/>
                <br>
                <label for="descriprion-input">${description}:</label>
                <textarea id="descriprion-input" class="containerArea" name="conferencName" readonly>
                        ${conferenc.description}"</textarea>
                <br>
                <button type="submit" class="button">${more}</button>
            </li>
        </form>
    </c:forEach>
</ul>
<div id="container">
    <ul class="pagination">
        <c:forEach var="pageNum" begin="1" end="${requestScope.maxPagesCount}">
            <li>
                <a href="${pageContext.request.contextPath}/controller?command=show_conferences_with_pagination&page=${pageNum}">${pageNum}</a>
            </li>
        </c:forEach>
    </ul>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
