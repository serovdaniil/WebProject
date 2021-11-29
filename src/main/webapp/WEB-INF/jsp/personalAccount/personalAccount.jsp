<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.jwd.finalProject.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.link.personalInformation" var="pesronalInformationLink"/>
<fmt:message bundle="${loc}" key="label.link.allQuestions" var="allQuestionsLink"/>
<fmt:message bundle="${loc}" key="label.link.myQuestions" var="myQuestionsLink"/>
<fmt:message bundle="${loc}" key="label.link.users" var="usersLink"/>
<fmt:message bundle="${loc}" key="label.link.allApplications" var="allApplicationsLink"/>
<fmt:message bundle="${loc}" key="label.link.myApplications" var="myApplicationsLink"/>
<fmt:message bundle="${loc}" key="label.link.personalAccount" var="personalAccountLink"/>
<html>
<head>
    <title>${personalAccountLink}</title>
</head>
<body>
<ul>
    <c:if test="${not empty sessionScope.user}">
        <li><a href="${pageContext.request.contextPath}/controller?command=show_personal_infomation">${pesronalInformationLink}</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.USER}">
        <li><a href="${pageContext.request.contextPath}/controller?command=show_applications_by_account">${myApplicationsLink}</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.USER}">
        <li><a href="${pageContext.request.contextPath}/controller?command=find_questions_by_id_account">${myQuestionsLink}</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <li><a href="${pageContext.request.contextPath}/controller?command=show_questions">${allQuestionsLink}</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <li><a href="${pageContext.request.contextPath}/controller?command=show_users">${usersLink}</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
        <li><a href="${pageContext.request.contextPath}/controller?command=show_applications">${allApplicationsLink}</a></li>
        </c:if>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role eq Role.ADMIN}">
    <li><a href="${pageContext.request.contextPath}/controller?command=show_read_user_by_id">Find user by id</a></li>
    </c:if>
</ul>
</body>
</html>
