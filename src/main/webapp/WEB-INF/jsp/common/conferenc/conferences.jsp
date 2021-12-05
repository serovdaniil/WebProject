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
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>НАЙТИ ТЕКСТ! Инфа о наших конференциях, которые мы проводим.</h2>
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
