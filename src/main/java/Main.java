import controllers.TaskController;
import services.impl.DbTaskRepositoryImpl;
import services.impl.FileTaskRepositoryImpl;
import views.ConsoleView;

public class Main {
    public static void main(String[] args) {
        DbTaskRepositoryImpl taskRepository = new DbTaskRepositoryImpl();
//        FileTaskRepositoryImpl taskRepository = new FileTaskRepositoryImpl();

        TaskController taskController = new TaskController(taskRepository);
        ConsoleView consoleView = new ConsoleView(taskController);
        consoleView.run();
    }
}
