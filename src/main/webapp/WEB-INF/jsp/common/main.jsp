<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title" var="pageTitle"/>
<html>
<head>
    <title>IT-школа IT-Future</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/text.css"%>
    <%@include file="/WEB-INF/css/main.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<div id="main_block">
    <p><img class="bg-fon" src="/jpg/IT.jpg" alt="img"/></p>
    <h1 class="main">IT-Future - школа будущего, IT курсы онлайн!</h1>
</div>
<div id="main_advantag">
    <h2 class="shadow">Этапы обучения</h2>
    <p><img class="image" src="/jpg/man_with_laptop.jpg" alt="img"/></p>
    <lu>
        <li class="bolt">Теория + практика
            <p class="left">Занятия на 80 % состоят из практики, поэтому Вы точно получите необходимые знания и
                умения!</p>
        </li>
        <li class="bolt">Проект
            <p class="left">Вы пройдете путь от основ до профессионального уровня; упор в обучении будет сделан на
                практическую часть!</p>
        </li>
        <li class="bolt">Собеседование
            <p class="left"> Разбор вопросов с собеседований, рекомендации и советы!</p>
        </li>
        <li class="bolt">Помощь в трудоустройстве
            <p class="left">Преподаватель продолжает курировать Вас при прохождении собеседований!</p>
        </li>
    </lu>
    <br>
    <a class="button"
       href="${pageContext.request.contextPath}/controller?command=show_conferences">Посмотреть конференции</a>
</div>
<div>
    <h2 class="shadow">Если у Вас остались впоросы,будем рады на них овтетить.</h2>
    <form name="createQuestion-form" action="/controller?command=create_question" method="post">
        <label for="name-input">Вопрос:</label>
        <input id="name-input" type="text" required min="1" max="245" name="name"
               pattern="^[A-ZА-Яa-zа-я]+((\s)?((\'|\-|\.|\,)?([A-ZА-Яa-zа-я])+))*$" value=""/>
        <button type="submit">Задать вопрос</button>
    </form>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>