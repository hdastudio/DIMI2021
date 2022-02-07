<%@ page import="models.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: SifolDifol
  Date: 10.01.2022
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<!--  %@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>All Tasks</title>
</head>
<body>
<td><c:set var = "salary" scope = "session" value = "2000"/></td>

    <jsp:include page="index.jsp"></jsp:include>
<td><c:out value = "${salary}"/></td>
    <h3>Task List</h3>

    <% String error = (String)request.getAttribute("errorString"); %>


    <% List<Task> taskList = (ArrayList<Task>)request.getAttribute("taskList"); %>

    <p style="color: red;"><%= error %></p>

    <table border="1" cellpadding="5" cellspacing="1" >
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>DESCRIPTION</th>
            <th>EDIT</th>
            <th>DELETE</th>
        </tr>
       <% for (Task task: taskList) { %>

            <tr>
                <td><c:out value = "<%= task.getId() %>" /></td>
                <td><c:out value = "<%= task.getName() %>" /></td>
                <td><c:out value = "<%= task.getDescription() %>" /></td>
                <td>
                    <a href="/EditTask.jsp?id=<%= task.getId() %>">EDIT</a>
                </td>
                <td>
                    <a href="/deleteTask?id=<%= task.getId() %>">DELETE</a>
                </td>
            </tr>
       <% } %>
    </table>
</body>
</html>
