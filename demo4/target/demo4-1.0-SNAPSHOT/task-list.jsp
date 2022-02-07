<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tasks Directory</title>

</head>
<body>
<div>
<h1>Tasks Directory</h1>
<hr/>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>

    <c:forEach items="${list}" var="tasks">

        <tr>
            <td><c:out value="${tasks.taskId}"/></td>
            <td><c:out value="${tasks.taskName}"/></td>
            <td><c:out value="${tasks.taskDescription}"/></td>
            <td>
                <a href="${pageContext.request.contextPath}/tasks?action=EDIT&id=${tasks.taskId}">Edit</a>
                |
                <a href="${pageContext.request.contextPath}/tasks?action=DELETE&id=${tasks.taskId}">Delete</a>
            </td>
        </tr>

    </c:forEach>

</table>
    <p>${NOTIFICATION}</p>

    <p>
        <button onclick="window.location.href = 'task-form.jsp'">Add new task</button>
    </p>
</div>

</body>
</html>