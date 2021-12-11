
/**
 * Консольное приложение для записи списка покупок
 *
 * @autor Ирина Приходько
 * @version 1.1
 */
package com.gg;

import java.util.HashMap;
import java.util.Map;

/**
 * Основное тело программы
 */
public class Main {


    public static void main(String[] args) {
        Map<Integer, Task> taskList = new HashMap<Integer, Task>();
        TaskController taskController = new TaskController(taskList);
        ConsoleView consoleView = new ConsoleView(taskController);
        consoleView.run();

    }
}