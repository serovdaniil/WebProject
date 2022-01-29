<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.lang.value}"/>
<fmt:setBundle basename="l10n.page.main" var="loc"/>
<fmt:message bundle="${loc}" key="label.title.adminPanelSectionConferenc" var="pageTitle"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.boxDescriptionSection" var="boxDescription"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.boxIdConferenc" var="boxIdConferenc"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.boxNameSection" var="boxNameSection"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.button.createSectionConferenc" var="buttonCreate"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.button.findSectionConferenc" var="buttonFindSectionConferenc"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.button.removeSectionConferenc" var="buttonRemoveSectionConferenc"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.button.updateSectionConferenc" var="buttonUpdateSectionConferenc"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.menu.create" var="menuCreate"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.menu.readCategory" var="menuReadCategory"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.menu.readConferenc" var="menuReadConferenc"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.menu.remove" var="menuRemove"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.menu.searchId" var="menuSearchById"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.menu.readSectionConferenc" var="menuSectionConferenc"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.menu.updateDescription" var="menuUpdateDescription"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.text" var="text"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.textMain" var="textMain"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.textAfterCreate" var="textAfterCreate"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.textAfterFindId" var="textAfterFindId"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.textAfterFindName" var="textAfterFindName"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.textAfterRemove" var="textAfterRemove"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.textAfterUpdate" var="textAfterUpdate"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.textCreate" var="textCreate"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.textFindId" var="textFindId"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.textFindName" var="textFindName"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.textRemove" var="textRemove"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.textUpdate" var="textUpdate"/>
<fmt:message bundle="${loc}" key="label.panelSectionConferenc.boxIdSectionConferenc" var="boxIdSectionConferenc"/>
<fmt:message bundle="${loc}" key="label.message.resultNotOperation" var="message"/>
<fmt:message bundle="${loc}" key="label.message.exception.common" var="exceptionCommon"/>
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
<p><a href="#update" class="create">${menuUpdateDescription}</a></p>
<p><a href="#remove" class="create">${menuRemove}</a></p>
<p><a href="${pageContext.request.contextPath}/controller?command=show_all_conferences&page=1"
      class="create">${menuReadConferenc}</a></p>
<p><a href="${pageContext.request.contextPath}/controller?command=show_categories&page=1"
      class="create">${menuReadCategory}</a></p>
<p><a href="${pageContext.request.contextPath}/controller?command=show_section_conferences&page=1"
      class="create">${menuSectionConferenc}</a></p>
<c:if test="${not empty requestScope.result}">
    <p><b>${message}</b></p>
    <br>
</c:if>
<p>${requestScope.sectionConferenc}</p>
<p><a name="create"></a></p>
<p class="bolt">${textCreate}
    <br>
   ${textAfterCreate}
</p>
<form name="createSectionConferenc-form"
      action="${pageContext.request.contextPath}/controller?command=create_section_conferenc" method="post">
    <label for="nameSectionConferenc-input" class="bolt">${boxNameSection}</label>
    <input id="nameSectionConferenc-input" type="text" name="name" min="2" max="400" value="" required pattern="^.{0,400}$"
           oninput="validateName(this)"/>
    <br>
    <label for="description-input" class="bolt">${boxDescription}</label>
    <input id="description-input" type="text" name="description" min="2" max="1000" value="" required pattern="^.{0,1000}$"
           oninput="validateDescription(this)"/>
    <br>
    <label for="idCategory-input" class="bolt">${boxIdConferenc}</label>
    <input id="idCategory-input" type="text" name="idConferenc" required pattern="^[0-9]+$" value=""
           oninput="validateId(this)"/>
    <button type="submit" class="create">${buttonCreate}</button>
    <br>
</form>
<br>
<p><a name="update"></a></p>
<p class="bolt">${textUpdate}
    <br>
    ${textAfterUpdate}</p>
<form name="updateDescriptionBySectionConferenc-form"
      action="${pageContext.request.contextPath}/controller?command=update_description_in_section_conferenc"
      method="post">
    <label for="idUpdateSection-input" class="bolt">${boxIdSectionConferenc}</label>
    <input id="idUpdateSection-input" type="text" name="id" value=""required pattern="^[0-9]+$"
           oninput="validateId(this)"/>
    <br>
    <label for="descriprionSectionConferenc-input" class="bolt">${boxDescription}</label>
    <input id="descriprionSectionConferenc-input" type="text" name="description" min="2" max="1000" value=""required
           pattern="^.{0,1000}$" oninput="validateDescription(this)"/>
    <button type="submit" class="create">${buttonUpdateSectionConferenc}</button>
    <br>
</form>
<br>
<p><a name="remove"></a></p>
<p class="bolt">${textRemove}
    <br>
    ${textAfterRemove}
</p>
<form name="removeSectionConferenc-form"
      action="${pageContext.request.contextPath}/controller?command=remove_section_conferenc_by_id" method="post">
    <label for="idSection-input" class="bolt">${boxIdSectionConferenc}</label>
    <input id="idSection-input" type="text" name="id" value="" required pattern="^[0-9]+$"
           oninput="validateId(this)"/>
    <button type="submit" class="create">${buttonRemoveSectionConferenc}</button>
</form>
<br>
<p><a name="searchByName"></a></p>
<p class="bolt">${textFindName}
    <br>${textAfterFindName}
</p>
<form name="findSectionConferencByName-form"
      action="${pageContext.request.contextPath}/controller?command=find_section_conferences_by_name" method="post">
    <label for="searchNameSectionConferenc-input" class="bolt">${boxIdSectionConferenc}</label>
    <input id="searchNameSectionConferenc-input" type="text" name="name" min="2" max="400" value=""required
           pattern="^.{2,400}$" oninput="validateName(this)"/>
    <button type="submit" class="create">${buttonFindSectionConferenc}</button>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
<script>
    var regexId = /^[0-9]+$/;
    var regexName = /^.{2,400}$/;
    var regexDescription = /^.{2,1000}$/;

    function validateId(input) {
        if (!regexId.test(input.value)) {
            input.setCustomValidity("${exceptionCommon}");
        } else {
            input.setCustomValidity("");
        }
    }

    function validateName(input) {
        if (!regexName.test(input.value)) {
            input.setCustomValidity("${exceptionCommon}");
        } else {
            input.setCustomValidity("");
        }
    }
    function validateDescription(input) {
        if (!regexDescription.test(input.value)) {
            input.setCustomValidity("${exceptionCommon}");
        } else {
            input.setCustomValidity("");
        }
    }
</script>
