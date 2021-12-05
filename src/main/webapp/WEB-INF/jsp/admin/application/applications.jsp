<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show all applications</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2> Заявки клиентов на обучение. </h2>
<p>На странице представлен список заявок на обучение. С каждоый подробнее Вы можете ознакомиться.</p>
<p>Также Вам доступен поиск по статусу заявки.</p>
<a class="create" href="/controller?command=show_find_by_status_result_application">Find application by status result</a>
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
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>