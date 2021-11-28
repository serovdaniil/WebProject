<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all categories</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
    <h4>Change name category</h4>
    <form name="changeNameCategory-form" action="/controller?command=change_name_category" method="post">
        <label for="id-input">ID Category:</label>
        <input id="id-input" type="text" name="id" value=""/>
        <label for="name-input">Change name Category:</label>
        <input id="name-input" type="text" name="name" value=""/>
        <input type="submit" value="Change name category"/>
        <br/>
    </form>
    <h4>Create category</h4>
    <form name="createConferenc-form" action="/controller?command=create_category" method="post">
        <label for="nameCategory-input">Name Category:</label>
        <input id="nameCategory-input" type="text" name="name" value=""/>
        <input type="submit" value="Create category"/>
        <br/>
    </form>
    <h4>Remove category by id</h4>
    <form name="removeCategory-form" action="/controller?command=remove_category_by_id" method="post">
        <label for="idCategory-input">ID category:</label>
        <input id="idCategory-input" type="text" name="id" value=""/>
        <input type="submit" value="Remove category"/>
    </form>
    <h4>Find category by id</h4>
    <form name="idCategory-form" action="/controller?command=id_category" method="post">
        <label for="id_category-input">ID category:</label>
        <input id="id_category-input" type="text" name="id" value=""/>
        <input type="submit" value="Search"/>
    </form>
    <h4>${requestScope.category}</h4>
    <h4> Result: ${requestScope.result}</h4>
    </br>
</c:if>
<br>
<a href="/controller?command=show_all_conferences_in_category">Show conferences in category</a>
<br>
<a href="/controller?command=show_all_section_conferences_in_category">Show section conferences in category</a>
<br>

<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
    </tr>
    <c:forEach var="category" items="${requestScope.categories}">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>