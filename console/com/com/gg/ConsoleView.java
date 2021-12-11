package com.gg;

import java.util.Scanner;

public class ConsoleView {
    TaskController taskController;

    public ConsoleView(TaskController taskController) {
        this.taskController = taskController;
    }

    /**
     * Вывод меню
     *
     * @return возвращает выбранный вариант
     */
    public static int chooseMenuItem() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("Меню");
        System.out.println("-------------------------\n");
        System.out.println("1 - Добавить");
        System.out.println("2 - Отобразить");
        System.out.println("3 - Сохранить");
        System.out.println("4 - Удалить");
        System.out.println("5 - Выход");

        System.out.println("Введите число:");
        selection = input.nextInt();
        return selection;


    }

    /**
     * освная работа программы
     */
    public void run() {
        boolean alive = true;
        while (alive) {
            int choice = chooseMenuItem();


            Scanner scanner = new Scanner(System.in);

            switch (choice) {
                case 1:
                    System.out.println("Номер задачи:");
                    int taskId = scanner.nextInt();
                    System.out.println("Введите наименование задачи:");
                    String taskName = scanner.next();
                    System.out.println("Введите описание задачи:");
                    String taskDesc = scanner.next();
                    taskController.create(taskId, taskName, taskDesc);
                    System.out.println(taskController.getTaskList());
                    break;

                case 2: {
                    System.out.println("Все задачи:\n");
                    System.out.print(taskController.getTaskList());
                    System.out.println("\n");

                }
                break;

                case 3:
                    System.out.println("Сохранить куда?");
                    System.out.println("1-xml-файл\n" + "2-json-файл\n" + "3-csv-файл\n" + "4-база данных");
                    int case1 = scanner.nextInt();
                {
                    switch (case1) {
                        case 1:
                            taskController.save();
                            System.out.println("Сохранение в xml файл успешно\n");
                            break;
                        case 2: {
                            taskController.save2();
                            System.out.println("Сохранение в json файл успешно\n");
                            break;
                        }
                        case 3: {
                            taskController.save3();
                            System.out.println("Сохранение в csv файл успешно\n");
                            break;
                        }
                        case 4: {
                            taskController.save4();
                            System.out.println("Сохранение в базу данных успешно\n");
                            break;
                        }
                    }
                }
                break;
                case 4: {
                    System.out.println("Удалить из?");
                    System.out.println("1-файл\n" + "2-база данных\n");
                    int case2 = scanner.nextInt();
                    switch (case2) {
                        case 1:
                            System.out.println("Номер задачи, которую нужно удалить:");
                            int c = scanner.nextInt();
                            taskController.getTaskList().remove(c);
                            System.out.println("Задача удалена");
                            break;

                        case 2:
                            taskController.removeFromDatabase();
                            System.out.println("Задача удалена");
                            break;
                    }
                    break;
                }
                case 5:
                    System.out.println("Сохранить?");
                    System.out.println("1-да\n" + "2-нет\n");
                    int case3 = scanner.nextInt();
                    switch (case3) {
                        case 1:
                            taskController.save();
                            taskController.save2();
                            taskController.save3();
                            taskController.save4();
                            System.out.println("Ваш список сохранен\n");
                            System.out.println("Работа завершена");
                            break;
                        case 2:
                            System.out.println("Сохранение не было произведено\n");
                            System.out.println("Работа завершена");
                            break;
                    }
                    alive = false;
                default:
                    throw new IllegalStateException("Неккоректный номер задачи: " + choice);
            }
        }
    }


}
