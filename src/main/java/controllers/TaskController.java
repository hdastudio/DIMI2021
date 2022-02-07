package controllers;

import models.Task;
import services.TaskRepository;

import java.util.HashMap;
import java.util.Map;



public class TaskController {

    private TaskRepository taskRepository;

    private Map<Integer, Task> taskList;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.taskList = new HashMap();
    }

    public void createTask(int id, String name, String description) {
        if(name == null) throw new RuntimeException("Name cannot be null");

        Task task = new Task(id, name, description);
        taskRepository.createTask(task);
        taskList.put(id, task);
    }

    public Map<Integer, Task> getTaskList() {
        taskList = taskRepository.getTaskList();
        return taskList;
    }

    public Task getTask(Integer id) {
        if(taskList.containsKey(id)) return taskList.get(id);

        return taskRepository.getTask(id);
    }

    public void updateTask(Integer taskId, String newName, String newDescription) {
        Task updatedTask = new Task(taskId, newName, newDescription);

        taskList.put(taskId, updatedTask);

        taskRepository.updateTask(updatedTask);
    }

    public void deleteTask(Integer id) {
        taskList.remove(id);
        taskRepository.deleteTask(id);
    }

    @Deprecated
    public void saveTaskListToDB(){
        for(Integer key: taskList.keySet()){
            taskRepository.createTask(taskList.get(key));
        }
    }
}