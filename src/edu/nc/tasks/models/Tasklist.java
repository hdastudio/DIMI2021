package edu.nc.tasks.models;

import java.util.HashMap;

/**
 * The model component of the MVC pattern
 */
public class Tasklist {
    private HashMap<Integer, String> tasks;

    /**
     * Gets the HashMap<Integer, String>
     * containing the tasklist
     *
     * @return the HashMap<Integer, String>
     *         containing the tasklist
     */
    public HashMap<Integer, String> getTasks() {

        return tasks;
    }

    /**
     * Sets the HashMap<Integer, String>
     * containing the tasklist
     *
     * @param tasks new HashMap<Integer, String>
     *              to replace the tasklist
     */
    public void setTasks(HashMap<Integer, String> tasks) {

        this.tasks = tasks;

        return;
    }

    /**
     * Adds a task to the tasklist
     *
     * @param key  # of the task
     * @param task the task
     */
    public void addTask(int key, String task) {

        tasks.put(key, task);

        return;
    }

    /**
     * Removes the task from the tasklist
     *
     * @param key # of the task
     */
    public void removeTask(int key) {

        tasks.remove(key);

        return;
    }

    /**
     * Gets the specified task from the tasklist
     *
     * @param key # of the task
     * @return the specified task
     */
    public String getTask(int key) {

        return tasks.get(key);
    }
}
