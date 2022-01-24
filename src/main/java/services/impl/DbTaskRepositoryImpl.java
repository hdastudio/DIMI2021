package services.impl;

import models.Task;
import services.TaskRepository;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DbTaskRepositoryImpl implements TaskRepository {

    static final String url = "jdbc:oracle:thin:@sql.edu-netcracker.com:1251:xe";
    static final String username = "TLT_4";
    static final String password = "TLT_4";

    private Connection getConnection() throws Exception{

        Class.forName("oracle.jdbc.OracleDriver").getDeclaredConstructor().newInstance();

         return DriverManager.getConnection(url, username, password);

    }

    @Override
    public void createTask(Task task) {
        try {
            String sql = "INSERT INTO TASKS(id,name,description) VALUES (?,?,?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt (1,task.getId());
            preparedStatement.setString (2,task.getName());
            preparedStatement.setString (3,task.getDescription());
            int rows = preparedStatement.executeUpdate();
            System.out.printf("Added %d rows", rows);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Task getTask(Integer id) {
        try {
            String sql = "SELECT * FROM TASKS WHERE ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM TASKS WHERE ID = ?");
            while(resultSet.next()){
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                Task task = new Task(id, name, description);
                return task;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateTask(Task updateTask) {
        //TBD
    }

    @Override
    public void deleteTask(Integer id) {
        //TBD
/*        try {

        }*/
    }

    @Override
    public Map<Integer, Task> getTaskList() {
        Map<Integer, Task> taskList = new HashMap<>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM TASKS");
            while(resultSet.next()){

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                Task task = new Task(id, name, description);
                taskList.put(id, task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return taskList;
    }
}
