<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all conferences in category</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>advasd</h2>
<p>qwerqwfasdf</p>
<form name="findConferencesInCategory-form" action="/controller?command=all_conferences_in_category" method="post">
    <label for="login-input">Name:</label>
    <input id="login-input" type="text" name="id" value=""/>
    <input type="submit" value="Search"/>
</form>
<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>Description</th>
        <th>CategoryName</th>
    </tr>
    <c:forEach var="conferenc" items="${requestScope.conferences}">
        <tr>
            <td>${conferenc.id}</td>
            <td>${conferenc.name}</td>
            <td>${conferenc.description}</td>
            <td>${conferenc.category.name}</td>
        </tr>
    </c:forEach>
</table>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
