package com.todolist.todo.services;

import com.todolist.todo.dto.TodoDTO;
import com.todolist.todo.models.Todo;
import com.todolist.todo.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TodoService {

    private final TodoRepository repository;

    @Autowired
    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public List <Todo> getAllTasks() {
        return repository.findAll();
    }

    public Todo getTodoById(Integer id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public void createNewTask(TodoDTO todoDto) {
        Todo todoToSave = new Todo();
        todoToSave.setTitle(todoDto.title());
        todoToSave.setText(todoDto.text());
        repository.save(todoToSave);
    }

    public void updateTodo(Todo todoToUpdate) {
        repository.save(todoToUpdate);
    }

    public void deleteTask(Integer id) {
        repository.deleteById(id);
    }
}
