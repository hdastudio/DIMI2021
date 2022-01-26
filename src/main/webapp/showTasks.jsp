<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <td><form action="editTask.jsp" method="post">
            <input type="hidden" name="taskn" value="${task.id}">
            <input type="hidden" name="taskc" value="${task.task}">
            <input type="submit" name="editbtn" value="edit">
        </form></td>
        <td><form action="TaskController" method="post">
            <input type="hidden" name="action" value="delete">
            <input type="hidden" name="taskn" value="${task.id}">
            <input type="submit" name="delbtn" value="delete">
        </form></td>
    </tr>
</c:forEach>
</table>

<c:if test="${not empty msg}">
    <div style="color:red">${msg}</div>
</c:if>
<a href="addTask.jsp">Добавить задачу</a><br>
<a href="index.jsp">Назад</a>

</body>
</html>