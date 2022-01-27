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
        <form method="post" action="Controller">
            <p></p><label>Name: </label>
            <input type="text" id="taskName"><br>

            <p></p><label>Description: </label>
            <input type="text" id="taskDescription"><br><br>

            <button style="height: 25px; width: 75px" type="submit">Update</button>
        </form>
    </body>
</html>
