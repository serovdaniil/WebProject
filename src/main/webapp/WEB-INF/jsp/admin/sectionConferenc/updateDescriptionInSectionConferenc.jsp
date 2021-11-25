<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update descriprion in section conferenc</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
    <p>Hello, ${sessionScope.user.firstName}</p>
    <p>ID, ${sessionScope.user.id}</p>
</c:if>
<h3>Update descriprion in SectionConferenc</h3>

<form name="updateDescriptionByConferenc-form" action="/controller?command=update_description_in_section_conferenc" method="post">
    <label for="login-input">ID:</label>
    <input id="login-input" type="text" name="id" value=""/>
    <br>
    <label for="password-input">Description:</label>
    <input id="password-input" type="text" name="description" value=""/>
    <input type="submit" value="Update description"/>
    <br/>
</form>
<br>
<h1> ${requestScope.result}</h1>
</br>
</body>
</html>