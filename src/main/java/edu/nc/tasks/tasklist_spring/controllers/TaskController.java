package edu.nc.tasks.tasklist_spring.controllers;

import edu.nc.tasks.tasklist_spring.models.Task;
import edu.nc.tasks.tasklist_spring.repositories.TaskRepository;
import edu.nc.tasks.tasklist_spring.utils.StorageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class TaskController {
    private TasklistManager controller;

    public TaskController() {
        this.controller = new TasklistManager(new TaskRepository());
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/showTasks")
    public String showTasks(Model model) {
        try {
            List<Task> tasklist = controller.getTasks();

            model.addAttribute("tasklist", tasklist);
            return "showTasks";
        } catch (StorageException e) {
            System.out.println("------------------------------------->");
            e.printStackTrace();

            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/addTask")
    public String gotoAddTask(Model model) {
        model.addAttribute("task", new Task());
        return "addTask";
    }

    @PostMapping("/addTask")
    public RedirectView addTask(@ModelAttribute Task task, RedirectAttributes ra) {
        RedirectView rw = new RedirectView("/addTask", true);

        try {
            controller.addTask(task);
        } catch (StorageException e) {
            System.out.println("------------------------------------->");
            e.printStackTrace();

            rw = new RedirectView("/error", true);
            ra.addFlashAttribute("error", e);
            return rw;
        }

        ra.addFlashAttribute("msg", "Задача успешно добавлена!");

        return rw;
    }

    @GetMapping("/editTask")
    public String gotoEditTask(Model model, @RequestParam String taskn,
                               @RequestParam String taskc) {
        model.addAttribute("oldn", taskn);
        model.addAttribute("taskn", taskn);
        model.addAttribute("taskc", taskc);
        model.addAttribute("task", new Task());
        return "editTask";
    }

    @PostMapping("/editTask")
    public RedirectView editTask(@ModelAttribute Task task, @RequestParam String oldn, RedirectAttributes ra) {
        RedirectView rw = new RedirectView("/showTasks", true);

        String n = Integer.toString(task.getId());
        String taskc = task.getTask();

        try {
            if (n.equals(oldn)) {
                controller.editTask(n, taskc);

                List<Task> tasklist = controller.getTasks();
                ra.addFlashAttribute("tasklist", tasklist);
                ra.addFlashAttribute("msg", "Задача успешно изменена!");

                return rw;
            } else {
                if (controller.existsTask(n)) {
                    ra.addFlashAttribute("msg", "Задача с этим номером уже существует!");
                    ra.addAttribute("oldn", oldn);
                    ra.addAttribute("taskc", taskc);
                    ra.addAttribute("taskn", n);

                    rw = new RedirectView("/editTask", true);
                    return rw;
                } else {
                    controller.addTask(n, taskc);
                    controller.removeTask(oldn);

                    List<Task> tasklist = controller.getTasks();
                    ra.addFlashAttribute("tasklist", tasklist);
                    ra.addFlashAttribute("msg", "Задача успешно изменена!");

                    return rw;
                }
            }
        } catch (StorageException e) {
            System.out.println("------------------------------------->");
            e.printStackTrace();

            rw = new RedirectView("/error", true);
            ra.addFlashAttribute("error", e);
            return rw;
        }
    }

    @PostMapping("/deleteTask")
    public RedirectView deleteTask(@RequestParam String taskn, RedirectAttributes ra) {
        RedirectView rw = new RedirectView("/showTasks", true);

        try {
            controller.removeTask(taskn);

            List<Task> tasklist = controller.getTasks();

            ra.addFlashAttribute("tasklist", tasklist);
            ra.addFlashAttribute("msg", "Задача успешно удалена!");
            return rw;
        } catch (StorageException e) {
            System.out.println("------------------------------------->");
            e.printStackTrace();

            rw = new RedirectView("/error", true);
            ra.addFlashAttribute("error", e);
            return rw;
        }
    }
}
