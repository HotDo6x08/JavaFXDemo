package com.km_technology_javafx.models;

import java.time.LocalDateTime;

public class TodoItem {
    private String description;
    private LocalDateTime createdTime;
    private LocalDateTime deadline;

    public TodoItem(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
        this.createdTime = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
}
