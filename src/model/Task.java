package model;

public class Task {
    String name;
    String description;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  "{" +
                "\n\ttaskNum: " + name +
                "\n\ttaskDescription: " + description +
                "\n}";
    }
}
