package services.impl;

import constants.SqlQueries;
import models.Task;
import services.TaskRepository;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DbTaskRepositoryImpl implements TaskRepository {

    static final String url = "jdbc:oracle:thin:@sql.edu-netcracker.com:1251:xe";
    static final String username = "TLT_4";
    static final String password = "TLT_4";

    Scanner scan = new Scanner(System.in);

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
            PreparedStatement preparedStatement = getConnection().prepareStatement(SqlQueries.GET_TASK);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            // Example: 1 | Задача 1 | Описание 1
            while(resultSet.next()){
                return new Task(id, resultSet.getString(2), resultSet.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateTask(Task updatedTask) {
        //TBD
        try{
            String sql = "UPDATE tasks t SET t.NAME = ? , t.DESCRIPTION = ? WHERE t.ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setString(1, updatedTask.getName());
            preparedStatement.setString(2, updatedTask.getDescription());
            preparedStatement.setInt(3, updatedTask.getId());
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(Integer id) {
        //TBD
        try {
            String sql = "DELETE FROM tasks WHERE ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();

        }
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