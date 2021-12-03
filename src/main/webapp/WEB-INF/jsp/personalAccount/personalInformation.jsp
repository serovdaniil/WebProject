<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.link.personalInformation" var="pesronalInformationLink"/>
<fmt:message bundle="${loc}" key="label.result" var="result"/>
<fmt:message bundle="${loc}" key="label.update" var="update"/>
<fmt:message bundle="${loc}" key="label.firstName" var="firstName"/>
<fmt:message bundle="${loc}" key="label.lastName" var="lastName"/>
<fmt:message bundle="${loc}" key="label.email" var="email"/>
<fmt:message bundle="${loc}" key="label.password" var="password"/>
<fmt:message bundle="${loc}" key="label.updateLastName" var="updateLastName"/>
<fmt:message bundle="${loc}" key="label.updateEmail" var="updateEmail"/>
<fmt:message bundle="${loc}" key="label.updatePassword" var="updatePassword"/>
<html>
<head>
    <title>${pesronalInformationLink}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/personalInformationStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${pesronalInformationLink}</h2>
<p>Ниже Вы можете изменять основную информацию своего аккаунта</p>
<p>Вам не обходимо следовать основным правилам, для изменения личных данных: </p>
<div id="description">
    <ul class="text">
        <li>Имя и фамилия должны состоять только из букв;</li>
        <li>Пароль может состоять из одной заглвной буквы и строчных букв, а также цифр;</li>
        <li>Email в формате Email</li>
    </ul>
</div>
<div id="main">
    <form name="updateFirstName-form"
          action="/controller?command=update_first_name_by_user" method="post">
        <label for="firstName-input">${firstName}:</label>
        <input id="firstName-input" type="text" name="firstName" min="1" max="45" required
               pattern="^[A-ZА-Яa-zа-я]+((\s)?((\'|\-|\.)?([A-ZА-Яa-zа-я])+))*$"
               value="${sessionScope.user.firstName}"/>
        <button type="submit" class="cancelbtn">${firstName}></button>
    </form>
    <br>
    <form name="updateLastName-form"
          action="${pageContext.request.contextPath}/controller?command=update_last_name_by_user" method="post">
        <label for="lastName-input">${lastName}:</label>
        <input id="lastName-input" type="text" name="lastName" min="1" max="45" required
               pattern="^[A-ZА-Яa-zа-я]+((\s)?((\'|\-|\.)?([A-ZА-Яa-zа-я])+))*$" value="${sessionScope.user.lastName}"/>
        <button type="submit" class="cancelbtn">${updateLastName}</button>
    </form>
    <br>
    <form name="updatePassword-form"
          action="${pageContext.request.contextPath}/controller?command=update_password_by_user" method="post">
        <label for="password-input">${password}:</label>
        <input id="password-input" type="password" name="password" required
               pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{8,15})$" placeholder="${password}"/>
        <button type="submit" class="cancelbtn">${updatePassword}</button>
    </form>
    <br>
    <form name="updateEmail-form"
          action="${pageContext.request.contextPath}/controller?command=update_email_by_user" method="post">
        <label for="email-input">${email}:</label>
        <input id="email-input" type="text" name="email"
               requiredpattern="^([A-Za-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$"
               value="${sessionScope.user.email}"/>
        <button type="submit" class="cancelbtn">${updateEmail}</button>
    </form>
</div>
<br>
<h3> ${result}: ${requestScope.user}</h3>
</br>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>