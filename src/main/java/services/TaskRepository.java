package services;

import models.Task;

import java.util.Map;

public interface TaskRepository {
    void createTask(Task task);

    Task getTask(Integer id);

    void updateTask(Task updateTask);

    void deleteTask(Integer id);

    Map<Integer, Task> getTaskList();
}
