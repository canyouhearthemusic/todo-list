package com.todolist.todo.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "tasks")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String text;
    private boolean completed = false;
    private LocalDateTime createdAt = LocalDateTime.now();
    public Todo(int id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public Todo() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id && completed == todo.completed && Objects.equals(title, todo.title) && Objects.equals(text, todo.text) && Objects.equals(createdAt, todo.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, completed, createdAt);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", completed=" + completed +
                ", createdAt=" + createdAt +
                '}';
    }
}
