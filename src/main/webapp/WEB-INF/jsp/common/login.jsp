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
<fmt:message bundle="${loc}" key="label.removeText" var="removeText"/>
<html>
<head>
    <title>${loginLink}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/loginStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp"%>
<h3>${loginLink}:</h3>
<form name="login-form" action="/controller?command=login" method="post">
    <div class="container">
        <label for="login-input">${login}:</label>
        <input id="login-input" type="text" min="1" max="40" required pattern="^([A-Za-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" name="login" value=""/>
        <br>
        <label for="password-input">${password}:</label>
        <input id="password-input" type="password" required pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$" name="password" value=""/>
        <br/>
        <c:if test="${not empty requestScope.errorLoginPassMessage}">
            <b>${requestScope.errorLoginPassMessage}</b>
            <br>
        </c:if>
        <button type="submit">${loginLink}</button>
    </div>
    <div class="container" style="background-color:#ffffff">
        <button type="reset" class="cancelbtn">${removeText}</button>
        <%-- <span class="psw">Forgot <a href="#">password?</a></span>--%>
    </div>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>