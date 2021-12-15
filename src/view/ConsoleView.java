package view;

import controller.Controller;

import java.util.Scanner;

public class ConsoleView {

    public String chooseSaveMethod(){
        System.out.println("Choose a save method to work:\n" +
                "1.JSON files\n" +
                "2.DataBase");
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        Integer choose = 0;
        while(check) {
            if (sc.hasNextInt()) {
                choose = sc.nextInt();
                check = false;
            }else{
                System.out.println("It's not a number!");
            }
        }
        if(choose == 1){
            return "JSON";
        }else if(choose == 2){
            return "DataBase";
        }
        return null;
    }

    public void printMenu(String chooseSaveMethod){
        if(chooseSaveMethod == "JSON"){
            System.out.print("""
                Menu:
                1.Add
                2.Display
                3.Save
                4.Delete
                5.Exit
                Choose action(write number):\s""");
            selectMenuAction(new Controller("JSON"));
        }else if(chooseSaveMethod.equals("DataBase")) {
            System.out.print("""
                Menu:
                1.Add
                2.Display
                3.Delete
                4.Exit
                Choose action(write number):\s""");
            selectMenuAction(new Controller("DataBase"));
        }else{
            System.out.println("Something went wrong...");
        }
    }

    public void selectMenuAction(Controller controller){
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        Integer choose = null;
        while(check){
            if(sc.hasNextInt()){
                choose = sc.nextInt();
                check = false;
            }else{
                System.out.println("It's not a number! Try again:");
                sc.nextLine();
            }
        }
        if (controller.getSaveMethod() == "JSON"){
            switch (choose){
                case 1 -> controller.actionAdd();
                case 2 -> controller.actionDisplay();
                case 3 -> controller.actionSave();
                case 4 -> controller.actionDelete();
                case 5 -> controller.actionExit();
            }
        }else if(controller.getSaveMethod() == "DataBase"){
            switch (choose){
                case 1 -> controller.actionAdd();
                case 2 -> controller.actionDisplay();
                case 3 -> controller.actionDelete();
                case 4 -> controller.actionExit();
            }
        }

    }
}
