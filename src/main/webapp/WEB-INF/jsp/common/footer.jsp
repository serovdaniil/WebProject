<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<html>
<head>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/footerStyle.css"%>
</style>
<div id="footer">
    <p class="footer">ITFurute.com</p>
    <p class="footer">@the author Daniil Serov</p>
</div>
</body>
</html>