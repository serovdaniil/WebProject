<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.categories" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.categories.textMain" var="textMain"/>
<fmt:message bundle="${loc}" key="label.categories.boxId" var="id"/>
<fmt:message bundle="${loc}" key="label.categories.boxName" var="name"/>
<fmt:message bundle="${loc}" key="label.categories.more" var="more"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/dataListStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${textMain}</h2>
<a href="/controller?command=show_all_section_conferences_in_category">Show section conferences in category</a>
<br>
<ul class="list3a">
    <c:forEach var="category" items="${requestScope.categories}">
        <form name="conferenc-form" action="${pageContext.request.contextPath}/controller?command=all_conferences_in_category"
              method="post">
            <li>
                <label for="categoryId-input">${id}</label>
                <input id="categoryId-input" class="container" type="text" name="id" readonly
                       value="${category.id}"/>
                <label for="categoryName-input">${name}</label>
                <input id="categoryName-input" class="container" type="text" name="id" readonly
                       value="${category.name}"/>
                <button type="submit" class="button">${more}</button>
            </li>
        </form>
    </c:forEach>
</ul>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>