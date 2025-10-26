package project.entity;

public class Category {
    private int id;
    private String name;
    private String description;

    // Constructor
    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // Domain methods
    public void updateDetails(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String summary() {
        return "[" + id + "] " + name + ": " + description;
    }
}
