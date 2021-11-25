<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update descriprion in conferenc</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
    <p>ID, ${sessionScope.user.id}</p>
</c:if>
<h3>Find Conferenc by name</h3>
<form name="createConferenc-form" action="/controller?command=create_conferenc" method="post">
    <label for="name-input">Name Conferenc:</label>
    <input id="name-input" type="text" name="name" value=""/>
    <br>
    <label for="description-input">Descriprion Conferenc:</label>
    <input id="description-input" type="text" name="description" value=""/>
    <br>
    <label for="id-input">ID category:</label>
    <input id="id-input" type="text" name="idCategory" value=""/>

    <input type="submit" value="Create conferenc"/>
    <br/>
</form>
<br>
<h1> ${requestScope.result}</h1>
</br>
<a href="/controller?command=show_conferences">Conferences page</a>
<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>DESCRIPTION</th>
        <th>CATEGORY</th>
    </tr>
    <c:forEach var="conferenc" items="${requestScope.conferences}">
        <tr>
            <td>${conferenc.id}</td>
            <td>${conferenc.name}</td>

            <td>${conferenc.description}</td>
            <td>${conferenc.category}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
