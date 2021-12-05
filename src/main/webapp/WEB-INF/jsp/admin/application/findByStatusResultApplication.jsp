<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find applications by id status result</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/styleForObject.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2> Фильтр заявок по ее статусу.</h2>
<form name="idCategory-form" action="/controller?command=find_by_status_result_application" method="post">
    <label for="login-input">ID status application:</label>
    <select id="login-input" name="resultNew">
        <option>Open</option>
        <option>Waiting</option>
        <option>Completed</option>
    </select>
    <button type="submit">Search</button>
</form>
<table>
    <tr>
        <th>Уникальный номер</th>
        <th>ID пользователя</th>
        <th>Название секции</th>
        <th>Название конференции</th>
        <th>Статус заявки</th>
        <th></th>
    </tr>
    <c:forEach var="application" items="${requestScope.applications}">
        <form name="application-form" action="/controller?command=find_application_by_id" method="post">
            <tr>
                <td>
                    <input class="container" type="text" name="id" readonly value="${application.id}"/>
                </td>
                <td>
                    <input class="container" type="text" name="idUser" readonly value="${application.user.id}"/>
                </td>
                <td>
                    <input class="container" type="text" name="sectionName" readonly
                           value="${application.sectionConferenc.name}"/>
                </td>
                <td>
                    <input class="container" type="text" name="sectionName" readonly
                           value="${application.sectionConferenc.conferenc.name}"/>
                </td>
                <td>
                    <input class="container" type="text" name="result" readonly value="${application.result.result}"/>
                </td>
                <td>
                    <button type="submit" >Подробнее</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
</br>
</body>
</html>
