<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MAIN</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/welcomebackground.css"%>
    <%@include file="/WEB-INF/css/loginStyle.css"%>
</style>
<ul>
    <li><a href="${pageContext.request.contextPath}/controller?command=">Home</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_conferences">Conferenc</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_section_conferences">Section conferenc</a>
    </li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_categories">Category</a></li>
    <li><a href="${pageContext.request.contextPath}/controller?command=show_contact">Сontacts</a></li>
    <li><c:choose>
        <c:when test="${not empty sessionScope.user}">
            <a href="/controller?command=logout">logout</a>
        </c:when>
        <c:otherwise>
            <a href="/controller?command=show_login">login</a>
        </c:otherwise>
    </c:choose></li>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.UNAUTHORIZED}">
        <li><a href="/controller?command=show_create_an_account">registration</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.user}">
        <li><a href="/controller?command=show_personal_account">Personal account</a></li>
    </c:if>
</ul>
<br>
<h1>World Hello</h1>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
    <p>${sessionScope.user.role}</p>
</c:if>

<a href="/controller?command=show_find_conferences_by_name">find Conferences by name</a>
<br>
<br>
<a href="/controller?command=show_all_conferences_in_category">conferences in category</a>
<br>
<a href="/controller?command=show_all_section_conferences_in_category">section conferences in category</a>
<br>
</body>
</html>
