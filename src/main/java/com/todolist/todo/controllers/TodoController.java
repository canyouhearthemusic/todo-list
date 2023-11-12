package com.todolist.todo.controllers;

import com.todolist.todo.dto.TodoDTO;
import com.todolist.todo.models.Todo;
import com.todolist.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/todos")
@CrossOrigin(origins = "**")
public class TodoController {

    private final TodoService service;

    @Autowired
    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public List <Todo> index() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public Todo show(@PathVariable Integer id) {
        return service.getTodoById(id);
    }


    @PostMapping
    public void store(@RequestBody TodoDTO todoDTO) {
        service.createNewTask(todoDTO);
    }

    @PutMapping("/{id}")
    public void put(@PathVariable Integer id, @RequestBody TodoDTO todoDTO) {
        Todo todoToUpdate = service.getTodoById(id);
        todoToUpdate.setTitle(todoDTO.title());
        todoToUpdate.setText(todoDTO.text());
        todoToUpdate.setCompleted(todoDTO.completed());
        service.updateTodo(todoToUpdate);
    }

    @PatchMapping("/{id}")
    public void patch(@PathVariable Integer id, @RequestBody TodoDTO todoDTO) {
        Todo todoToUpdate = service.getTodoById(id);
        todoToUpdate.setCompleted(todoDTO.completed());
        service.updateTodo(todoToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public void destroy(@PathVariable Integer id) {
        service.deleteTask(id);
    }
}
