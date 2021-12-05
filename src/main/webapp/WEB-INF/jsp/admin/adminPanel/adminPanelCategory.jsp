<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin panel category</title>
</head>
<body>
<style>
    <%@include file="/WEB-INF/css/tableStyle.css"%>
    <%@include file="/WEB-INF/css/text.css"%>
</style>
<%@include file="/WEB-INF/jsp/common/header.jsp" %>
<h2>Панель администрирования категорий</h2>
<p>Данный раздел представляет собой панель управления. На странице представлены операции, который доступны для Вас.</p>
<p><a href="#create" class="create">Перейти к созданию</a></p>
<p><a href="#update" class="create">Обновить название</a></p>
<p><a href="#remove" class="create">Удалить категорию</a></p>
<p><a href="#searchById" class="create">Поиск по id категории</a></p>
<p><a href="${pageContext.request.contextPath}/controller?command=show_categories" class="create">Просмотр категорий</a></p>
<p>${requestScope.result}</p>
<p>${requestScope.category}</p>
<p><a name="create"></a></p>
<p class="bolt">Создание конференции
    <br>
    Заполните соответствующие поля ниже для создания конференции.
</p>
<form name="createCategory-form" action="/controller?command=create_category" method="post">
    <label for="nameConferenc-input" class="bolt">Name category:</label>
    <input id="nameConferenc-input" type="text" name="name" value="" required pattern="(^[A-Za-z]+((\s)?((\'|\-|\.|\,)?([A-Za-z])+))*$)"/>
   <button type="submit" class="create"> Create conferenc</button>
    <br>
</form>
<br>
<p><a name="update"></a></p>
<p class="bolt">Update name category
    <br>
    Обновление названия категории.</p>
<form name="updateNameCategory-form" action="/controller?command=change_name_category"
      method="post">
    <label for="idUpdate-input" class="bolt">ID:</label>
    <input id="idUpdate-input" type="text" name="id" value=""required pattern="^[0-9]+$"/>
    <br>
    <label for="descriprionConferenc-input" class="bolt">New name:</label>
    <input id="descriprionConferenc-input" type="text" name="name" value=""required pattern="^[A-Za-z]+((\s)?((\'|\-|\.|\,)?([A-Za-z])+))*$"/>
    <button type="submit" class="create">Update name</button>
    <br>
</form>
<br>
<p><a name="remove"></a></p>
<p class="bolt">Remove category
    <br>
    Для удаления конференции Вам неоьбходимо ввести ее id.
</p>
<form name="removeCategory-form" action="/controller?command=remove_category_by_id" method="post">
    <label for="idForm-input" class="bolt">ID conferenc:</label>
    <input id="idForm-input" type="text" name="id" value=""required pattern="^[0-9]+$"/>
    <button type="submit" class="create">Remove</button>
</form>
<br>
<p><a name="searchById"></a></p>
<p class="bolt">Find conferenc by ID
    <br>
    Поиск конференции по id.
</p>
<form name="findCategoryById-form" action="/controller?command=id_category" method="post">
    <label for="idConferenc-input" class="bolt">ID conferenc:</label>
    <input id="idConferenc-input" type="text" name="id" value=""required pattern="^[0-9]+$"/>
    <button type="submit" class="create">Search</button>
</form>

<%@include file="/WEB-INF/jsp/common/footer.jsp" %>
</body>
</html>
