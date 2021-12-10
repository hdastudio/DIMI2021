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

    public Map<Integer, Task> fillMapFromJSON(){
        Map<Integer, Task> map = new HashMap<>();
        try{
            map = (Map<Integer, Task>) mapper.readValue(Paths.get("tasks.json").toFile(), Map.class);
            return map;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return map;
    }

    public void writeToJSON(Map<Integer, Task> map){
        try {
            writer.writeValue(Paths.get("tasks.json").toFile(), map);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
