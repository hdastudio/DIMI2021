<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create task</title>
        <style>
            h1 { font-size: 36px; }
            p { font-size: 18px; }
        </style>
    </head>
    <body>
    <h1>Create task:</h1>
        <form method="post" action="Controller">
            <input type="hidden" id="method" value="createTask">

            <p><label>Name: </label>
            <input type="text" id="taskName"><br>

            <p><label>Description: </label>
            <input type="text" id="taskDescription"><br><br>

            <button style="height: 25px; width: 75px" type="submit">Create</button>
        </form>
    </body>
</html>
