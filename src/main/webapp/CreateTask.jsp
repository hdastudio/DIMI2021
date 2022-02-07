<%--
  Created by IntelliJ IDEA.
  User: SifolDifol
  Date: 10.01.2022
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Task</title>
</head>
<body>
    <jsp:include page="index.jsp"></jsp:include>

    <h3>Create Task</h3>

    <p style="color: red;">${errorString}</p>

    <form method="POST" action="/createSaveTask">
        <table border="0">
            <tr>
                <td>ID</td>
                <td><input type="Integer" name="id" /></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" name="description" /></td>
            </tr>
            <tr>
                <td colspan="3">
                    <input type="submit" value="Submit" />
                    <a href="/tasks">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
