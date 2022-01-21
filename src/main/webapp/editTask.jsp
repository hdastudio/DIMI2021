<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Edit task jsp</h1>

<form method="post" action="TaskController">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="oldn" value="${param.taskn}">

    <label>id</label><br>
    <input type="number" id="taskn" name="taskn" min="0" step="1" value="${param.taskn}" required><br>

    <label>task</label><br>
    <input type="text" id="taskc" name="taskc" value="${param.taskc}" required><br><br>

    <input type="submit" value="edit task">
</form>

<c:if test="${not empty msg}">
    <div style="color:red">${msg}</div>
</c:if>

<a href="index.jsp">Назад</a>

</body>
</html>