<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.email" var="email"/>
<fmt:message bundle="${loc}" key="label.password" var="password"/>
<fmt:message bundle="${loc}" key="label.link.registration" var="registrationLink"/>
<fmt:message bundle="${loc}" key="label.removeText" var="removeText"/>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>${registrationLink}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/loginStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp"%>
<h3>${registrationLink}:</h3>
<form name="login-form" action="/controller?command=create_an_account" method="post">
    <div class="container">
        <label for="login-input">${email}:</label>
        <input id="login-input" type="text"  name="email"  min="1" max="45" value="" required pattern="^([A-Za-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$"/>
        <br>
        <label for="password-input">${password}:</label>
        <input id="password-input" type="password" required pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$" name="password" value=""/>
        <br/>
        <c:if test="${not empty requestScope.errorRegistrationPassMessage}">
            <b>${requestScope.errorRegistrationPassMessage}</b>
            <br>
        </c:if>
        <button type="submit">${registrationLink}</button>
    </div>
    <div class="container" style="background-color:#f1f1f1">
        <button type="reset" class="cancelbtn">${removeText}</button>
        <%-- <span class="psw">Forgot <a href="#">password?</a></span>--%>
    </div>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>