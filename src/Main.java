import controller.JsonController;
import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        JsonController controller = new JsonController();
        controller.copyingMap();
        while(true){
            view.printMenu(view.chooseSaveMethod());
        }
    }
}
