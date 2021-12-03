<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show conferences</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/dataListStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h3>НАЙТИ ТЕКСТ! Инфа о наших конференциях, которые мы проводим.</h3>
<c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
    <h3>Admin panel for administrator</h3>
    <br>
    <h4>Remove conferenc</h4>
    <form name="removeConferenc-form" action="/controller?command=remove_conferenc_by_id" method="post">
        <label for="idForm-input">ID conferenc:</label>
        <input id="idForm-input" type="text" name="id" value=""/>
        <input type="submit" value="Remove"/>
    </form>
    <h4>Update description conferenc</h4>
    <form name="updateDescriptionByConferenc-form" action="/controller?command=update_description_in_conferenc"
          method="post">
        <label for="idUpdate-input">ID:</label>
        <input id="idUpdate-input" type="text" name="id" value=""/>
        <br>
        <label for="descriprionConferenc-input">Description:</label>
        <input id="descriprionConferenc-input" type="text" name="description" value=""/>
        <input type="submit" value="Update description"/>
        <br/>
    </form>
    <h4>Create conferenc</h4>
    <form name="createConferenc-form" action="/controller?command=create_conferenc" method="post">
        <label for="nameConferenc-input">Name Conferenc:</label>
        <input id="nameConferenc-input" type="text" name="name" value=""/>
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
<a href="/controller?command=show_find_section_conferences_by_name">find SectionConferences by name</a>
<br>
<ul class="list3a">
    <c:forEach var="conferenc" items="${requestScope.conferences}">
        <form name="conferenc-form" action="/controller?command=find_section_conferences_in_conferenc_by_id"
              method="post">
            <li><label for="id-input">Уникальный номер:</label>
                <input id="id-input" class="container" type="text" name="id" readonly
                       value="${conferenc.id}"/>
                <label for="name-input">Название:</label>
                <input id="name-input" class="container" type="text" name="name" readonly value="${conferenc.name}"/>
                <label for="category-input">Категория:</label>
                <input id="category-input" class="container" type="text" name="categoryName" readonly
                       value="${conferenc.category.name}"/>
                <br>
                <label for="descriprion-input">Описание:</label>
                <input id="descriprion-input" class="container" type="text" name="conferencName" readonly
                       value="${conferenc.description}"/>
                <br>
                <button type="submit" class="button">Подробнее о конференции</button>
                    <%-- <span class="psw">Forgot <a href="#">password?</a></span>--%>
            </li>
        </form>
    </c:forEach>
</ul>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
