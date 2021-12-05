<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all categories</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/dataListStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
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
<ul class="list3a">
    <c:forEach var="category" items="${requestScope.categories}">
        <form name="conferenc-form" action="/controller?command=all_conferences_in_category"
              method="post">
            <li>
                <label for="categoryId-input">Уникальный номер:</label>
                <input id="categoryId-input" class="container" type="text" name="id" readonly
                       value="${category.id}"/>
                <label for="categoryName-input">Название:</label>
                <input id="categoryName-input" class="container" type="text" name="id" readonly
                       value="${category.name}"/>
                <button type="submit" class="button">Конференции в данной категории</button>
            </li>
        </form>
    </c:forEach>
</ul>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>