<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin panel conferenc</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>Панель администрирования конферений</h2>
<p>Данный раздел представляет собой панель управления. На странице представлены операции, который доступны для Вас.</p>
<p><a href="#create" class="create">Перейти к созданию</a></p>
<p><a href="#update" class="create">Обновить описание</a></p>
<p><a href="#remove" class="create">Удаление конференции</a></p>
<p><a href="#searchById" class="create">Поиск по id конференции</a></p>
<p><a href="#searchByName" class="create">Поиск по имени конференции</a></p>
<p><a href="${pageContext.request.contextPath}/controller?command=show_conferences" class="create">Просмотр конференций</a></p>
<p><a href="${pageContext.request.contextPath}/controller?command=show_categories" class="create">Просмотр категорий</a></p>
<p><a name="create"></a></p>
<p class="bolt">Создание конференции
    <br>
    Заполните соответствующие поля ниже для создания конференции.
</p>
<form name="createConferenc-form" action="/controller?command=create_conferenc" method="post">
    <label for="nameConferenc-input" class="bolt">Name Conferenc:</label>
    <input id="nameConferenc-input" type="text" name="name" value="" required pattern="(^[A-Za-z]+((\s)?((\'|\-|\.|\,)?([A-Za-z])+))*$)"/>
    <br>
    <label for="description-input" class="bolt">Descriprion Conferenc:</label>
    <input id="description-input" type="text" name="description" required pattern="^[A-Za-z]+((\s)?((\'|\-|\.|\,)?([A-Za-z])+))*$"/>
    <br>
    <label for="idCategory-input" class="bolt">ID category:</label>
    <input id="idCategory-input" type="text" name="idCategory" required pattern="^[0-9]+$" value=""/>
    <button type="submit" class="create"> Create conferenc</button>
    <br>
</form>
<br>
<p><a name="update"></a></p>
<p class="bolt">Update description conferenc
    <br>
    Обновление описания для конференции.</p>
<form name="updateDescriptionByConferenc-form" action="/controller?command=update_description_in_conferenc"
      method="post">
    <label for="idUpdate-input" class="bolt">ID:</label>
    <input id="idUpdate-input" type="text" name="id" value=""required pattern="#^[0-9]+$#"/>
    <br>
    <label for="descriprionConferenc-input" class="bolt">Description:</label>
    <input id="descriprionConferenc-input" type="text" name="description" value=""required pattern="^[A-Za-z]+((\s)?((\'|\-|\.|\,)?([A-Za-z])+))*$"/>
    <button type="submit" class="create">Update description</button>
    <br>
</form>
<br>
<p><a name="remove"></a></p>
<p class="bolt">Remove conferenc
    <br>
    Для удаления конференции Вам неоьбходимо ввести ее id.
</p>
<form name="removeConferenc-form" action="/controller?command=remove_conferenc_by_id" method="post">
    <label for="idForm-input" class="bolt">ID conferenc:</label>
    <input id="idForm-input" type="text" name="id" value=""required pattern="#^[0-9]+$#"/>
    <button type="submit" class="create">Remove</button>
</form>
<br>
<p><a name="searchById"></a></p>
<p class="bolt">Find conferenc by ID
    <br>
    Поиск конференции по id.
</p>
<form name="findConferencById-form" action="/controller?command=find_conferenc_by_id" method="post">
    <label for="idConferenc-input" class="bolt">ID conferenc:</label>
    <input id="idConferenc-input" type="text" name="id" value=""required pattern="#^[0-9]+$#"/>
    <button type="submit" class="create">Search</button>
</form>
<br>
<p><a name="searchByName"></a></p>
<p class="bolt">Find conferenc by name
    <br>
    Поиск конференции по имнеи.
</p>
<form name="findConferencByName-form" action="/controller?command=find_conferences_by_name" method="post">
    <label for="searchNameConferenc-input" class="bolt">ID conferenc:</label>
    <input id="searchNameConferenc-input" type="text" name="name" value=""required pattern="^[A-Za-z]+((\s)?((\'|\-|\.|\,)?([A-Za-z])+))*$"/>
    <button type="submit" class="create">Search</button>
</form>
<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
