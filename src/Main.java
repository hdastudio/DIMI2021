import controller.TaskController;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        TaskController controller = new TaskController();
        controller.copyingMap();
        while(true){
            view.printMenu();
            view.selectMenuAction(controller);
        }
    }
}
