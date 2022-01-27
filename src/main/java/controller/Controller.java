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
        sqlHelper.addTask(name, description);
    }

    public Map<Integer, Task> actionDisplay(){
        return sqlHelper.displayTaskList();
    }

    public void actionDelete(int id){
        sqlHelper.deleteTask(id);
    }

//    public void actionExit(){
//        sqlHelper.closeProgram();
//    }
}
