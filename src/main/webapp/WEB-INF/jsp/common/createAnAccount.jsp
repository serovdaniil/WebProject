<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.registration" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.registration.boxLogin" var="login"/>
<fmt:message bundle="${loc}" key="label.registration.boxPassword" var="password"/>
<fmt:message bundle="${loc}" key="label.registration.button.registration" var="registration"/>
<fmt:message bundle="${loc}" key="label.registration.removeText" var="removeText"/>
<fmt:message bundle="${loc}" key="label.registration.boxPasswordRepeat" var="passwordRepeat"/>
<fmt:message bundle="${loc}" key="label.registration.create" var="createAccount"/>
<fmt:message bundle="${loc}" key="label.message.newUser" var="messageNewUser"/>
<fmt:message bundle="${loc}" key="label.message.passwords" var="messagePasswords"/>
<fmt:message bundle="${loc}" key="label.message.exception.login" var="exceptionLogin"/>
<fmt:message bundle="${loc}" key="label.message.exception.password" var="exceptionPassword"/>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/loginStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp"%>
<h3>${createAccount}:</h3>
<form name="login-form" action="${pageContext.request.contextPath}/controller?command=create_an_account" method="post">
    <div class="container">
        <label for="login-input">${login}:</label>
        <input id="login-input" type="text"  name="email"  min="1" max="45" value="" required
               pattern="^([A-Za-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$"
               oninput="validateLogin(this)"/>
        <br>
        <label for="password-input">${password}:</label>
        <input id="password-input" type="password" min="2" max="15" required
               pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$" name="password"
               value="" oninput="validatePassword(this)"/>
        <br/>
        <label for="passwordRepeat-input">${passwordRepeat}:</label>
        <input id="passwordRepeat-input" type="password" min="2" max="15" required
               pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$" name="passwordRepeat"
               value="" oninput="validatePassword(this)"/>
        <c:if test="${not empty requestScope.errorRegistrationPassMessage}">
            <b>${messageNewUser}</b>
            <br>
        </c:if>
        <c:if test="${not empty requestScope.errorPasswordsPassMessage}">
            <b>${messagePasswords}</b>
            <br>
        </c:if>
        <button type="submit">${registration}</button>
    </div>
    <div class="container" style="background-color:#f1f1f1">
        <button type="reset" class="cancelbtn">${removeText}</button>
        <%-- <span class="psw">Forgot <a href="#">password?</a></span>--%>
    </div>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
<script>
    var regexPassword = /(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$/;
    var regexLogin = /^([A-Za-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/;
    function validatePassword(input) {
        if (!regexPassword.test(input.value)) {
            input.setCustomValidity("${exceptionPassword}");
        }else{input.setCustomValidity("");}
    }
    function validateLogin(input) {
        if (!regexLogin.test(input.value)) {
            input.setCustomValidity("${exceptionLogin}");
        }else{input.setCustomValidity("");}
    }
</script>