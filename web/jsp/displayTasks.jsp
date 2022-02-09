<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Tasks</title>
        <style>
            h1 { font-size: 36px; }
            p { font-size: 18px; }
        </style>
    </head>
    <body>
        <h1>Tasks:</h1>

        <table>
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Description</td>
                <td>Action</td>
            </tr>

            <c:forEach var="entry" items="${taskList}">
            <tr>
                <td><c:out value="${entry.key}"/></td>
                <td><c:out value="${entry.value.getName}"/></td>
                <td><c:out value="${entry.value.getDescription}"/></td>
                <td><form method="post" action="task">
                        <input type="hidden" name="action" value="updateTask">
                    </form>
                </td>
                <td><form method="post" action="task">
                    <input type="hidden" name="action" value="deleteTask">
                </form>
                </td>
            </tr>
            </c:forEach>
        </table>

        <p><a href="/dimi2021">Back...</a>
    </body>
</html>
