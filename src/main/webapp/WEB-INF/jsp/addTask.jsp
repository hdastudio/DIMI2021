<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Lab3 - Add Task</title>
</head>
<body>

<h1>Add Task</h1>
<p>
    <form:form action="/addTask" method="post" modelAttribute="task">
        <form:label path="id">id</form:label><br>
        <form:input path="id" type="number" min="0" step="1" placeholder="task #" /><br>
        <br>
        <form:label path="task">task</form:label><br>
        <form:input path="task" placeholder="task"/><br>

        <input type="submit" value="add task"/>
    </form:form>

<c:if test="${not empty msg}">
    <div style="color:red">${msg}</div>
</c:if>

<a href="/">Назад</a>

</body>
</html>