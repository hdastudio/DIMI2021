import java.util.HashMap;
import java.util.Map;

public class TaskController {
    Map<Integer, Task> taskList;

    public TaskController() {
        this.taskList = new HashMap();
    }

    public void create(int id, String name, String description) {
        Task task = new Task(id, name, description);
        taskList.put(id, task);
    }
}