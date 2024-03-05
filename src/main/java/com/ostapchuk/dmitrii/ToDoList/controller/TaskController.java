package com.ostapchuk.dmitrii.ToDoList.controller;

import com.ostapchuk.dmitrii.ToDoList.data.entity.Task;
import com.ostapchuk.dmitrii.ToDoList.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

//    @GetMapping
//    public String findAll(Model model) {
//        model.addAttribute("allTasks", taskService.findAll());
//        return "page1";
//    }


    @GetMapping("/pages/{page}")
    public String findAll (@PathVariable int page, @RequestParam(defaultValue = "10") int count, Model model) {
        Pageable pageable = PageRequest.of(page-1, count);
        model.addAttribute("tasksByPage", taskService.findAll(pageable));
        return "page";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id, Model model) {
        Task deletedTask = taskService.findById(id);
        taskService.deleteById(id);
        model.addAttribute("deletedTask", deletedTask);
        return "deletedTask";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable String id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        return "task";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("editedTask", taskService.findById(id));
        return "editedTask";
    }
    @PatchMapping("/{id}")
    public String update (@PathVariable String id, @ModelAttribute Task editedTask) {
        taskService.update(id, editedTask);
        return "redirect:/tasks/pages/1";
    }
}
