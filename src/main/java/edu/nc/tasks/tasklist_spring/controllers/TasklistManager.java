package edu.nc.tasks.tasklist_spring.controllers;

import edu.nc.tasks.tasklist_spring.models.Task;
import edu.nc.tasks.tasklist_spring.utils.StorageException;
import edu.nc.tasks.tasklist_spring.repositories.TaskRepository;

import java.sql.SQLException;
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

    public void addTask(Task task) throws StorageException {
        rep.insert(task);

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
