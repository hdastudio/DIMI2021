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

    public String getSaveMethod() {
        return saveMethod;
    }

    private String saveMethod;

    public Controller(String saveMethod) {
        this.saveMethod = saveMethod;
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
        boolean check = true;
        Integer numEx = null;
        System.out.print("Enter number of exercise: ");
        while (check){
            if(sc.hasNextInt()){
                numEx = sc.nextInt();
                check = false;
            }else{
                System.out.println("It's not a number! Try again:");
                sc.nextLine();
            }
        }
        if(taskMap.containsKey(numEx)){
            System.out.println("Task with this number already exists. Rewrite it?(Y/N): ");
            String answer = sc.next();
            if(answer.equalsIgnoreCase("y")){
                System.out.print("Enter exercise description: ");
                sc.nextLine();
                String descriptionEx = sc.nextLine();
                Task task = new Task(numEx, descriptionEx);
                taskMap.put(task.getId(), task);
            }else{
                System.out.println("Task not added");
            }
        }else{
            System.out.print("Enter exercise description: ");
            sc.nextLine();
            String descriptionEx = sc.nextLine();
            Task task = new Task(numEx, descriptionEx);
            taskMap.put(task.getId(), task);
        }

    }

    public void actionDisplay(){
        System.out.println(taskMap);
    }

    public void actionSave(){
        jsonHelper.writeToJSON(taskMap);
        System.out.println("File saved!");
    }

    public void actionDelete(){
        boolean check = true;
        Integer numEx = null;
        System.out.println("Enter number of exercise to delete:");
        while (check){
            if(sc.hasNextInt()){
                numEx = sc.nextInt();
                check = false;
            }else{
                System.out.println("It's not a number! Try again:");
                sc.nextLine();
            }
        }
        taskMap.remove(numEx);
    }

    public void actionExit(){
        while (true){
            System.out.print("Do you want to save file?(Y/N): ");
            String choose = sc.next();
            if(choose.equalsIgnoreCase("y")){
                actionSave();
                System.exit(0);
            }else if(choose.equalsIgnoreCase("n")) {
                System.exit(0);
            }else {
                System.out.print("It's not a right symbol! Try again: ");
            }
        }
    }
}
