<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Add task jsp</h1>

<form method="post" action="TaskController">
    <input type="hidden" name="action" value="add">

    <label>id</label><br>
    <input type="number" id="taskn" name="taskn" min="0" step="1" placeholder="task #" required><br>

    <label>task</label><br>
    <input type="text" id="taskc" name="taskc" placeholder="task" required><br><br>

    <input type="submit" value="add task">
</form>

<c:if test="${not empty msg}">
    <div style="color:red">${msg}</div>
</c:if>

<a href="index.jsp">Назад</a>

</body>
</html>