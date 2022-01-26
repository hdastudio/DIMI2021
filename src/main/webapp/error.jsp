<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lab3 - Error</title>
</head>
<body>
<h1>DB error'd :(</h1>
<p>
<c:if test="${not empty error}">
    <div style="color:red">${error}</div>
</c:if>

</body>
</html>
