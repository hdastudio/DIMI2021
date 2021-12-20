import java.util.Scanner;

public class ConsoleView {

    Task task;
    TaskController taskController;
    SQLManager sqlManager = new SQLManager();
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
                + "2 Отобразить из файла"
                +"\n"
                + "3 Отобразить из sql"
                +"\n"
                + "4 Сохранить в фаил"
                +"\n"
                + "5 Сохранить в sql"
                +"\n"
                + "6 Удалить из файла"
                +"\n"
                + "7 Удалить из sql"
                +"\n"
                + "8 Выход"
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
                    sqlManager.readSQL();
                    break;
                case 4:
                    System.out.println("Выбрано меню " + menuItem);
                    fileManager.writeJSON(this.taskController.taskList);
                    break;

                case 5:
                    System.out.println("Выбрано меню " + menuItem);
                    taskController.saveTaskListToDB();
                    break;

                case 6:
                    System.out.println("Выбрано меню " + menuItem);
                    fileManager.deliteJSON();
                    break;

                case 7:
                    System.out.println("Выбрано меню " + menuItem);
                    sqlManager.deleteSQL();
                    break;

                case 8:
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
