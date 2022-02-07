<%--
  Created by IntelliJ IDEA.
  User: SifolDifol
  Date: 18.01.2022
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="index.jsp"></jsp:include>
<h3>Edit Task</h3>

<p style="color: red;">${errorString}</p>

    <form method="POST" action="/editTasks">
        <input type="hidden" name="code" value="${code}" />
        <table border="0">
            <tr>
                <td>Id</td>
                <td><input type="text" name="id" /></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" name="2312r2f3rtb5y"/></td>
            </tr>
            <tr>
                <td colspan = "2">
                    <input type="submit" value="Submit" />
                    <a href="/tasks">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
