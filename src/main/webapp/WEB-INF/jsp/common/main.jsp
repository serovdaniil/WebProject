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
<br>
<a href="/controller?command=show_delete_application">delete application</a>
<br>
<br>
<a href="/controller?command=show_create_application">create application</a>
<br>
<br>
<a href="/controller?command=show_find_by_status_result_application">Find application by status result</a>
<br>

<a href="/controller?command=show_find_conferences_by_name">find Conferences by name</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/controller?command=show_update_description_in_conferenc">update description
    in Conferenc by ID</a>
<br>
<br>
<a href="/controller?command=show_find_conferenc_by_id">find Conferences by ID</a>
<br>
<c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
    <a href="${pageContext.request.contextPath}/controller?command=show_read_user_by_id">Read user by id</a>
    <br>
</c:if>
<br>
<h3>SectionConferenc</h3>
<a href="/controller?command=show_section_conferences">section Conferenc</a>
<br>
<br>
<a href="/controller?command=show_find_section_conferenc_by_id">find SectionConferences by ID</a>
<br>
<br>
<a href="/controller?command=show_remove_section_conferenc_by_id">remove SectionConferences by ID</a>
<br>
<br>
<a href="/controller?command=show_update_description_in_section_conferenc">update description in SectionConferenc by
    ID</a>
<br>
<br>
<a href="/controller?command=show_find_section_conferences_by_name">find SectionConferences by name</a>
<br>
<br>
<a href="/controller?command=show_find_section_conferences_in_conferenc_by_id">find SectionConferences in conferenc</a>
<br>
<br>
<h3>Category</h3>
<a href="/controller?command=show_create_category">add category</a>
<br>
<br>
<a href="/controller?command=show_change_name_category">change name category</a>
<br>
<br>
<a href="/controller?command=show_all_conferences_in_category">conferences in category</a>
<br>
<br>
<a href="/controller?command=show_all_section_conferences_in_category">section conferences in category</a>
<br>
<br>
<a href="/controller?command=show_id_category">id category</a>
<br>
<br>
<a href="/controller?command=show_remove_category_by_id">remove category</a>
<br>
</body>
</html>
