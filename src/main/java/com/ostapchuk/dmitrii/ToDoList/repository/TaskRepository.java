package com.ostapchuk.dmitrii.ToDoList.repository;

import com.ostapchuk.dmitrii.ToDoList.data.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

}
