package project.entity;

import java.time.LocalDate;

public class Report {
    private int id;
    private String type;       // DAILY, WEEKLY, MONTHLY
    private LocalDate date;
    private String content;

    public Report(int id, String type, LocalDate date, String content) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    // Helper method for displaying in UI
    public String summary() {
        return type + " Report (ID: " + id + ") generated on " + date + "\n" + content;
    }
}
