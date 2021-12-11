public class Main {
    public static void main(String[] args) {

        TaskController taskController = new TaskController();
        ConsoleView consoleView = new ConsoleView(taskController);
        consoleView.run();
    }
}
