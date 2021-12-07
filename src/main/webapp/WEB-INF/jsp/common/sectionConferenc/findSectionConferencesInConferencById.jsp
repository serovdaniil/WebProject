<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.sectionInConferenc" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.sectionConferencInConferenc.button.add" var="add"/>
<fmt:message bundle="${loc}" key="label.sectionConferencInConferenc.button.login" var="login"/>
<fmt:message bundle="${loc}" key="label.sectionConferencInConferenc.conferenc" var="conferenc"/>
<fmt:message bundle="${loc}" key="label.sectionConferencInConferenc.description" var="description"/>
<fmt:message bundle="${loc}" key="label.sectionConferencInConferenc.name" var="name"/>
<fmt:message bundle="${loc}" key="label.sectionConferencInConferenc.id" var="id"/>
<fmt:message bundle="${loc}" key="label.sectionConferencInConferenc.textMain" var="textMain"/>
<fmt:message bundle="${loc}" key="label.sectionConferencInConferenc.text" var="text"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>

<style>
    <%@include file="/WEB-INF/css/dataListStyle.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>${textMain}</h2>
<p>${text}</p>
<ul class="list3a">
    <c:forEach var="sectionConferenc" items="${requestScope.conferences}">
        <form name="conferenc-form" action="${pageContext.request.contextPath}/controller?command=create_application"
              method="post">
            <li><label for="id-input">${id
            }</label>
                <input id="id-input" class="container" type="text" name="id" readonly
                       value="${sectionConferenc.id}"/>
                <label for="name-input">${name}</label>
                <input id="name-input" class="container" type="text" name="nameSectionConferenc" readonly
                       value="${sectionConferenc.name}"/>
                <label for="conferenc-input">${conferenc}</label>
                <input id="conferenc-input" class="container" type="text" name="conferencName" readonly
                       value="${sectionConferenc.conferenc.name}"/>
                <br>
                <label for="descriprion-input">${description}</label>
                <input id="descriprion-input" class="container" type="text" name="descriptionConferenc" readonly
                       value="${sectionConferenc.description}"/>
                <br><br>
                <c:if test="${not empty sessionScope.user}">
                    <button type="submit" class="button">${add}</button>
                </c:if>
                <c:if test="${empty sessionScope.user }">
                    <a class="loginbutton" href="${pageContext.request.contextPath}/controller?command=show_login">${login}</a>
                </c:if>
            </
            >
        </form>
    </c:forEach>
</ul>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
