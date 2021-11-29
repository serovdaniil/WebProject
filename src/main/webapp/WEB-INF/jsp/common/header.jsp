<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/headerStyle.css"%>
</style>
<ul>
    <li><a href="/controller?command=">Home</a></li>
    <li><a href="/controller?command=show_conferences">Conferenc</a></li>
    <li><a href="/controller?command=show_categories">Category</a></li>
    <li><a href="#">Сontacts</a></li>
    <li><c:choose>
        <c:when test="${not empty sessionScope.user}">
            <a href="/controller?command=logout">logout</a>
        </c:when>
        <c:otherwise>
            <a href="/controller?command=show_login">login</a>
        </c:otherwise>
    </c:choose></li>
    <li><c:if test="${not empty sessionScope.user}">
        <a href="${pageContext.request.contextPath}/controller?command=show_personal_account">Personal Account</a>
    </c:if></li>
</ul>
</body>
</html>
