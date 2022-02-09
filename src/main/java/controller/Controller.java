package controller;

import java.util.HashMap;

import model.Task;
import utils.SQLHelper;

import java.util.Map;
import java.util.Scanner;

public class Controller {

    private Map<Integer, Task> taskMap = new HashMap<>();
    private SQLHelper sqlHelper = new SQLHelper();
    Scanner sc = new Scanner(System.in);

    public Controller() {}

    public void fillMap(){
      sqlHelper.fillMap(taskMap);
    }

    public void actionAdd(String name, String description){
        sqlHelper.createTask(name, description);
    }

    public Map<Integer, Task> actionGetList(){
        return sqlHelper.getTaskList();
    }

    public String actionGetTask(int id){ return sqlHelper.getTask(id); }

    public void actionUpdate(int id, String name, String description){ sqlHelper.updateTask(id, name, description); }

    public void actionDelete(int id){
        sqlHelper.deleteTask(id);
    }
}
