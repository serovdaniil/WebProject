<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.users" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.users.tetxMain" var="textMain"/>
<fmt:message bundle="${loc}" key="label.users.boxEmail" var="email"/>
<fmt:message bundle="${loc}" key="label.users.boxFirstName" var="firstName"/>
<fmt:message bundle="${loc}" key="label.users.boxId" var="id"/>
<fmt:message bundle="${loc}" key="label.users.boxLastName" var="lastName"/>
<fmt:message bundle="${loc}" key="label.users.boxLogin" var="login"/>
<fmt:message bundle="${loc}" key="label.users.boxRole" var="role"/>
<fmt:message bundle="${loc}" key="label.users.button.more" var="more"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${textMain}</h2>
<table>
    <tr>
        <th>${id}</th>
        <th>${login}</th>
        <th>${email}</th>
        <th>${firstName}</th>
        <th>${lastName}</th>
        <th>${role}</th>
        <th></th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <form name="application-form" action="${pageContext.request.contextPath}/controller?command=read_user_by_id" method="post">
            <tr>
                <td>
                    <input class="container" type="text" name="id" readonly value="${user.id}"/>
                </td>
                <td>
                    <input class="container" type="text" name="login" readonly value="${user.login}"/>
                </td>
                <td>
                    <input class="container" type="text" name="email" readonly value="${user.email}"/>
                </td>
                <td>
                    <input class="container" type="text" name="firstName" readonly value="${user.firstName}"/>
                </td>
                <td>
                    <input class="container" type="text" name="lastName" readonly value="${user.lastName}"/>
                </td>
                <td>
                    <input class="container" type="text" name="lastName" readonly value="${user.role}"/>
                </td>
                <td>
                    <button type="submit" >${more}</button>
                </td>
            </tr>
        </form>
    </c:forEach>
</table>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>