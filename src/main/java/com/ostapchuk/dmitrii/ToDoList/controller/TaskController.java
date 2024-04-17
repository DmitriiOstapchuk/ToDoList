package com.ostapchuk.dmitrii.ToDoList.controller;

import com.ostapchuk.dmitrii.ToDoList.data.entity.Task;
import com.ostapchuk.dmitrii.ToDoList.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;



    @GetMapping("/pages/{page}")
    public String findAll (@PathVariable int page, @RequestParam(defaultValue = "10", name= "count") int tasksPerPage, Model model) {
        model.addAttribute("newTask", new Task());
        Pageable pageable = PageRequest.of(page-1, tasksPerPage);
        model.addAttribute("tasksByPage", taskService.findAll(pageable));
        List<Integer> pages = new ArrayList<>();
        int numberOfPages = (int) Math.ceil((double) taskService.count() / tasksPerPage);
        for (int i = 0; i < numberOfPages; i++) {
            pages.add(i+1);
        }
        System.out.println(taskService.count());
        pages.forEach(System.out::println);
        model.addAttribute("pages", pages);
        return "page";
    }

    @DeleteMapping("/{id}/deleted")
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
        return "redirect:/tasks/pages/" + getNumberOfPages();
    }

    @PostMapping
    public String save(@ModelAttribute(name="newTask") Task newTask) {
        taskService.save(newTask);
        return "redirect:/tasks/pages/" + getNumberOfPages();
    }

    public int getNumberOfPages () {
        return (int) Math.ceil((double) taskService.count() / 10);
    }
}
