package utils;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import model.Task;

public class JsonHelper {
    //read and write from/in JSON file
    private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
    Scanner sc = new Scanner(System.in);

    public void fillMap(Map<Integer, Task> taskMap){
        try{
            taskMap = (Map<Integer, Task>) mapper.readValue(Paths.get("tasks.json").toFile(), Map.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void saveTaskMap(Map<Integer, Task> map){
        try {
            writer.writeValue(Paths.get("tasks.json").toFile(), map);
            System.out.println("File saved!");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void addTask(Map<Integer, Task> taskMap){
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
        System.out.print("Enter exercise description: ");
        sc.nextLine();
        String descriptionEx = sc.nextLine();
        Task task = new Task(numEx, descriptionEx);
        taskMap.put(taskMap.size() + 1, task);
    }

    public void deleteTask(Map<Integer, Task> taskMap){
        boolean check = true;
        Integer numEx = null;
        System.out.println("Enter id of exercise to delete:");
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

    public void closeProgram(Map<Integer, Task> taskMap){
        while (true){
            System.out.print("Do you want to save file?(Y/N): ");
            String choose = sc.next();
            if(choose.equalsIgnoreCase("y")){
                saveTaskMap(taskMap);
                System.exit(0);
            }else if(choose.equalsIgnoreCase("n")) {
                System.exit(0);
            }else {
                System.out.print("It's not a right symbol! Try again: ");
            }
        }
    }

}
