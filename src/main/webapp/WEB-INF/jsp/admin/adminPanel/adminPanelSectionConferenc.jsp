<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin panel section conferenc</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>Панель администрирования секций конференции</h2>
<p>Данный раздел представляет собой панель управления. На странице представлены операции, который доступны для Вас.</p>
<p><a href="#create" class="create">Перейти к созданию</a></p>
<p><a href="#update" class="create">Обновить описание</a></p>
<p><a href="#remove" class="create">Удаление секции конференции</a></p>
<p><a href="#searchById" class="create">Поиск по id секции конференции</a></p>
<p><a href="${pageContext.request.contextPath}/controller?command=show_conferences" class="create">Просмотр секций конференций</a></p>
<p><a href="${pageContext.request.contextPath}/controller?command=show_categories" class="create">Просмотр категорий</a></p>
<p>${requestScope.result}</p>
<p>${requestScope.sectionConferenc}</p>
<p><a name="create"></a></p>
<p class="bolt">Создание секции конференции
    <br>
    Заполните соответствующие поля ниже для создания секции конференции.
</p>
<form name="createConferenc-form" action="/controller?command=create_section_conferenc" method="post">
    <label for="nameConferenc-input" class="bolt">Name section conferenc:</label>
    <input id="nameConferenc-input" type="text" name="name" value="" required pattern="(^[A-Za-z]+((\s)?((\'|\-|\.|\,)?([A-Za-z])+))*$)"/>
    <br>
    <label for="description-input" class="bolt">Descriprion section conferenc:</label>
    <input id="description-input" type="text" name="description" value="" required pattern="^[A-Za-z]+((\s)?((\'|\-|\.|\,)?([A-Za-z])+))*$"/>
    <br>
    <label for="idCategory-input" class="bolt">ID conferenc:</label>
    <input id="idCategory-input" type="text" name="idConferenc" required pattern="^[0-9]+$" value=""/>
    <button type="submit" class="create"> Create section conferenc</button>
    <br>
</form>
<br>
<p><a name="update"></a></p>
<p class="bolt">Update description section conferenc
    <br>
    Обновление описания для секции конференции.</p>
<form name="updateDescriptionByConferenc-form" action="/controller?command=update_description_in_section_conferenc"
      method="post">
    <label for="idUpdate-input" class="bolt">ID:</label>
    <input id="idUpdate-input" type="text" name="id" value=""required pattern="^[0-9]+$"/>
    <br>
    <label for="descriprionConferenc-input" class="bolt">Description:</label>
    <input id="descriprionConferenc-input" type="text" name="description" value=""required pattern="^[A-Za-z]+((\s)?((\'|\-|\.|\,)?([A-Za-z])+))*$"/>
    <button type="submit" class="create">Update description</button>
    <br>
</form>
<br>
<p><a name="remove"></a></p>
<p class="bolt">Remove section conferenc
    <br>
    Для удаления секции конференции Вам неоьбходимо ввести ее id.
</p>
<form name="removeConferenc-form" action="/controller?command=remove_section_conferenc_by_id" method="post">
    <label for="idForm-input" class="bolt">ID conferenc:</label>
    <input id="idForm-input" type="text" name="id" value=""required pattern="^[0-9]+$"/>
    <button type="submit" class="create">Remove</button>
</form>

<br>
<p><a name="searchById"></a></p>
<p class="bolt">Find section conferenc by ID
    <br>
    Поиск секции конференции по id.
</p>
<form name="findConferencById-form" action="/controller?command=find_section_conferenc_by_id" method="post">
    <label for="idConferenc-input" class="bolt">ID conferenc:</label>
    <input id="idConferenc-input" type="text" name="id" value=""required pattern="^[0-9]+$"/>
    <button type="submit" class="create">Search</button>
</form>
<br>
<p><a name="searchByName"></a></p>
<p class="bolt">Find conferenc by name
    <br>
    Поиск секции конференции по имени.
</p>
<form name="findConferencByName-form" action="/controller?command=find_section_conferences_by_name" method="post">
    <label for="searchNameConferenc-input" class="bolt">ID conferenc:</label>
    <input id="searchNameConferenc-input" type="text" name="name" value=""required pattern="^[A-Za-z]+((\s)?((\'|\-|\.|\,)?([A-Za-z])+))*$"/>
    <button type="submit" class="create">Search</button>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
