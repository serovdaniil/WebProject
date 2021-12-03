<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Show all conferences</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/styleForObject.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2> Заявка от пользователя ${requestScope.application.user.firstName} ${requestScope.application.user.lastName}</h2>
<form name="question-form" action="/controller?command=update_status_result_application" method="post">
    <label for="id-input">Уникальный номер:</label>
    <input id="id-input" class="container" type="text" name="id" readonly
           value="${requestScope.application.id}"/>
    <label for="nameUser-input">Имя пользователя:</label>
    <input id="nameUser-input" class="container" type="text" name="name_user" readonly
           value="${requestScope.application.user.firstName} ${requestScope.application.user.lastName}"/>
    <label for="sectionConferenc-input">Секция конфереции:</label>
    <input id="sectionConferenc-input" class="container" type="text" name="section_confernec" readonly
           value="${requestScope.application.sectionConferenc.name}"/>
    <label for="conferenc-input">Конференция:</label>
    <input id="conferenc-input" class="container" type="text" name="confernec" readonly
           value="${requestScope.application.sectionConferenc.conferenc.name}"/>
    <label for="result-input">Статус заявки:</label>
    <input id="result-input" class="container" type="text" name="result" readonly
           value="${requestScope.application.result.result}"/>
    <label for="resultNew-input">Выберите статус результата заявки:</label>
    <select id="resultNew-input" name="resultNew">
        <option>Open</option>
        <option>Waiting</option>
        <option>Completed</option>
    </select>
    <button type="submit" class="button">Обновить статус</button>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>