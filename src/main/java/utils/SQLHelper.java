package utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Task;

public class SQLHelper {

    private final String url = "jdbc:oracle:thin:@sql.edu-netcracker.com:1251:xe";
    private final String user = "TLT_24";
    private final String password = "TLT_24";
    Scanner sc = new Scanner(System.in);

    public void addTask(String name, String description){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String sqlRequest = "INSERT INTO Task (ID, NAME, DESCRIPTION) Values(task_list_seq.NEXTVAL, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
        }catch (Exception exception){
            System.out.println(exception);
        }
    }

    public Map<Integer, Task> displayTaskList(){
        Map<Integer, Task> taskList = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String sqlRequest = "SELECT * FROM Task";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
               int id = resultSet.getInt(1);
               String taskName = resultSet.getString(2);
               String taskDescription = resultSet.getString(3);

               taskList.put(id, new Task(taskName, taskDescription));
            }
        }catch (Exception exception){
            System.out.println("Connection failed...");
        }
        return taskList;
    }

    public void deleteTask(int id){
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            String sqlRequest = "DELETE FROM Task WHERE ID = ";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest + id);
            ResultSet resultSet = preparedStatement.executeQuery();
        }catch (Exception ex){
            System.out.println("Connection failed...");
        }
    }

    public void fillMap(Map<Integer, Task> taskMap){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String sqlRequest = "SELECT * FROM Task";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                taskMap.put(
                        resultSet.getInt("ID"),
                        new Task(resultSet.getString("NAME"),
                        resultSet.getString("DESCRIPTION"))
                );
            }
        }catch (Exception exception){
            System.out.println("Connection failed...");
        }
    }
}
