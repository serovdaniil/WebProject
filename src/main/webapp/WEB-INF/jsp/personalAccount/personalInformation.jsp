<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jwds" uri="jwd.epam.com" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.informationalAccount" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.personalInformational.textMain" var="textMain"/>
<fmt:message bundle="${loc}" key="label.personalInformational.text" var="text"/>
<fmt:message bundle="${loc}" key="label.personalInformational.rules" var="rules"/>
<fmt:message bundle="${loc}" key="label.personalInformational.rule.one" var="ruleOne"/>
<fmt:message bundle="${loc}" key="label.personalInformational.rule.two" var="ruleTwo"/>
<fmt:message bundle="${loc}" key="label.personalInformational.rule.three" var="ruleThree"/>
<fmt:message bundle="${loc}" key="label.personalInformational.boxEmail" var="email"/>
<fmt:message bundle="${loc}" key="label.personalInformational.boxFirstName" var="firstName"/>
<fmt:message bundle="${loc}" key="label.personalInformational.boxLastName" var="lastName"/>
<fmt:message bundle="${loc}" key="label.personalInformational.boxPassword" var="password"/>
<fmt:message bundle="${loc}" key="label.personalInformational.button.email" var="buttonEmail"/>
<fmt:message bundle="${loc}" key="label.personalInformational.button.password" var="buttonPassword"/>
<fmt:message bundle="${loc}" key="label.personalInformational.button.firstName" var="buttonFirstName"/>
<fmt:message bundle="${loc}" key="label.personalInformational.button.lastName" var="buttonLastName"/>
<fmt:message bundle="${loc}" key="label.registration.boxPasswordRepeat" var="passwordRepeat"/>
<fmt:message bundle="${loc}" key="label.message.passwords" var="messagePasswords"/>
<fmt:message bundle="${loc}" key="label.message.exception.login" var="exceptionLogin"/>
<fmt:message bundle="${loc}" key="label.message.exception.password" var="exceptionPassword"/>
<fmt:message bundle="${loc}" key="label.message.exception.common" var="exceptionCommon"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/personalInformationStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${textMain}</h2>
<p>${text}</p>
<p>${rules}</p>
<div id="description">
    <ul class="text">
        <li>${ruleOne}</li>
        <li>${ruleTwo}</li>
        <li>${ruleThree}</li>
    </ul>
</div>
<div id="main">
    <form name="updateFirstName-form"
          action="${pageContext.request.contextPath}/controller?command=update_first_name_by_user" method="post">
        <label for="firstName-input">${firstName}:</label>
        <input id="firstName-input" type="text" name="firstName" min="1" max="45" required
               pattern="(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,22}$)|(^[??-??][??-??]{0,22}(-[??-??])*[??-??]{0,22}$)|(^[A-Z][a-z]{0,45}$)|(^[??-??][??-??]{0,45}$)"
               value="${sessionScope.user.firstName}" oninput="validateName(this)"/>
        <c:if test="${not empty requestScope.result}">
            <b>${requestScope.result}</b>
            <br>
        </c:if>
        <button type="submit" class="cancelbtn">${buttonFirstName}</button>
    </form>
    <br>
    <form name="updateLastName-form"
          action="${pageContext.request.contextPath}/controller?command=update_last_name_by_user" method="post">
        <label for="lastName-input">${lastName}:</label>
        <input id="lastName-input" type="text" name="lastName" min="1" max="45" required
               pattern="(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,22}$)|(^[??-??][??-??]{0,22}(-[??-??])*[??-??]{0,22}$)|(^[A-Z][a-z]{0,45}$)|(^[??-??][??-??]{0,45}$)"
               value="${sessionScope.user.lastName}" oninput="validateName(this)"/>
        <c:if test="${not empty requestScope.result}">
            <b>${requestScope.result}</b>
            <br>
        </c:if>
        <button type="submit" class="cancelbtn">${buttonLastName}</button>
    </form>
    <br>
    <form name="updatePassword-form"
          action="${pageContext.request.contextPath}/controller?command=update_password_by_user" method="post">
        <label for="password-input">${password}:</label>
        <input id="password-input" type="password" min="2" max="15" name="password" required
               pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$" placeholder="${password}"
               oninput="validatePassword(this)"/>
        <label for="passwordRepeat-input">${passwordRepeat}:</label>
        <input id="passwordRepeat-input" type="password" min="2" max="15" required
               pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$" name="passwordRepeat"
               placeholder="${password}" oninput="validatePassword(this)"/>
        <c:if test="${not empty requestScope.errorUpdatePassMessage}">
            <b>${messagePasswords}</b>
            <br>
        </c:if>
        <br>
        <button type="submit" class="cancelbtn">${buttonPassword}</button>
    </form>
    <br>
    <form name="updateEmail-form"
          action="${pageContext.request.contextPath}/controller?command=update_email_by_user" method="post">
        <label for="email-input">${email}:</label>
        <input id="email-input" type="text" name="email" min="2" max="45"
               required pattern="^([A-Za-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$"
               value="${sessionScope.user.email}" oninput="validateEmail(this)"/>
        <c:if test="${not empty requestScope.result}">
            <b>${requestScope.result}</b>
            <br>
        </c:if>
        <button type="submit" class="cancelbtn">${buttonEmail}</button>
    </form>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
<script>
    var regexPassword = /(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$/;
    var regexLogin = /^([A-Za-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/;
    var regexName = /(^[A-Z][a-z]{0,35}(-[A-Z])*[a-z]{0,22}$)|(^[??-??][??-??]{0,22}(-[??-??])*[??-??]{0,22}$)|(^[A-Z][a-z]{0,45}$)|(^[??-??][??-??]{0,45}$)/;

    function validatePassword(input) {
        if (!regexPassword.test(input.value)) {
            input.setCustomValidity("${exceptionPassword}");
        } else {
            input.setCustomValidity("");
        }
    }

    function validateEmail(input) {
        if (!regexLogin.test(input.value)) {
            input.setCustomValidity("${exceptionLogin}");
        } else {
            input.setCustomValidity("");
        }
    }

    function validateName(input) {
        if (!regexName.test(input.value)) {
            input.setCustomValidity("${exceptionCommon}");
        } else {
            input.setCustomValidity("");
        }
    }
</script>