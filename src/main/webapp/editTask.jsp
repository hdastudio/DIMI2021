<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab3 - Edit Task</title>
</head>
<body>

<h1>Edit Task</h1>
<p>
<c:choose>
    <c:when test="${not empty oldn}">
        <c:set var="oldid" value="${oldn}"/>
        <c:set var="taskid" value="${taskn}"/>
        <c:set var="taskcon" value="${taskc}"/>
    </c:when>
    <c:otherwise>
        <c:set var="oldid" value="${param.taskn}"/>
        <c:set var="taskid" value="${param.taskn}"/>
        <c:set var="taskcon" value="${param.taskc}"/>
    </c:otherwise>
</c:choose>

<form method="post" action="TaskController">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="oldn" value="${oldid}">

    <label>id</label><br>
    <input type="number" id="taskn" name="taskn" min="0" step="1" value="${taskid}" required><br>

    <label>task</label><br>
    <input type="text" id="taskc" name="taskc" value="${taskcon}" required><br><br>

    <input type="submit" value="edit task">
</form>

<c:if test="${not empty msg}">
    <div style="color:red">${msg}</div>
</c:if>

<a href="index.jsp">Назад</a>

</body>
</html>