<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add new Task</title>
</head>
<body>
<h1>Tasks Directory</h1>
<hr/>
<form action="${pageContext.request.contextPath}/tasks" method="POST">

    <div>
        <label>Task Name</label> <br>
        <input type="text"  name="name"  placeholder="task name" value="${tasks.taskName}"/>
    </div>
    <div>
        <label>Task Description</label><br>
        <input type="text" name="description"  placeholder="task description" value="${tasks.taskDescription}"/>
    </div>

    <button type="submit">Save</button>
</form>
<br>
<a href="${pageContext.request.contextPath}/tasks?action=LIST">Back to List</a>
</div>

</body>