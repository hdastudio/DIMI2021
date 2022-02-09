package utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import model.Task;

public class SQLHelper {

    private final String url      = "jdbc:oracle:thin:@sql.edu-netcracker.com:1251:xe";
    private final String user     = "TLT_24";
    private final String password = "TLT_24";
    private Connection connection;

    private void link(){
        String driverName = "oracle.jdbc.OracleDriver";
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void createTask(String name, String description) {
        link();
        try{
            String sqlRequest = "INSERT INTO Task (ID, NAME, DESCRIPTION) Values(task_list_seq.NEXTVAL, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public String getTask(int id) {
        link();
        String task = null;
        try {
            String sqlRequest = "SELECT * FROM Task WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer identification = resultSet.getInt(1);
                String taskName = resultSet.getString(2);
                String taskDescription = resultSet.getString(3);
                task += identification + " " + taskName + " " + taskDescription;
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return task;
    }

    public void updateTask(int id, String name, String description) {
        link();
        try {
            String sqlRequest = "UPDATE Task SET NAME = ?, DESCRIPTION = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public Map<Integer, Task> getTaskList() {
        Map<Integer, Task> taskList = new HashMap<>();
        link();
        try {
            String sqlRequest = "SELECT * FROM Task";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String taskName = resultSet.getString(2);
                String taskDescription = resultSet.getString(3);
                taskList.put(id, new Task(taskName, taskDescription));
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return taskList;
    }

    public void deleteTask(int id) {
        link();
        try {
            String sqlRequest = "DELETE FROM Task WHERE ID = ";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest + id);
            preparedStatement.executeQuery();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void fillMap(Map<Integer, Task> taskMap) {
        link();
        try {
            String sqlRequest = "SELECT * FROM Task";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                taskMap.put(
                        resultSet.getInt("ID"),
                        new Task(resultSet.getString("NAME"),
                                resultSet.getString("DESCRIPTION"))
                );
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
