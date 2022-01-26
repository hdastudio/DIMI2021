package edu.nc.tasks.repositories;

import edu.nc.tasks.models.Task;
import edu.nc.tasks.utils.StorageException;
import edu.nc.tasks.storages.TaskStorage;

import java.util.List;

public class TaskRepository implements Repository<Task, String> {
    private TaskStorage store;

    public TaskRepository() {
        this.store = new TaskStorage();
    }
    public TaskRepository(TaskStorage store) {
        this.store = store;
    }

    public void insert(Task t) throws StorageException {
        store.insert(t);

        return;
    }

    public void update(Task t) throws StorageException {
        store.update(t);

        return;
    }

    public void delete(String k) throws StorageException {
        store.delete(Integer.parseInt(k));

        return;
    }

    public boolean exists(String k) throws StorageException {
        return store.exists(Integer.parseInt(k));
    }

    public Task get(String k) throws StorageException {
        return store.get(Integer.parseInt(k));
    }

    public List<Task> getAll() throws StorageException {
        return store.getAll();
    }
}
