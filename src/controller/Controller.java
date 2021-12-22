package controller;

import java.util.HashMap;
import utils.JsonHelper;
import model.Task;
import utils.SQLHelper;

import java.util.Map;
import java.util.Scanner;

public class Controller {

    private Map<Integer, Task> taskMap = new HashMap<>();
    private JsonHelper jsonHelper = new JsonHelper();
    private SQLHelper sqlHelper = new SQLHelper();
    private String saveMethod;

    public Controller(String saveMethod) {
        this.saveMethod = saveMethod;
    }

    public String getSaveMethod() {
        return saveMethod;
    }

    public void fillMap(){
        if(saveMethod.equalsIgnoreCase("JSON")){
            jsonHelper.fillMap(taskMap);
        }else if(saveMethod.equalsIgnoreCase("DataBase")){
            sqlHelper.fillMap(taskMap);
        }
    }
    Scanner sc = new Scanner(System.in);

    public void actionAdd(){
        if(saveMethod.equalsIgnoreCase("JSON")){
            jsonHelper.addTask(taskMap);
        }else if(saveMethod.equalsIgnoreCase("DataBase")){
            sqlHelper.addTask(taskMap);
        }
    }

    public void actionDisplay(){
        if(saveMethod.equalsIgnoreCase("JSON")){
            System.out.println(taskMap);
        }else if(saveMethod.equalsIgnoreCase("DataBase")){
            sqlHelper.displayTaskList();
        }
    }

    public void actionSave(){
        jsonHelper.saveTaskMap(taskMap);
    }

    public void actionDelete(){
        if(saveMethod.equalsIgnoreCase("JSON")){
            jsonHelper.deleteTask(taskMap);
        }else if(saveMethod.equalsIgnoreCase("DataBase")){
            sqlHelper.deleteTask(taskMap);
        }
    }

    public void actionExit(){
        if(saveMethod.equalsIgnoreCase("JSON")){
            jsonHelper.closeProgram(taskMap);
        }else if(saveMethod.equalsIgnoreCase("DataBase")){
            sqlHelper.closeProgram();
        }
    }
}
