package utils;

import java.sql.*;
import java.util.Map;
import model.Task;

public class SQLHelper {

    private final String url = "jdbc:oracle:thin:@sql.edu-netcracker.com:1251:xe";
    private final String user = "TLT_24";
    private final String password = "TLT_24";

    public void fillMap(Map<Integer, Task> taskMap){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String sqlRequest = "SELECT * FROM Task_list";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                taskMap.put(
                        resultSet.getInt("ID"),
                        new Task(resultSet.getInt("TASK_ID"),
                        resultSet.getString("TASK_DESCRIPTION"))
                );
            }
        }catch (Exception exception){
            System.out.println("Connection failed...");
        }
    }

    public void insertRequest(Integer id, String description){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String sqlRequest = "INSERT INTO Task_list (TASK_ID, TASK_DESCRIPTION) Values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, description);
            preparedStatement.executeUpdate();
        }catch (Exception exception){
            System.out.println("Connection failed...");
        }
    }

    public void selectFromRequest(){
        try (Connection connection = DriverManager.getConnection(url, user, password)){
            String sqlRequest = "SELECT * FROM Task_list";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest);
            preparedStatement.executeQuery();
        }catch (Exception exception){
            System.out.println("Connection failed...");
        }
    }
}
