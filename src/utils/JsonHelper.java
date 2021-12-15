package utils;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import model.Task;

public class JsonHelper {
    //read and write from/in JSON file
    private ObjectMapper mapper = new ObjectMapper();
    private ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

    public void fillMap(Map<Integer, Task> taskMap){
        try{
            taskMap = (Map<Integer, Task>) mapper.readValue(Paths.get("tasks.json").toFile(), Map.class);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void writeToJSON(Map<Integer, Task> map){
        try {
            writer.writeValue(Paths.get("tasks.json").toFile(), map);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
