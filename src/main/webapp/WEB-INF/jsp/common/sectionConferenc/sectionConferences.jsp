<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all SectionConferences</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
</c:if>

<c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
    <h4>Create section conferenc</h4>
    <form name="createSectionConferenc-form" action="/controller?command=create_section_conferenc" method="post">
        <label for="name-input">Name section conferenc:</label>
        <input id="name-input" type="text" name="name" value=""/>
        <br>
        <label for="description-input">Descriprion section sonferenc:</label>
        <input id="description-input" type="text" name="description" value=""/>
        <br>
        <label for="id-input">ID conferenc:</label>
        <input id="id-input" type="text" name="idConferenc" value=""/>

        <input type="submit" value="Create section conferenc"/>
        <br/>
    </form>
    </br>
    <h4>Remove section conferenc</h4>
    <form name="removeSectionConferenc-form" action="/controller?command=remove_section_conferenc_by_id" method="post">
        <label for="idRemove-input">ID section conferenc:</label>
        <input id="idRemove-input" type="text" name="id" value=""/>
        <input type="submit" value="Remove section conferenc"/>
    </form>
    <h4>Update description section conferenc</h4>
    <form name="updateDescriptionByConferenc-form" action="/controller?command=update_description_in_section_conferenc" method="post">
        <label for="idSection-input">ID:</label>
        <input id="idSection-input" type="text" name="id" value=""/>
        <br>
        <label for="descriptionConferenc-input">Description:</label>
        <input id="descriptionConferenc-input" type="text" name="description" value=""/>
        <input type="submit" value="Update description"/>
        <br/>
    </form>
    <h4>Find section conferenc by id</h4>
    <form name="findSectionConferencByName-form" action="/controller?command=find_section_conferenc_by_id" method="post">
        <label for="findId-input">ID sectionConferenc:</label>
        <input id="findId-input" type="text" name="id" value=""/>
        <input type="submit" value="Search"/>
    </form>
    <h4>${requestScope.sectionConferenc}</h4>
</c:if>


<h3>Create application</h3>
<form name="createApplication-form" action="/controller?command=create_application" method="post">
    <label for="login-input">ID application:</label>
    <input id="login-input" type="text" name="id" value=""/>
    <input type="submit" value="Create"/>
</form>
<h3> ${requestScope.result}</h3>
<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>DESCRIPTION</th>
        <th>CONFERENC</th>
    </tr>
    <c:forEach var="conferenc" items="${requestScope.sectionConferences}">
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