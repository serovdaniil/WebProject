<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find conferences by name</title>
</head>
<body>
<h3>Find SectionConferenc by name</h3>

<form name="findConferencByName-form" action="/controller?command=find_section_conferences_by_name" method="post">
    <label for="login-input">Name:</label>
    <input id="login-input" type="text" name="name" value=""/>
    <input type="submit" value="Search"/>
</form>

<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>DESCRIPTION</th>
        <th>CONFERENC</th>
        <%--        <th>Owner</th>--%>
    </tr>
    <c:forEach var="conferenc" items="${requestScope.conferences}">
        <tr>
            <td>${conferenc.id}</td>
            <td>${conferenc.name}</td>

            <td>${conferenc.description}</td>
            <td>${conferenc.conferenc}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
