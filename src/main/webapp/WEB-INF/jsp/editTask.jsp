<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab3 - Edit Task</title>
</head>
<body>

<h1>Edit Task</h1>
<p>

<form:form action="/editTask" method="post" modelAttribute="task">
    <input type="hidden" name="oldn" value="${oldn}">
    <input type="hidden" name="_method" value="PUT">

    <form:label path="id">id</form:label><br>
    <form:input path="id" type="number" min="0" step="1" value="${taskn}" /><br>

    <form:label path="task">task</form:label><br>
    <form:input path="task" type="text" value="${taskc}"/><br>
    <br>
    <input type="submit" value="edit task"/>
</form:form>

<c:if test="${not empty msg}">
    <div style="color:red">${msg}</div>
</c:if>

<a href="/">Назад</a>

</body>
</html>