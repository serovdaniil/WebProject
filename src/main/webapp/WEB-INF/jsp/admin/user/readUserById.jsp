<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show user by id</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/styleForObject.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>Поиск пользователя по id</h2>
<p>Страница для детального просмотра пользователя по id.</p>

<h2> Пользователь, ${requestScope.user.firstName} ${requestScope.user.lastName}</h2>
<form name="question-form" action="${pageContext.request.contextPath}/controller?command=update_role_by_user" method="post">
    <label for="id-input">Уникальный номер:</label>
    <input id="id-input" class="container" type="text" name="id" readonly
           value="${requestScope.user.id}"/>
    <label for="firstName-input">Имя пользователя:</label>
    <input id="firstName-input" class="container" type="text" name="first_name" readonly
           value="${requestScope.user.firstName} ${requestScope.application.user.lastName}"/>
    <label for="lastName-input">Фамилия пользователя:</label>
    <input id="lastName-input" class="container" type="text" name="last_name" readonly
           value="${requestScope.user.lastName}"/>
    <label for="login-input">Логин:</label>
    <input id="login-input" class="container" type="text" name="login" readonly
           value="${requestScope.user.login}"/>
    <label for="email-input">Email:</label>
    <input id="email-input" class="container" type="text" name="email" readonly
           value="${requestScope.user.email}"/>
    <label for="password-input">Пароль:</label>
    <input id="password-input" class="container" type="password" name="password" readonly
           value="${requestScope.user.password}"/>
    <label for="role-input">Роль:</label>
    <input id="role-input" class="container" type="text" name="role" readonly
           value="${requestScope.user.role}"/>
    <label for="roleNew-input">Выберите новую роль:</label>
    <select id="roleNew-input" name="roleNew">
        <option>USER</option>
        <option>ADMIN</option>
    </select>
    <button type="submit" class="button">Обновить роль</button>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>