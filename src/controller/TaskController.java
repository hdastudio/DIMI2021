package controller;

import java.util.HashMap;
import utils.FileHelper;
import model.Task;
import java.util.Map;
import java.util.Scanner;

public class TaskController {

    Scanner sc = new Scanner(System.in);
    Map<Integer, Task> taskMap = new HashMap<>();
    FileHelper fileHelper = new FileHelper();

    public void copyingMap(){
        taskMap.putAll(fileHelper.fillMapFromJSON());
    }

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
            System.out.println("model.Task with this number already exists. Rewrite it?(Y/N): ");
            String answer = sc.next();
            if(answer.toLowerCase().equals("y")){
                System.out.print("Enter exercise description: ");
                sc.nextLine();
                String descriptionEx = sc.nextLine();
                Task task = new Task(numEx, descriptionEx);
                taskMap.put(task.getId(), task);
            }else{
                System.out.println("model.Task not added");
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
        fileHelper.writeToJSON(taskMap);
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
            if(choose.toLowerCase().equals("y")){
                actionSave();
                System.exit(0);
            }else if(choose.toLowerCase().equals("n")) {
                System.exit(0);
            }else {
                System.out.print("It's not a right symbol! Try again: ");
            }
        }
    }
}
