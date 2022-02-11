package com.nt.controller;

import com.nt.model.Task;
import com.nt.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/createTask")
    public String create(Model model) {
        return "create";
    }

    @PostMapping("/createTask")
    public String createPost(@RequestParam String name, @RequestParam String description, Model model){
        Task task = new Task(name, description);
        taskRepo.save(task);
        return "create";
    }

    @GetMapping("/updateTask")
    public String update(Model model){
        return "update";
    }

    @PostMapping("/updateTask")
    public String updatePost(@RequestParam Long id, @RequestParam String name, @RequestParam String description, Model model){
        Task task = taskRepo.findById(id).orElseThrow(IllegalStateException::new);
        task.setName(name);
        task.setDescription(description);
        taskRepo.save(task);
        return "redirect:/";
    }

    @GetMapping("/deleteTask")
    public String delete(Model model){
        return "delete";
    }

    @PostMapping("/deleteTask")
    public String deletePost(@RequestParam Long id, Model model){
        taskRepo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/displayTasks")
    public String display(Map<String, Object> model){
        Iterable<Task> tasks = taskRepo.findAll();
        model.put("tasks", tasks);
        return "display";
    }

    @GetMapping("/displayTasks/{id}")
    public String displayTaskDetails(@PathVariable(value = "id") Long id, Model model){
        Optional<Task> task = taskRepo.findById(id);
        ArrayList<Task> result = new ArrayList<>();
        task.ifPresent(result::add);
        model.addAttribute("tasks", result);
        return "display_detail";
    }
}
