<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.loginAccount" var="login"/>
<fmt:message bundle="${loc}" key="label.loginPlease" var="loginPlease"/>
<fmt:message bundle="${loc}" key="label.password" var="password"/>
<fmt:message bundle="${loc}" key="label.email" var="email"/>
<fmt:message bundle="${loc}" key="label.link.login" var="loginLink"/>
<html>
<head>
    <title>${loginLink}</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h3>${loginPlease}:</h3>
<form name="login-form" action="/controller?command=login" method="post">
    <label for="login-input">${login}:</label>
    <input id="login-input" type="text" name="login" value=""/>
    <br>
    <label for="password-input">${password}:</label>
    <input id="password-input" type="password" name="password" value=""/>
    <br/>
    <c:if test="${not empty requestScope.errorLoginPassMessage}">
        <b>${requestScope.errorLoginPassMessage}</b>
        <br>
    </c:if>
    <style>
        <%@include file="/WEB-INF/css/loginStyle.css"%>
    </style>
    <div>
        <button  id="login" class="button blue">
            <i class="fa fa-unlock"></i>
            <span>${loginLink}</span>
        </button>
    </div>
    <%-- <input type="submit" value="Log in">--%>
</form>
</form>
</body>
</html>