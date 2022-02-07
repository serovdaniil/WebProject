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
<fmt:message bundle="${loc}" key="label.message.login" var="message"/>
<fmt:message bundle="${loc}" key="label.message.exception.login" var="exceptionLogin"/>
<fmt:message bundle="${loc}" key="label.message.exception.password" var="exceptionPassword"/>
<fmt:message bundle="${loc}" key="label.recovery.title" var="buttonRecovery"/>
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
        <input id="login-input" type="text" min="1" max="45" required
               pattern="^([A-Za-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" name="login"
               value="" oninput="validateLogin(this)"/>
        <br>
        <label for="password-input">${boxPassword}:</label>
        <input id="password-input" type="password" min="2" max="15" required
               pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$"
               name="password" value="" oninput="validatePassword(this)"/>
        <br/>
        <c:if test="${not empty requestScope.errorLoginPassMessage}">
            <b>${message}</b>
            <br>
        </c:if>
        <button type="submit">${buttonLogin}</button>
    </div>
    <div class="container" style="background-color:#ffffff">
        <button type="reset" class="cancelbtn">${removeText}</button>
       <span class="psw"><a href="${pageContext.request.contextPath}/controller?command=show_recovery_password">
           ${buttonRecovery}</a></span>
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