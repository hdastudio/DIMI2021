package edu.nc.tasks.controllers;

import edu.nc.tasks.models.Tasklist;
import edu.nc.tasks.utils.DBManager;
import edu.nc.tasks.utils.FileManager;
import edu.nc.tasks.views.ConsoleMenu;

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
     * Instantiates a new tasklist manager
     *
     * @param model the model component
     * @param view  the view component
     * @param db    the database manager
     */
    public TasklistManager(Tasklist model, ConsoleMenu view, DBManager db) throws SQLException {

        this.model = model;
        this.view = view;

        this.db = db;
        db.connect();

        return;
    }

    /**
     * Calls the model component to
     * get the HashMap<Integer, String>
     * containing the tasklist
     *
     * @return HashMap<Integer, String>
     *         containing the tasklist
     */
    public HashMap<Integer, String> getTasks() {

        return model.getTasks();
    }

    /**
     * Calls the model component to
     * set the HashMap<Integer, String>
     * containing the tasklist
     *
     * @return HashMap<Integer, String>
     *         containing the tasklist
     */
    public void setTasks(HashMap<Integer, String> tasks) {

        model.setTasks(tasks);

        return;
    }

    /**
     * Checks if a task with the specified
     * number exists
     *
     * @param key # of the task
     * @return boolean true if the task exists;
     *                 false if the task doesn't exist
     */
    public boolean existsTask(int key) {

        return model.getTasks().containsKey(key);
    }

    /**
     * Gets a task with the specified
     * number.
     *
     * @param key # of the task to return
     * @return the specified task
     */
    public String getTask(int key) {

        return model.getTask(key);
    }

    /**
     * Calls the model component to
     * add a task to the map
     *
     * @param key  # of the task
     * @param task the task
     */
    public void addTask(int key, String task) {

        model.addTask(key, task);

        return;
    }

    /**
     * Adds the task to the database,
     * by either inserting a new row or
     * updating an existing one
     *
     * @param key # of the task
     * @param task the task
     */
    public void addTaskToDB(int key, String task) throws SQLException {

        if (db.rowExists(key)) {
            db.update(key, task);
        } else {
            db.insert(key, task);
        }

        return;
    }

    /**
     * Calls the model component to
     * remove the specified task
     * from the tasklist
     *
     * @param key # of the task to remove
     */
    public void removeTask(int key) {

        model.removeTask(key);

        return;
    }

    /**
     * Removes the specified task from the
     * database by deleting the appropriate row
     *
     * @param key the key
     */
    public void removeTaskFromDB(int key) throws SQLException {

        db.delete(key);

        return;
    }

    /**
     * Calls the view component to print
     * the tasklist to the console
     */
    public void showTasks() {

        view.printTasks(model.getTasks());

        return;
    }

    /**
     * Initializes the tasklist from a file
     *
     * @param path the path to the file
     */
    public void initializeFromFile(String path) throws IOException {

        model.setTasks(FileManager.readXML(path));

        return;
    }

    /**
     * Initializes the tasklist from the database
     *
     * @throws SQLException if a database access error occurs
     */
    public void initializeFromDB() throws SQLException {

        model.setTasks(new HashMap<Integer, String>());
        ResultSet rs = db.executeQuery("select * from sss_tasks");

        //slow??
        while (rs.next()) {
            model.addTask(rs.getInt(1), rs.getString(2));
        }

        return;

    }

    /**
     * Updates the database to match the local tasklist
     * stored in the HashMap<Integer, String>
     *
     * @throws SQLException if a database access error occurs
     */
    public void updateDB() throws SQLException {

        ResultSet rs = db.executeQuery("select * from sss_tasks");
        HashMap<Integer, String> tasksCopy = model.getTasks();

        //delete rows
        while (rs.next()) {
            int curKey = rs.getInt(1);
            String curTask = rs.getString(2);

            if (!existsTask(curKey)) {
                removeTaskFromDB(curKey);
                tasksCopy.remove(curKey);
            }
        }

        //update & insert rows
        tasksCopy.forEach((key, task) -> {
            try {
                addTaskToDB(key, task);
            } catch (SQLException e) {
                System.out.println("Произошла ошибка при внесении в базу данных задачи \"" + key + ": " + task + "\"");
            }
        });

        return;
    }
}
