<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Section conferences in</title>
</head>
<body>

<style>
    <%@include file="/WEB-INF/css/dataListStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>add information if cinferenc</h2>
<h2>Find SectionConferenc by name</h2>

<form name="findConferencByName-form" action="/controller?command=find_section_conferences_in_conferenc_by_id"
      method="post">
    <label for="login-input">Name:</label>
    <input id="login-input" type="text" name="id" value=""/>
    <input type="submit" value="Search"/>
</form>
<ul class="list3a">
    <c:forEach var="sectionConferenc" items="${requestScope.conferences}">
        <form name="conferenc-form" action="/controller?command=create_application"
              method="post">
            <li><label for="id-input">Уникальный номер:</label>
                <input id="id-input" class="container" type="text" name="id" readonly
                       value="${sectionConferenc.id}"/>
                <label for="name-input">Название:</label>
                <input id="name-input" class="container" type="text" name="nameSectionConferenc" readonly
                       value="${sectionConferenc.name}"/>
                <label for="conferenc-input">В составе конференции:</label>
                <input id="conferenc-input" class="container" type="text" name="conferencName" readonly
                       value="${sectionConferenc.conferenc.name}"/>
                <br>
                <label for="descriprion-input">Описание:</label>
                <input id="descriprion-input" class="container" type="text" name="descriptionConferenc" readonly
                       value="${sectionConferenc.description}"/>
                <br><br>
                <c:if test="${not empty sessionScope.user}">
                    <button type="submit" class="button">Записаться</button>
                </c:if>
                <c:if test="${empty sessionScope.user }">
                    <a class="loginbutton" href="/controller?command=show_login">${loginLink}</a>
                </c:if>
            </
            >
        </form>
    </c:forEach>
</ul>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
