package views;

import controllers.TaskController;
import models.Task;

import java.util.Scanner;
import java.util.Map;

public class ConsoleView {
    TaskController taskController;

    public ConsoleView(TaskController taskController) {
        this.taskController = taskController;
    }

    private int chooseMenuItem(){

        int menuItem;
        Scanner scan = new Scanner(System.in);

        System.out.println("Меню:"
                +"\n"
                + "1 Добавить задачу"
                +"\n"
                + "2 Показать все задачи"
                +"\n"
                + "3 Показать задачу по идентификатору"
                +"\n"
                + "4 Изменить задачу"
                +"\n"
                + "5 Удалить задачу"
                +"\n"
                + "6 Выход"
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

                    System.out.println("Введиде уникальный номер задачи");
                    int id1 = scan.nextInt();
                    System.out.println("Введите название задачи");
                    String name = scan.next();
                    System.out.println("Введите описание задачи");
                    String description = scan.next();
                    try {
                        taskController.createTask(id1, name, description);
                        System.out.println("Задача успешно создана");
                    }
                    catch (Exception e){
                        System.out.println("Ошибка при создании задачи: "+e.getMessage());
                    }

                    break;

                case 2:
                    System.out.println("Выбрано меню " + menuItem);
                    Map<Integer, Task> taskList = taskController.getTaskList();

                    System.out.println("ID | NAME | DESCRIPTION");
                    for(Integer key: taskList.keySet()){
                        Task task = taskList.get(key);
                        System.out.println(task.getId() + " | "+ task.getName() + " | " + task.getDescription());
                    }
                    break;

                case 3:
                    System.out.println("Выбрано меню " + menuItem);
                    System.out.println("Введите идентификатор задачи для ее отображения");
                    Integer id = Integer.valueOf(scan.nextInt());
                    System.out.println("ID | NAME | DESCRIPTION");
                    System.out.println(taskController.getTask(id));

                    break;

                case 4:
                    System.out.println("Выбрано меню " + menuItem);
                    System.out.println("Введите идентификатор задачи для ее редактирования");
                    int taskId = scan.nextInt();

                    // Шаг 1. Получить задачу из БД (Проверить, что она существет)
                    System.out.println(taskController.getTask(taskId));

                    // Шаг 2. Запросить новые имя и описание

                    System.out.println("Введите новое название задачи");
                    String newName = scan.next();
                    System.out.println("Введите новое описание задачи");
                    String newDescription = scan.next();

                    // Шаг 3. Вызвать метод обновления задачи
                    taskController.updateTask(taskId, newName, newDescription);
                    System.out.println("ID | NAME | DESCRIPTION");
                    System.out.println(taskId +"|"+ newName +"|"+ newDescription);
                    break;

                case 5:
                    System.out.println("Выбрано меню " + menuItem);
                    System.out.println("Введите идентификатор задачи для ее удаления");
                    Integer id2 = Integer.valueOf(scan.nextInt());
                    taskController.deleteTask(id2);
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