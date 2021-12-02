package view;

import controller.TaskController;

import java.util.Scanner;

public class ConsoleView {
    public void printMenu(){
        System.out.print("""
                Menu:
                1.Add
                2.Display
                3.Save
                4.Delete
                5.Exit
                Choose action(write number):\s""");
    }

    public void selectMenuAction(TaskController controller){
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
