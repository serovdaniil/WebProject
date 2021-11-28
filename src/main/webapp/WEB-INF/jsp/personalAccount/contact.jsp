
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Contacts</title>
</head>
<body>
<h3>Create question</h3>

<form name="createQuestion-form" action="/controller?command=create_question" method="post">
    <label for="name-input">Question:</label>
    <input id="name-input" type="text" name="name" value=""/>
    <input type="submit" value="Create"/>
</form>
<h4> Result: ${requestScope.result}</h4>
</body>
</html>
