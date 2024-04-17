package com.ostapchuk.dmitrii.ToDoList.service;

import com.ostapchuk.dmitrii.ToDoList.data.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface TaskService {
    Task findById(String id);
    void save(Task task);
    void deleteById(String id);
    void update(String id, Task task);
    Page<Task> findAll(Pageable pageable);

    int count();
}
