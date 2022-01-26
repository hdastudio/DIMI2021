package edu.nc.tasks.controllers;

import edu.nc.tasks.models.Task;
import edu.nc.tasks.utils.StorageException;
import edu.nc.tasks.repositories.TaskRepository;

import java.util.List;

public class TasklistManager {
    private TaskRepository rep;

    public TasklistManager(TaskRepository rep) {
        this.rep = rep;

        return;
    }

    public void addTask(String id, String task) throws StorageException {
        rep.insert(new Task(id, task));

        return;
    }

    public void editTask(String id, String task) throws StorageException {
        rep.update(new Task(id, task));

        return;
    }

    public void removeTask(String id) throws StorageException {
        rep.delete(id);

        return;
    }

    public boolean existsTask(String id) throws StorageException {
        return rep.exists(id);
    }

    public List<Task> getTasks() throws StorageException {
        return rep.getAll();
    }
}
