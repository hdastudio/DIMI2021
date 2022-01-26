package edu.nc.tasks.models;

public class Task {
    private int id;
    private String task;

    public Task(int id, String task) {
        this.id = id;
        this.task = task;
    }
    public Task(String id, String task) {
        this.id = Integer.parseInt(id);
        this.task = task;
    }

    public void setId(int newId) {
        id = newId;
    }

    public void setTask(String newTask) {
        task = newTask;
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }
}
