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
    <title>Delete Task</title>
</head>
<body>
    <jsp:include page="index.jsp"></jsp:include>

<h3>Delete Task</h3>
    <input type="hidden" name="id" value="${id}" />
    <p style="color: red;">${errorString}</p>
    <a href="/tasks">All Tasks</a>

</body>
</html>
