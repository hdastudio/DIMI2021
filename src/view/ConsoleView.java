package view;

import controller.JsonController;
import controller.SqlController;

import java.util.Scanner;

public class ConsoleView {

    public int chooseSaveMethod(){
        System.out.println("Choose a save method to work:\n" +
                "1.JSON files\n" +
                "2.DataBase");
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        Integer choose = null;
        while(check) {
            if (sc.hasNextInt()) {
                choose = sc.nextInt();
                check = false;
            }else{
                System.out.println("It's not a number!");
            }
        }
        return choose;
    }

    public void printMenu(int chooseSaveMethod){
        System.out.print("""
                Menu:
                1.Add
                2.Display
                3.Save
                4.Delete
                5.Exit
                Choose action(write number):\s""");
        if(chooseSaveMethod == 1){
            selectMenuAction(new JsonController());
        }else if(chooseSaveMethod == 2){
            selectMenuAction(new SqlController());
        }else{
            System.out.println("Something went wrong...");
        }
    }

    public void selectMenuAction(JsonController controller){
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
        switch (choose){
            case 1 -> controller.actionAdd();
            case 2 -> controller.actionDisplay();
            case 3 -> controller.actionSave();
            case 4 -> controller.actionDelete();
            case 5 -> controller.actionExit();
        }
    }

    public void selectMenuAction(SqlController controller){
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
        switch (choose){
            case 1 -> controller.actionAdd();
            case 2 -> controller.actionDisplay();
            case 3 -> controller.actionSave();
            case 4 -> controller.actionDelete();
            case 5 -> controller.actionExit();
        }
    }
}
