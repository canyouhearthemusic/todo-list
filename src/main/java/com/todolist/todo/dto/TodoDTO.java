package com.todolist.todo.dto;

import java.time.LocalDateTime;

public record TodoDTO(
        Integer id,
        String title,
        String text,
        boolean completed,
        LocalDateTime createdAt
) {};