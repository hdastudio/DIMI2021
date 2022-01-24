package services.impl;

import models.Task;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.TaskRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileTaskRepositoryImpl implements TaskRepository {

    JSONArray jsonList = new JSONArray();
    JSONParser jsonParser = new JSONParser();

    private void writeTaskListToFile(Map<Integer, Task> taskList) {

        jsonList.add(taskList);

        try (FileWriter file = new FileWriter("D:\\DIMI2021\\src\\main\\java\\database.json")) {
            file.write(jsonList.toJSONString());
            file.flush();
        } catch (IOException e) {
            System.out.print("Error" + e);
        }

    }

    private Map<Integer, Task> readTaskListFromFile() {

        Map<Integer, Task> taskList = new HashMap();

        try(FileReader jReadr = new FileReader("D:\\DIMI2021\\src\\main\\java\\database.json")) {

            Object object = jsonParser.parse(jReadr);
            JSONArray jsonList = (JSONArray) object;

            //TDB Заполнить taskList

            System.out.println(jsonList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return taskList;
    }


    @Override
    public void createTask(Task task) {
        Map<Integer, Task> taskList = readTaskListFromFile();
        taskList.put(task.getId(), task);
        writeTaskListToFile(taskList);
    }

    @Override
    public Task getTask(Integer id) {
        Map<Integer, Task> taskList = readTaskListFromFile();
        return taskList.get(id);
    }

    @Override
    public void updateTask(Task updateTask) {
        Map<Integer, Task> taskList = readTaskListFromFile();
        taskList.put(updateTask.getId(), updateTask);
        writeTaskListToFile(taskList);
    }

    @Override
    public void deleteTask(Integer id) {
        Map<Integer, Task> taskList = readTaskListFromFile();
        taskList.remove(id);
        writeTaskListToFile(taskList);

    }

    @Override
    public Map<Integer, Task> getTaskList() {
        return readTaskListFromFile();
    }
}
