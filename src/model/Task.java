package model;

public class Task {
    Integer id;
    String description;

    public Task(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "model.Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
