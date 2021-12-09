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
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${textMain}</h2>
<ul class="list3a">
    <c:forEach var="conferenc" items="${requestScope.conferences}">
        <form name="conferenc-form" action="${pageContext.request.contextPath}/controller?command=find_section_conferences_in_conferenc_by_id"
              method="post">
            <li><label for="id-input">${id}</label>
                <input id="id-input" class="container" type="text" name="id" readonly
                       value="${conferenc.id}"/>
                <label for="name-input">${name}</label>
                <input id="name-input" class="container" type="text" name="name" readonly value="${conferenc.name}"/>
                <label for="category-input">${category}</label>
                <input id="category-input" class="container" type="text" name="categoryName" readonly
                       value="${conferenc.category.name}"/>
                <br>
                <label for="descriprion-input">${description}:</label>
                <input id="descriprion-input" class="container" type="text" name="conferencName" readonly
                       value="${conferenc.description}"/>
                <br>
                <button type="submit" class="button">${more}</button>
            </li>
        </form>
    </c:forEach>
</ul>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
