package edu.nc.tasks.controllers;

import edu.nc.tasks.models.Tasklist;
import edu.nc.tasks.utils.FileManager;
import edu.nc.tasks.views.ConsoleMenu;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * The controller component of the MVC pattern
 */
public class TasklistManager {
    private Tasklist model;
    private ConsoleMenu view;

    private DBManager db;

    /**
     * This method instantiates a new Task controller.
     *
     * @param model - model component
     * @param view  - view component
     */
    public TasklistManager(Tasklist model, ConsoleMenu view, DBManager db) throws SQLException {
        this.model = model;
        this.view = view;

        this.db = db;
        db.connect();
    }

    /**
     * This method calls the model component to
     * get the tasks.
     *
     * @return - map of tasks
     */
    public HashMap<Integer, String> getTasks() {

        return model.getTasks();
    }

    /**
     * This method calls the model component to
     * set the tasks.
     *
     * @param tasks - tasklist
     */
    public void setTasks(HashMap<Integer, String> tasks) {

        model.setTasks(tasks);

        return;
    }

    /**
     * This method checks if a task with a specified
     * number exists.
     *
     * @param key - # to check
     * @return boolean - whether the task exists
     */
    public boolean existsTask(int key) {

        return model.getTasks().containsKey(key);
    }

    /**
     * This method gets a task with a specified
     * number.
     *
     * @param key - # of the task to return
     * @return - the task
     */
    public String getTask(int key) {

        return model.getTask(key);
    }

    /**
     * This method calls the model component to
     * add a task to the map
     *
     * @param key  - # of the task
     * @param task - the task
     */
    public void addTask(int key, String task) {
        model.addTask(key, task);
        return;
    }

    /**
     * This method calls the model component to
     * remove a task from the map
     *
     * @param key - # of the task to remove
     */
    public void removeTask(int key) {
        model.removeTask(key);
        return;
    }

    /**
     * This method calls the view component to print
     * the task list to the console
     */
    public void showTasks() {

        view.printTasks(model.getTasks());
    }

    public void initializeFromFile(String path) throws IOException {

        model.setTasks(FileManager.readXML(path));

        return;
    }

    public void initializeFromDB() throws SQLException {

        ResultSet rs = db.executeQuery("select * from sss_tasks");

        //slow??
        while (rs.next()) {
            model.addTask(rs.getInt(1), rs.getString(2));
        }

        return;

    }

    //????
    public void saveToDB() throws SQLException {
        ResultSet rs = db.executeQuery("select sss_tasks.* from sss_tasks");
        HashMap<Integer, String> tasksCopy = model.getTasks();

        //update & delete rows
        while (rs.next()) {
            int curKey = rs.getInt(1);
            String curTask = rs.getString(2);

            if (existsTask(curKey)) {
                if (!getTask(curKey).equals(curTask)) {
                    rs.updateString(2, getTask(curKey));
                    rs.updateRow();
                }
                tasksCopy.remove(curKey);
            } else {
                rs.deleteRow();
            }
        }

        //insert rows
        tasksCopy.forEach((key, task) -> {
            try {
                rs.moveToInsertRow();
                rs.updateInt(1, key);
                rs.updateString(2, task);
                rs.insertRow();
                rs.moveToCurrentRow();

            } catch (SQLException e) {
                System.out.println("Произошла ошибка при внесении в базу данных задачи \"" + key + ": " + task + "\"");
            }
        });
    }
}
