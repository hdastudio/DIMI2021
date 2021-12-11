import java.util.Scanner;

public class ConsoleView {
    TaskController taskController;
    FileManager fileManager = new FileManager();

    public ConsoleView(TaskController taskController) {
        this.taskController = taskController;
    }

    private int chooseMenuItem(){

        int menuItem;
        Scanner scan = new Scanner(System.in);

        System.out.println("Меню:"
                +"\n"
                + "1 Добавить"
                +"\n"
                + "2 Отобразить"
                +"\n"
                + "3 Сохранить"
                +"\n"
                + "4 Удалить"
                +"\n"
                + "5 Выход"
        );
        System.out.println("Выберите нужный пункт меню");
        menuItem = scan.nextInt();
        return menuItem;
    }

    public void run(){
        boolean isAlive = true;
        Scanner scan = new Scanner(System.in);


        while (isAlive) {
            int menuItem = chooseMenuItem();

            switch (menuItem) {
                case 1:
                    System.out.println("Выбрано меню " + menuItem);

                    System.out.println("Введиде числовой номер данных для записи");
                    int id = scan.nextInt();
                    System.out.println("Введите название для записи");
                    String name = scan.next();
                    System.out.println("Введите данные для записи");
                    String description = scan.next();
                    System.out.println("Данные введены в фаил");
                    taskController.create(id, name, description);

                    break;
                case 2:
                    System.out.println("Выбрано меню " + menuItem);
                    fileManager.readJSON();

                    break;

                case 3:
                    System.out.println("Выбрано меню " + menuItem);
                    fileManager.writeJSON(this.taskController.taskList);
                    break;

                case 4:
                    System.out.println("Выбрано меню " + menuItem);
                    fileManager.deliteJSON();
                    break;

                case 5:
                    System.out.println("Выбрано меню " + menuItem);
                    System.exit(1);
                    break;

                default:
                    System.out.println("Такого в меню нет");
                    break;
            }
        }
    }
}
