package edu.nc.tasks.controllers;

import edu.nc.tasks.utils.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class TasklistManager {

    private DBManager db;

    public TasklistManager(DBManager db) {

        this.db = db;

        return;
    }

    public HashMap<Integer, String> getTasks() throws SQLException {

        HashMap<Integer, String> tasklist = new HashMap<Integer, String>();

        ResultSet rs = db.getAllTasks();
        //slow??
        while (rs.next()) {
            tasklist.put(rs.getInt(1), rs.getString(2));
        }

        return tasklist;

    }

    public void setTasks(HashMap<Integer, String> tasks) throws SQLException {

        ResultSet rs = db.getAllTasks();

        //delete rows
        while (rs.next()) {
            int curKey = rs.getInt(1);
            String curTask = rs.getString(2);

            if (!existsTask(curKey)) {
                removeTask(curKey);
                tasks.remove(curKey);
            }
        }

        //update & insert rows
        tasks.forEach((key, task) -> {
            try {
                addTask(key, task);
            } catch (SQLException e) {
                System.out.println("Произошла ошибка при внесении в базу данных задачи \"" + key + ": " + task + "\"");
            }
        });

        return;
    }

    public boolean existsTask(int key) throws SQLException {

        return db.rowExists(key);

    }

    public String getTask(int key) throws SQLException {

        ResultSet task = db.executeQuery("select * from temp_user where idnum=" + key);
        return task.getString(2);

    }

    public void addTask(int key, String task) throws SQLException {

        db.insert(key, task);

        return;
    }

    public void editTask(int key, String task) throws SQLException {

        db.update(key, task);

        return;
    }

    public void removeTask(int key) throws SQLException {

        db.delete(key);

        return;
    }
    public void openDB() throws SQLException {

        db.connect();

        return;
    }

    public void closeDB() throws SQLException {

        db.disconnect();

        return;
    }

}
