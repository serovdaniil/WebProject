<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.adminPanelConferenc" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.textMain" var="textMain"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.text" var="text"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.textCreate" var="textCreate"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.textAfterCreate" var="textAfterCreate"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.textUpdate" var="textUpdate"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.textAfterUpdate" var="textAfterUpdate"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.textRemove" var="textRemove"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.textAfterRemove" var="textAfterRemove"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.textFindId" var="textFindId"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.textAfterFindId" var="textAfterFindId"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.textFindName" var="textFindName"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.textAfterFindName" var="textAfterFindName"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.button.create" var="buttonCreate"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.button.update" var="buttonUpdate"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.button.remove" var="buttonRemove"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.button.search" var="buttonSearch"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.boxDescriptionConferenc" var="boxDescriptionConferenc"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.boxIdCategory" var="boxIdCategory"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.boxIdConferenc" var="boxIdConferenc"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.boxNameConferenc" var="boxNameConferenc"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.menu.create" var="menuCreate"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.menu.readCategory" var="menuReadCategory"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.menu.readConferenc" var="menuReadConferenc"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.menu.remove" var="menuRemove"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.menu.searchId" var="menuSearchId"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.menu.searchName" var="menuSearchName"/>
<fmt:message bundle="${loc}" key="label.panelConferenc.menu.update" var="menuUpdate"/>
<html>
<head>
    <title>${pageTitle}</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp"%>
<h2>${textMain}</h2>
<p>${text}</p>
<p><a href="#create" class="create">${menuCreate}</a></p>
<p><a href="#update" class="create">${menuUpdate}</a></p>
<p><a href="#remove" class="create">${menuRemove}</a></p>
<p><a href="#searchById" class="create">${menuSearchId}</a></p>
<p><a href="#searchByName" class="create">${menuSearchName}</a></p>
<p><a href="${pageContext.request.contextPath}/controller?command=show_conferences" class="create">${menuReadConferenc}</a></p>
<p><a href="${pageContext.request.contextPath}/controller?command=show_categories" class="create">${menuReadCategory}</a></p>
<p>${requestScope.result}</p>
<p>${requestScope.conferenc}</p>
<p><a name="create"></a></p>
<p class="bolt">${textCreate}
    <br>
    ${textAfterCreate}
</p>
<form name="createConferenc-form" action="${pageContext.request.contextPath}/controller?command=create_conferenc" method="post">
    <label for="nameConferenc-input" class="bolt">${boxNameConferenc}</label>
    <input id="nameConferenc-input" type="text" name="name" required min="2" max="400" pattern="^.{2,400}$"value="" />
    <br>
    <label for="description-input" class="bolt">${boxDescriptionConferenc}</label>
    <input id="description-input" type="text" name="description" required max="1000" pattern="^.{0,1000}$"value="" />
    <br>
    <label for="idCategory-input" class="bolt">${boxIdCategory}</label>
    <input id="idCategory-input" type="text" name="idCategory" required pattern="^[0-9]+$" value=""/>
    <button type="submit" class="create">${buttonCreate}</button>
    <br>
</form>
<br>
<p><a name="update"></a></p>
<p class="bolt">${textUpdate}
    <br>
    ${textAfterUpdate}</p>
<form name="updateDescriptionByConferenc-form" action="${pageContext.request.contextPath}/controller?command=update_description_in_conferenc"
      method="post">
    <label for="idUpdate-input" class="bolt">${boxIdConferenc}</label>
    <input id="idUpdate-input" type="text" name="id" value=""required pattern="^[0-9]+$"/>
    <br>
    <label for="descriprionConferenc-input" class="bolt">${boxDescriptionConferenc}</label>
    <input id="descriprionConferenc-input" type="text" name="description" value="" max="255" required pattern="^[A-ZА-Яa-zа-я]+((\s)?((\'|\-|\.|\,)?([A-ZА-Яa-zа-я])+))*$"/>
    <button type="submit" class="create">${buttonUpdate}</button>
</form>
<br>
<p><a name="remove"></a></p>
<p class="bolt">${textRemove}
    <br>
   ${textAfterRemove}
</p>
<form name="removeConferenc-form" action="${pageContext.request.contextPath}/controller?command=remove_conferenc_by_id" method="post">
    <label for="idForm-input" class="bolt">${boxIdConferenc}</label>
    <input id="idForm-input" type="text" name="id" value=""required pattern="^[0-9]+$"/>
    <button type="submit" class="create">${buttonRemove}</button>
</form>
<br>
<p><a name="searchById"></a></p>
<p class="bolt">${textFindId}
    <br>
    ${textAfterFindId}
</p>
<form name="findConferencById-form" action="${pageContext.request.contextPath}/controller?command=find_conferenc_by_id" method="post">
    <label for="idConferenc-input" class="bolt">${boxIdConferenc}</label>
    <input id="idConferenc-input" type="text" name="id" value=""required pattern="^[0-9]+$"/>
    <button type="submit" class="create">${buttonSearch}</button>
</form>
<p>${requestScope.result}</p>
<br>
<p><a name="searchByName"></a></p>
<p class="bolt">${textFindName}
    <br>
   ${textAfterFindName}
</p>
<form name="findConferencByName-form" action="${pageContext.request.contextPath}/controller?command=find_conferences_by_name" method="post">
    <label for="searchNameConferenc-input" class="bolt">${boxNameConferenc}</label>
    <input id="searchNameConferenc-input" type="text" name="name" min="2" max="255" value=""required pattern="^[A-ZА-Яa-zа-я]+((\s)?((\'|\-|\.|\,)?([A-ZА-Яa-zа-я])+))*$"/>
    <button type="submit" class="create">${buttonSearch}</button>
</form>

<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
