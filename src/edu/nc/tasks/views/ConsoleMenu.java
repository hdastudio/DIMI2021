package edu.nc.tasks.views;

import edu.nc.tasks.controllers.TasklistManager;
import edu.nc.tasks.utils.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The view component of the MVC pattern
 */
public class ConsoleMenu {
    /**
     * Prints the tasklist to the console
     *
     * @param tasks the tasks from the model component
     */
    public void printTasks(HashMap<Integer, String> tasks) {

        tasks.forEach((key, task) -> System.out.println(key + ": " + task));

        return;
    }

    /**
     * Launches the main menu
     * of the application
     *
     * @param controller the controller component
     */
    public void callMenu(TasklistManager controller) throws IOException, SQLException {

        boolean alive = true;
        int cmd = -1;

        System.out.print("""
                Выберите источник списка задач:
                1. Файл
                2. База данных
                """);
        switch (InputAction.readNumber()) {
            case 1: {
                if (FileManager.fileExists("data.xml")) {
                    controller.initializeFromFile("data.xml");
                    controller.updateDB();
                    System.out.println("Список задач загружен из файла \"data.xml\"");
                } else {
                    controller.setTasks(new HashMap<Integer, String>());
                    System.out.println("Не найден файл \"data.xml\". Создан новый список задач");
                }
                InputAction.waitForEnter();

                break;
            }
            case 2: {
                controller.initializeFromDB();
                System.out.println("Список задач загружен из базы данных");
                InputAction.waitForEnter();

                break;

            }
        }

        while (alive) {
            System.out.print("""
                Меню:
                1. Добавить
                2. Отобразить
                3. Сохранить
                4. Удалить
                5. Выход
                """);

            switch (InputAction.readNumber()) {
                //add task
                case 1: {
                    System.out.println("Введите номер задачи");
                    int taskn = InputAction.readNumber();

                    if (controller.existsTask(taskn)) {
                        System.out.println("В списке уже существует задача под номером " + taskn + " (" + controller.getTask(taskn) + ")");
                        System.out.print("Заменить задачу? (y/n) ");

                        if (!InputAction.confirm()) {
                            System.out.println("Добавление задачи прервано");
                            break;
                        }
                    }
                    System.out.println("Введите описание задачи");
                    Scanner inp = new Scanner(System.in);
                    String taskstr = inp.next();

                    controller.addTask(taskn, taskstr);
                    controller.addTaskToDB(taskn, taskstr);

                    System.out.println("Задача успешно добавлена");
                    InputAction.waitForEnter();

                    break;
                }
                //show tasks
                case 2: {
                    System.out.println("Задачи:");
                    controller.showTasks();
                    System.out.println("Конец списка задач");
                    InputAction.waitForEnter();

                    break;
                }
                //remove task
                case 4: {
                    System.out.println("Введите номер задачи");
                    int taskn = InputAction.readNumber();

                    if (!controller.existsTask(taskn)) {
                        System.out.println("Задача под номером " + taskn + " не найдена");
                        System.out.println("Удаление задачи прервано");
                    } else {
                        controller.removeTask(taskn);
                        controller.removeTaskFromDB(taskn);
                        System.out.println("Задача успешно удалена");
                    }
                    InputAction.waitForEnter();

                    break;
                }
                //quit
                case 5: {
                    alive = false;

                    System.out.print("Сохранить данные перед выходом? (y/n) ");
                    if (!InputAction.confirm()) {
                        break;
                    }
                    /* fall through */
                }
                //save to file
                case 3: {
                    System.out.print("""
                            Сохранить в формате:
                            1. xml
                            2. json
                            3. csv
                            """);

                    boolean validFormat = true;

                    String outs = "";
                    String outf = "data";

                    switch (InputAction.readNumber()) {
                        //xml
                        case 1: {
                            outs = XMLConverter.toXML(controller.getTasks());
                            outf += ".xml";

                            break;
                        }
                        //json
                        case 2: {
                            outs = JSONConverter.toJSON(controller.getTasks());
                            outf += ".json";

                            break;
                        }
                        //csv
                        case 3: {
                            outs = CSVConverter.toCSV(controller.getTasks());
                            outf += ".csv";

                            break;
                        }
                        default: {
                            validFormat = false;
                            System.out.println("Ошибка: неизвестная команда\n");
                            break;
                        }
                    }

                    if (!validFormat) {
                        System.out.println("Сохранение списка задач было прервано");
                        break;
                    }

                    if (FileManager.fileExists(outf)) {
                        System.out.print("Файл \"" + outf + "\" уже существует.\nПерезаписать файл? (y/n) ");
                        if (InputAction.confirm()) {
                            FileManager.rewriteFile(outf, outs);
                        } else {
                            System.out.println("Сохранение списка задач было прервано");
                            break;
                        }
                    } else {
                        FileManager.writeFile(outf, outs);
                    }
                    System.out.println("Список задач успешно сохранен в файл");
                    InputAction.waitForEnter();

                    break;
                }
                default: {
                    System.out.println("Ошибка: Неизвестная команда.");
                    break;
                }
            }
        }

        controller.closeDB();

        return;
    }
}