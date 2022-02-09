<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Update task</title>
        <style>
            h1 { font-size: 36px; }
            p { font-size: 18px; }
        </style>
    </head>
    <body>
        <h1>Update task:</h1>
        <form method="post" action="/dimi2021/task">
            <input type="hidden" name="method" value="updateTask">

            <p><label>ID: </label>
            <input type="number" name="taskId"><br>

            <p><label>Name: </label>
            <input type="text" name="taskName"><br>

            <p><label>Description: </label>
            <input type="text" name="taskDescription"><br><br>

            <button style="height: 25px; width: 75px" type="submit">Update</button>

            <p><a href="/dimi2021">Back...</a>
        </form>
    </body>
</html>
