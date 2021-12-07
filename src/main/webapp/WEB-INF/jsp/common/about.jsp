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
<fmt:message bundle="${loc}" key="label.registration.create" var="createAccount"/>
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

<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>