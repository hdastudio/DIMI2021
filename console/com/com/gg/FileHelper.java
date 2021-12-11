package com.gg;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.TimeZone;

/**
 * Сохранение в xml-файл
 */
public class FileHelper {
    public static void saveToFileXml(Map<Integer, Task> taskList) {


        try (FileWriter writer = new FileWriter("tasks.xml", false)) {
            writer.write("<tasks>\n");
            for (Integer key : taskList.keySet()) {
                Task task = taskList.get(key);
                writer.write("  <task>" + "\n");
                writer.write("    <taskId>" + task.getId() + "</taskId>" + "\n");
                writer.write("    <taskName>" + task.getName() + "</taskName>" + "\n");
                writer.write("    <taskDescription>" + task.getDescription() + "</taskDescription>" + "\n");
                writer.write("  </task>\n");
            }
            writer.write("</tasks>\n");
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    /**
     * Сохранение в json-файл
     */
    public static void saveToFileJson(Map<Integer, Task> taskList) {

        try (FileWriter writer = new FileWriter("tasks.json")) {
            for (Integer key : taskList.keySet()) {
                Task task = taskList.get(key);
                JSONObject tasks = new JSONObject();
                tasks.put("taskId", task.getId());
                tasks.put("taskName", task.getName());
                tasks.put("taskDescription", task.getDescription());

                writer.write(tasks.toString());
                writer.write("\n");
                writer.flush();
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }

    /**
     * Сохранение в csv-файл
     */
    public static void saveToFileCsv(Map<Integer, Task> taskList) {

        try (FileWriter file = new FileWriter("tasks.csv")) {
            for (Integer key : taskList.keySet()) {
                Task task = taskList.get(key);
                StringBuilder sb = new StringBuilder();
                sb.append("taskId");
                sb.append(',');
                sb.append("taskName");
                sb.append(',');
                sb.append("taskDescription");
                sb.append('\n');

                sb.append(task.getId());
                sb.append(',');
                sb.append(task.getName());
                sb.append(',');
                sb.append(task.getDescription());
                sb.append('\n');

                file.write(sb.toString());
                file.close();
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }

    }
/**
 * Установка соединения с бд
 */
    public static Connection getConnection() throws SQLException, IOException {

        Properties props = new Properties();
        try (InputStream in = Files.newInputStream(Paths.get("database.properties"))) {
            props.load(in);
        }
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        TimeZone timeZone = TimeZone.getTimeZone("Europe/Moscow");
        TimeZone.setDefault(timeZone);

        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Сохранение в базу данных
     */

    public static void saveToDatabase(Map<Integer, Task> taskList) {


        try (Connection conn = getConnection()) {
            for (Integer key : taskList.keySet()) {
                Task task = taskList.get(key);
                String sql = ("INSERT INTO Tasks(taskId,taskName,taskDescription) VALUES (?,?,?)");
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, task.getId());
                preparedStatement.setString(2, task.getName());
                preparedStatement.setString(3, task.getDescription());

                int rows = preparedStatement.executeUpdate();
                System.out.printf("Added %d rows", rows);
                System.out.println("\n");

            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }


    /**
     * Удаление задачи из базы данных
     */
    public static void Remove(Map<Integer, Task> taskList) {


        try (Connection conn = getConnection()) {

            Scanner input = new Scanner(System.in);
            System.out.println("Номер задачи, которую нужно удалить:");
            int selection = input.nextInt();

            String sql = ("DELETE FROM Tasks WHERE Id = ?");
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, selection);

            int rows = preparedStatement.executeUpdate();

            System.out.printf("%d row(s) deleted", rows);
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
}




