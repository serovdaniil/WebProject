<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.adminPanelCategory" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.panelCategory.boxIdCategory" var="boxIdCategory"/>
<fmt:message bundle="${loc}" key="label.panelCategory.boxNameCategory" var="boxNameCategory"/>
<fmt:message bundle="${loc}" key="label.panelCategory.button.creare" var="buttonCreate"/>
<fmt:message bundle="${loc}" key="label.panelCategory.button.remove" var="buttonRemove"/>
<fmt:message bundle="${loc}" key="label.panelCategory.button.search" var="buttonSearch"/>
<fmt:message bundle="${loc}" key="label.panelCategory.button.updateName" var="buttonUpdateName"/>
<fmt:message bundle="${loc}" key="label.panelCategory.menu.create" var="menuCreate"/>
<fmt:message bundle="${loc}" key="label.panelCategory.menu.finfById" var="menuFinfById"/>
<fmt:message bundle="${loc}" key="label.panelCategory.menu.readCategories" var="menuReadCategories"/>
<fmt:message bundle="${loc}" key="label.panelCategory.menu.remove" var="menuRemove"/>
<fmt:message bundle="${loc}" key="label.panelCategory.menu.update" var="menuUpdate"/>
<fmt:message bundle="${loc}" key="label.panelCategory.text" var="text"/>
<fmt:message bundle="${loc}" key="label.panelCategory.textAfterCreate" var="textAfterCreate"/>
<fmt:message bundle="${loc}" key="label.panelCategory.textAfterFindId" var="textAfterFindId"/>
<fmt:message bundle="${loc}" key="label.panelCategory.textAfterRemove" var="textAfterRemove"/>
<fmt:message bundle="${loc}" key="label.panelCategory.textAfterUpdate" var="textAfterUpdate"/>
<fmt:message bundle="${loc}" key="label.panelCategory.textCreate" var="textCreate"/>
<fmt:message bundle="${loc}" key="label.panelCategory.textFindId" var="textFindId"/>
<fmt:message bundle="${loc}" key="label.panelCategory.textRemove" var="textRemove"/>
<fmt:message bundle="${loc}" key="label.panelCategory.textUpdate" var="textUpdate"/>
<fmt:message bundle="${loc}" key="label.panelCategory.textMain" var="textMain"/>
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
<p>${text}</p>
<p><a href="#create" class="create">${menuCreate}</a></p>
<p><a href="#update" class="create">${menuUpdate}</a></p>
<p><a href="#remove" class="create">${menuRemove}</a></p>
<p><a href="#searchById" class="create">${menuFinfById}</a></p>
<p><a href="${pageContext.request.contextPath}/controller?command=show_categories" class="create">${menuReadCategories}</a></p>
<p>${requestScope.result}</p>
<p>${requestScope.category}</p>
<p><a name="create"></a></p>
<p class="bolt">${textCreate}
    <br>
   ${textAfterCreate}
</p>
<form name="createCategory-form" action="${pageContext.request.contextPath}/controller?command=create_category" method="post">
    <label for="nameCategory-input" class="bolt">${boxNameCategory}</label>
    <input id="nameCategory-input" type="text" name="name" value="" required pattern="(^[A-Za-z]+((\s)?((\'|\-|\.|\,)?([A-Za-z])+))*$)"/>
   <button type="submit" class="create"> ${buttonCreate}</button>
    <br>
</form>
<br>
<p><a name="update"></a></p>
<p class="bolt">${textUpdate}
    <br>
    ${textAfterUpdate}</p>
<form name="updateNameCategory-form" action="${pageContext.request.contextPath}/controller?command=change_name_category"
      method="post">
    <label for="idUpdate-input" class="bolt">${boxIdCategory}</label>
    <input id="idUpdate-input" type="text" name="id" value=""required pattern="^[0-9]+$"/>
    <br>
    <label for="nameCategory-input" class="bolt">${boxNameCategory}</label>
    <input id="nameCategory-input" type="text" name="name" value=""required pattern="^[A-Za-z]+((\s)?((\'|\-|\.|\,)?([A-Za-z])+))*$"/>
    <button type="submit" class="create">${buttonUpdateName}</button>
    <br>
</form>
<br>
<p><a name="remove"></a></p>
<p class="bolt">${textRemove}
    <br>
   ${textAfterRemove}
</p>
<form name="removeCategory-form" action="${pageContext.request.contextPath}/controller?command=remove_category_by_id" method="post">
    <label for="idForm-input" class="bolt">${boxIdCategory}</label>
    <input id="idForm-input" type="text" name="id" value=""required pattern="^[0-9]+$"/>
    <button type="submit" class="create">${buttonRemove}</button>
</form>
<br>
<p><a name="searchById"></a></p>
<p class="bolt">${textFindId}
    <br>
    ${textAfterFindId}
</p>
<form name="findCategoryById-form" action="${pageContext.request.contextPath}/controller?command=id_category" method="post">
    <label for="idCategory-input" class="bolt">${boxIdCategory}</label>
    <input id="idCategory-input" type="text" name="id" value=""required pattern="^[0-9]+$"/>
    <button type="submit" class="create">${buttonSearch}</button>
</form>

<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
