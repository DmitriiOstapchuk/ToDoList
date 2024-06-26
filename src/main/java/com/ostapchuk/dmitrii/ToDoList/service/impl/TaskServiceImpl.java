package com.ostapchuk.dmitrii.ToDoList.service.impl;

import com.ostapchuk.dmitrii.ToDoList.data.entity.Task;
import com.ostapchuk.dmitrii.ToDoList.repository.TaskRepository;
import com.ostapchuk.dmitrii.ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service

public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public Task findById(String id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Override
    public void deleteById(String id) {
        taskRepository.deleteById(id);
    }


    @Override
    public void update(String id, Task task) {
        Task existingTask = taskRepository.findById(id).orElse(null);
        existingTask.setDescription(task.getDescription());
        existingTask.setStatus(task.getStatus());
        taskRepository.save(existingTask);
    }


    @Override
    public Page<Task> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    @Override
    public int count() {
        return (int) taskRepository.count();
    }


}
