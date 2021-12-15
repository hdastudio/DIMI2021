import view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();
        String check = view.chooseSaveMethod();
        while(true){
            view.printMenu(check);
        }
    }
}
