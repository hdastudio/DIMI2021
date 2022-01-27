<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Delete task</title>
        <style>
            h1 { font-size: 36px; }
            p { font-size: 18px; }
        </style>
    </head>
    <body>
        <h1>Delete task:</h1>
        <form method="post" action="Controller">
            <p><label>ID: </label>
            <input type="number" id="taskId"><br><br>

            <button style="height: 25px; width: 75px" type="submit">Delete</button>
        </form>
    </body>
</html>

