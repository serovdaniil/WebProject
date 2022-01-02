<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.main" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.main.box.question" var="boxQuestion"/>
<fmt:message bundle="${loc}" key="label.main.button.conferenc" var="buttonConferenc"/>
<fmt:message bundle="${loc}" key="label.main.button.newQuestion" var="buttonQuestion"/>
<fmt:message bundle="${loc}" key="label.main.stageFour" var="stageFour"/>
<fmt:message bundle="${loc}" key="label.main.stageFourMain" var="stageFourMain"/>
<fmt:message bundle="${loc}" key="label.main.stageThree" var="stageThree"/>
<fmt:message bundle="${loc}" key="label.main.stageThreeMain" var="stageThreeMain"/>
<fmt:message bundle="${loc}" key="label.main.stageTwo" var="stageTwo"/>
<fmt:message bundle="${loc}" key="label.main.stageTwoMain" var="stageTwoMain"/>
<fmt:message bundle="${loc}" key="label.main.stageOne" var="stageOne"/>
<fmt:message bundle="${loc}" key="label.main.stageOneMain" var="stageOneMain"/>
<fmt:message bundle="${loc}" key="label.main.stages" var="stages"/>
<fmt:message bundle="${loc}" key="label.main.textThePicture" var="textThePicture"/>
<fmt:message bundle="${loc}" key="label.main.textQuestion" var="textQuestion"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/text.css"%>
    <%@include file="/WEB-INF/css/main.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<div id="main_block">
    <p><img class="bg-fon" src="/jpg/IT.jpg" alt="img"/></p>
    <h1 class="main">${textThePicture}</h1>
</div>
<div id="main_advantag">
    <h2 class="shadow">${stages}</h2>
    <p><img class="image" src="/jpg/man_with_laptop.jpg" alt="img"/></p>
    <lu>
        <li class="bolt">${stageOneMain}
            <p class="left">${stageOne}</p>
        </li>
        <li class="bolt">${stageTwoMain}
            <p class="left">${stageTwo}</p>
        </li>
        <li class="bolt">${stageThreeMain}
            <p class="left">${stageThree}</p>
        </li>
        <li class="bolt">${stageFourMain}
            <p class="left">${stageFour}</p>
        </li>
    </lu>
    <br>
    <br>
    <a class="button"
       href="${pageContext.request.contextPath}/controller?command=show_conferences">${buttonConferenc}</a>
</div>
<div>
    <h2 class="shadow">${textQuestion}</h2>
    <form name="createQuestion-form" action="${pageContext.request.contextPath}/controller?command=create_question"
          method="post">
        <label for="name-input">${boxQuestion}:</label>
        <input id="name-input" type="text" required min="1" max="245" name="name"
               pattern="^[A-ZА-Яa-zа-я]+((\s)?((\'|\-|\.|\,)?([A-ZА-Яa-zа-я])+))*$" value=""/>
        <button type="submit">${buttonQuestion}</button>
    </form>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>