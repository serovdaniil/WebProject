<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.message.exception.password" var="exceptionPassword"/>
<fmt:message bundle="${loc}" key="label.message.exception.noMatchCode" var="exceptionCode"/>
<fmt:message bundle="${loc}" key="label.message.exception.noUser" var="exceptionUser"/>
<fmt:message bundle="${loc}" key="label.personalInformational.boxEmail" var="email"/>
<fmt:message bundle="${loc}" key="label.personalInformational.boxPassword" var="password"/>
<fmt:message bundle="${loc}" key="label.registration.boxPasswordRepeat" var="passwordRepeat"/>
<fmt:message bundle="${loc}" key="label.recovery.title" var="title"/>
<fmt:message bundle="${loc}" key="label.recovery.text" var="text"/>
<fmt:message bundle="${loc}" key="label.recovery.boxCode" var="boxCode"/>
<fmt:message bundle="${loc}" key="label.recovery.buttonCode" var="buttonCode"/>
<fmt:message bundle="${loc}" key="label.recovery.buttonEmail" var="buttonEmail"/>
<fmt:message bundle="${loc}" key="label.recovery.buttonPassword" var="buttonPassword"/>
<fmt:message bundle="${loc}" key="label.message.exception.login" var="exceptionLogin"/>
<fmt:message bundle="${loc}" key="label.message.exception.password" var="exceptionPassword"/>
<html>
<html>
<head>
    <title>${title}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/loginStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h3>${text}</h3>
<c:if test="${empty sessionScope.code && empty requestScope.result eq true}">
    <form name="login-form" action="${pageContext.request.contextPath}/controller?command=recovery_password_first_stage"
          method="post">
        <div class="container">
            <label for="login-input">${email}</label>
            <input id="login-input" type="text" min="1" max="45" required
                   pattern="^([A-Za-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" name="email"
                   value="" oninput="validateLogin(this)"/>
            <c:if test="${not empty requestScope.errorFoundPassMessage}">
                <b>${exceptionUser}</b>
                <br>
            </c:if>
            <button type="submit">${buttonEmail}</button>
        </div>
    </form>
</c:if>
<c:if test="${not empty sessionScope.code}">
    <form name="recovery-form"
          action="${pageContext.request.contextPath}/controller?command=recovery_password_second_stage"
          method="post">
        <div class="container">
            <label for="email-input">${email}</label>
            <input id="email-input" type="text" min="1" max="45" required
                   pattern="^([A-Za-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" name="email"
                   value="${requestScope.email}" oninput="validateLogin(this)"/>
            <br>
            <label for="code-input">${boxCode}:</label>
            <input id="code-input" type="text" min="2" max="6" required
                   pattern="(?<!\d)[1-9]\d{5}(?!\d)"
                   name="code" value="" oninput="validateCode(this)"/>
            <br/>
            <c:if test="${not empty requestScope.errorCodePassMessage}">
                <b>${exceptionCode}</b>
                <br>
            </c:if>
            <button type="submit">${buttonCode}</button>
        </div>
    </form>
</c:if>
<c:if test="${requestScope.result eq true}">
    <form name="recovery-form" action="${pageContext.request.contextPath}/controller?command=recovery_password_three_stage"
          method="post">
        <div class="container">
            <label for="email-input">${email}</label>
            <input id="email-input" type="text" min="1" max="45" required
                   pattern="^([A-Za-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" name="email"
                   value="${requestScope.email}" oninput="validateLogin(this)"/>
            <br>
            <br>
            <label for="password-input">${password}</label>
            <input id="password-input" type="password" min="2" max="15" required
                   pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$" name="password"
                   value="" oninput="validatePassword(this)"/>
            <br/>
            <label for="passwordRepeat-input">${passwordRepeat}</label>
            <input id="passwordRepeat-input" type="password" min="2" max="15" required
                   pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$" name="passwordRepeat"
                   value="" oninput="validatePassword(this)"/>
            <c:if test="${not empty requestScope.errorPasswordsPassMessage}">
                <b>${exceptionPassword}</b>
                <br>
            </c:if>
            <button type="submit">${buttonPassword}</button>
        </div>
    </form>
</c:if>

<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
<script>
    var regexLogin = /^([A-Za-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/;
    var regexCode = /(?<!\d)[1-9]\d{5}(?!\d)/;
    var regexPassword = /(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$/;

    function validateLogin(input) {
        if (!regexLogin.test(input.value)) {
            input.setCustomValidity("${exceptionLogin}");
        } else {
            input.setCustomValidity("");
        }
    }

    function validatePassword(input) {
        if (!regexPassword.test(input.value)) {
            input.setCustomValidity("${exceptionPassword}");
        } else {
            input.setCustomValidity("");
        }
    }

    function validateCode(input) {
        if (!regexCode.test(input.value)) {
            input.setCustomValidity("${exceptionLogin}");
        } else {
            input.setCustomValidity("");
        }
    }
</script>