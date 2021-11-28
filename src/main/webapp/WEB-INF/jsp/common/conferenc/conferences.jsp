<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show conferences</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>
<c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
    <h4>Remove conferenc</h4>
    <form name="removeConferenc-form" action="/controller?command=remove_conferenc_by_id" method="post">
        <label for="id-input">ID conferenc:</label>
        <input id="id-input" type="text" name="id" value=""/>
        <input type="submit" value="Remove"/>
    </form>
    <h4>Update description conferenc</h4>
    <form name="updateDescriptionByConferenc-form" action="/controller?command=update_description_in_conferenc"
          method="post">
        <label for="idUpdate-input">ID:</label>
        <input id="idUpdate-input" type="text" name="id" value=""/>
        <br>
        <label for="descriprion-input">Description:</label>
        <input id="descriprion-input" type="text" name="description" value=""/>
        <input type="submit" value="Update description"/>
        <br/>
    </form>
    <h4>Create conferenc</h4>
    <form name="createConferenc-form" action="/controller?command=create_conferenc" method="post">
        <label for="name-input">Name Conferenc:</label>
        <input id="name-input" type="text" name="name" value=""/>
        <br>
        <label for="description-input">Descriprion Conferenc:</label>
        <input id="description-input" type="text" name="description" value=""/>
        <br>
        <label for="idCategory-input">ID category:</label>
        <input id="idCategory-input" type="text" name="idCategory" value=""/>
        <input type="submit" value="Create conferenc"/>
        <br/>
    </form>
    <h4>Find conferenc by ID</h4>
    <form name="findConferencById-form" action="/controller?command=find_conferenc_by_id" method="post">
        <label for="idConferenc-input">ID conferenc:</label>
        <input id="idConferenc-input" type="text" name="id" value=""/>
        <input type="submit" value="Search"/>
    </form>
    <br>
    <h4>${requestScope.conferenc}</h4>
    <h4> Result: ${requestScope.result}</h4>
    </br>
</c:if>
<br>
<a href="/controller?command=show_find_section_conferences_in_conferenc_by_id">find SectionConferences in conferenc</a>
<br>
<br>
<a href="/controller?command=show_find_section_conferences_by_name">find SectionConferences by name</a>
<br>
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
            <td>${conferenc.category.name}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
