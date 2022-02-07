package model;

import java.io.Serializable;

public class Tasks implements Serializable {
    private int taskId;
    private String taskName;
    private String taskDescription;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        return "Task {" + '\'' +
                "id=" + taskId +
                ", name='" + taskName + '\'' +
                ", description='" + taskDescription + '\'' +
                '}';


    }
}
