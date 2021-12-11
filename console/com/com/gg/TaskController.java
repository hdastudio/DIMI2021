package com.gg;

import java.util.Map;

public class TaskController {
    Map<Integer, Task> taskList;


    public TaskController(Map<Integer, Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Создание новой задачи
     */
    public void create(int taskId, String taskName, String taskDesc) {


        Task myTask = new Task(taskId, taskName, taskDesc);
        taskList.put(taskId, myTask);

    }

    /**
     * Сохранение в xml-файл
     */
    public void save() {
        FileHelper.saveToFileXml(taskList);
    }

    /**
     * Сохранение в json-файл
     */
    public void save2() {
        FileHelper.saveToFileJson(taskList);
    }

    /**
     * Сохранение в csv-файл
     */
    public void save3() {
        FileHelper.saveToFileCsv(taskList);
    }

    /**
     * Сохранение в базу данных
     */
    public void save4() {
        FileHelper.saveToDatabase(taskList);
    }

    /**
     * Удаление задачи из базы данных
     */
    public void removeFromDatabase() {
        FileHelper.Remove(taskList);

    }

    /**
     * Вывод всех задач
     */
    public Map<Integer, Task> getTaskList() {
        return taskList;
    }
}
