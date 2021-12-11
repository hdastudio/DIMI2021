import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileManager {

    JSONArray jsonList = new JSONArray();
    JSONParser jsonParser = new JSONParser();

    public void writeJSON(Map<Integer, Task> taskList) {

        jsonList.add(taskList);

        try (FileWriter file = new FileWriter("D:\\DIMI2021\\src\\main\\java\\database.json")) {
            file.write(jsonList.toJSONString());
            file.flush();
        } catch (IOException e) {
            System.out.print("Error" + e);
        }

    }

    public void readJSON() {

        try(FileReader jReadr = new FileReader("D:\\DIMI2021\\src\\main\\java\\database.json")) {

            Object object = jsonParser.parse(jReadr);
            JSONArray jsonList = (JSONArray) object;

            System.out.println(jsonList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void deliteJSON() {
        this.jsonList.remove(1);
    }

}
