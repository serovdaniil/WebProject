<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal account</title>
</head>
<body>
<ul>
    <c:if test="${not empty sessionScope.user}">
        <li><a href="${pageContext.request.contextPath}/controller?command=show_personal_infomation">Personal information</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.USER}">
        <li><a href="${pageContext.request.contextPath}/controller?command=show_applications_by_account">My applications</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.USER}">
        <li><a href="${pageContext.request.contextPath}/controller?command=find_questions_by_id_account">My questions</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <li><a href="${pageContext.request.contextPath}/controller?command=show_questions">All questions</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <li><a href="${pageContext.request.contextPath}/controller?command=show_users">Show users</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <li><a href="${pageContext.request.contextPath}/controller?command=show_applications">Show application</a></li>
        </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
    <li><a href="${pageContext.request.contextPath}/controller?command=show_read_user_by_id">Find user by id</a></li>
    </c:if>
</ul>
</body>
</html>
