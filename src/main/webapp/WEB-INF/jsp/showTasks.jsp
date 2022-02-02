<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Lab3 - Show Tasks</title>
</head>
<body>

<h1>Show Tasks</h1>

<table>
<tr>
    <th>idnum</th>
    <th>task</th>
    <th>edit</th>
    <th>delete</th>
</tr>

<c:forEach var="task" items="${tasklist}">
    <tr>
        <td><c:out value="${task.id}"/></td>
        <td><c:out value="${task.task}"/></td>
        <td><form:form action="/editTask" method="get">
            <input type="hidden" name="taskn" value="${task.id}">
            <input type="hidden" name="taskc" value="${task.task}">
            <input type="submit" value="edit">
        </form:form></td>
        <td><form:form action="/deleteTask" method="post">
            <input type="hidden" name="taskn" value="${task.id}">
            <input type="submit" value="delete">
        </form:form></td>
    </tr>
</c:forEach>
</table>

<c:if test="${not empty msg}">
    <div style="color:red">${msg}</div>
</c:if>
<a href="/addTask">Добавить задачу</a><br>
<a href="/">Назад</a>

</body>
</html>