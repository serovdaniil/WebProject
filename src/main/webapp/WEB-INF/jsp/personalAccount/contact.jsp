<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.contacs" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.contacts.title" var="title"/>
<fmt:message bundle="${loc}" key="label.contacts.boxQuestion" var="boxQuestion"/>
<fmt:message bundle="${loc}" key="label.contacts.button" var="button"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/loginStyle.css"%>
    <%@include file="/WEB-INF/css/dataListStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h3>${title}</h3>
<form name="createQuestion-form" action="${pageContext.request.contextPath}/controller?command=create_question" method="post">
    <label for="name-input">${boxQuestion}:</label>
    <input id="name-input" type="text" name="name" min="2" max="1000" pattern="^.{2,1000}$" value=""/>
    <br>
    <c:if test="${not empty sessionScope.user}">
    <button type="submit">${button}</button>
    </c:if>
    <c:if test="${empty sessionScope.user }">
        <a class="loginbutton"
           href="${pageContext.request.contextPath}/controller?command=show_login">${login}</a>
    </c:if>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>