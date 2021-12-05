<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Applications by account</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>Ваши заявки на обучение</h2>
<p>Вы можете ознакомиться с Вашими заявками, а также их статусом.</p>
<table>
    <tr>
        <th>ID</th>
        <th>SECTION CONFERENC ID</th>
        <th>SECTION CONFERENC NAME</th>
        <th>RESULT</th>
        <th></th>
    </tr>
    <c:forEach var="application" items="${requestScope.applications}">
        <form name="application-form" action="/controller?command=remove_application" method="post">
            <tr>
                <td>
                    <input class="container" type="text" name="id" readonly value="${application.id}"/>
                </td>
                <td>
                    <input class="container" type="text" name="name" readonly
                           value="${application.sectionConferenc.name}"/>
                </td>
                <td>
                    <input class="container" type="text" name="sectionConferec" readonly
                           value="${application.sectionConferenc.conferenc.name}"/>
                </td>
                <td>
                    <input class="container" type="text" name="result" readonly
                           value="${application.result.result}"/>
                </td>
                <td>
                    <button type="submit" class="button">Удалить заявку</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
