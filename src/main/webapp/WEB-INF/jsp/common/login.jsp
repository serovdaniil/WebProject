<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.login" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.login.boxLogin" var="boxLogin"/>
<fmt:message bundle="${loc}" key="label.login.boxPassword" var="boxPassword"/>
<fmt:message bundle="${loc}" key="label.login.button.login" var="buttonLogin"/>
<fmt:message bundle="${loc}" key="label.login.textLogin" var="textLogin"/>
<fmt:message bundle="${loc}" key="label.login.removeText" var="removeText"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/loginStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h3>${textLogin}:</h3>
<form name="login-form" action="${pageContext.request.contextPath}/controller?command=login" method="post">
    <div class="container">
        <label for="login-input">${boxLogin}:</label>
        <input id="login-input" type="email" min="1" max="45" required
               pattern="^([A-Za-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" name="login"
               value=""/>
        <br>
        <label for="password-input">${boxPassword}:</label>
        <input id="password-input" type="password" min="2" max="15" required
               pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$"
               name="password" value=""/>
        <br/>
        <c:if test="${not empty requestScope.errorLoginPassMessage}">
            <b>${requestScope.errorLoginPassMessage}</b>
            <br>
        </c:if>
        <button type="submit">${buttonLogin}</button>
    </div>
    <div class="container" style="background-color:#ffffff">
        <button type="reset" class="cancelbtn">${removeText}</button>
        <%-- <span class="psw">Forgot <a href="#">password?</a></span>--%>
    </div>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>