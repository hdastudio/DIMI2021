package edu.nc.tasks.controllers;

import java.io.*;
import java.util.List;

import edu.nc.tasks.models.Task;
import edu.nc.tasks.utils.StorageException;
import edu.nc.tasks.repositories.TaskRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "TaskController", value = "TaskController")
public class TasklistServlet extends HttpServlet {
    private TasklistManager controller;

    public void init() {
        controller = new TasklistManager(new TaskRepository());
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            List<Task> tasklist = controller.getTasks();

            req.setAttribute("tasklist", tasklist);
            req.getRequestDispatcher("showTasks.jsp").forward(req, res);
        } catch (StorageException e) {
            System.out.println("------------------------------------->");
            e.printStackTrace();

            req.setAttribute("error", e);
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String action = req.getParameter("action");

        try {
            switch (action) {
                case "add": {
                    String taskn = req.getParameter("taskn");
                    String taskc = req.getParameter("taskc");

                    //check if task # already exists
                    if (controller.existsTask(taskn)) {
                        req.setAttribute("msg", "Задача с этим номером уже существует!");
                    } else {
                        controller.addTask(taskn, taskc);
                        req.setAttribute("msg", "Задача успешно добавлена!");
                    }

                    req.getRequestDispatcher("addTask.jsp").forward(req, res);

                    break;
                }
                case "edit": {
                    String id = req.getParameter("taskn");
                    String oldid = req.getParameter("oldn");
                    String task = req.getParameter("taskc");

                    if (id.equals(oldid)) {
                        controller.editTask(id, task);

                        List<Task> tasklist = controller.getTasks();
                        req.setAttribute("tasklist", tasklist);
                        req.setAttribute("msg", "Задача успешно изменена!");

                        req.getRequestDispatcher("showTasks.jsp").forward(req, res);
                    } else {
                        if (controller.existsTask(id)) {
                            req.setAttribute("msg", "Задача с этим номером уже существует!");
                            req.setAttribute("oldn", oldid);
                            req.setAttribute("taskc", task);
                            req.setAttribute("taskn", id);
                            req.getRequestDispatcher("editTask.jsp").forward(req, res);
                        } else {
                            controller.addTask(id, task);
                            controller.removeTask(oldid);

                            List<Task> tasklist = controller.getTasks();
                            req.setAttribute("tasklist", tasklist);
                            req.setAttribute("msg", "Задача успешно изменена!");

                            req.getRequestDispatcher("showTasks.jsp").forward(req, res);
                        }
                    }

                    break;
                }
                case "delete": {
                    String id = req.getParameter("taskn");

                    controller.removeTask(id);

                    List<Task> tasklist = controller.getTasks();
                    req.setAttribute("tasklist", tasklist);
                    req.setAttribute("msg", "Задача успешно удалена!");

                    req.getRequestDispatcher("showTasks.jsp").forward(req, res);

                    break;
                }
                default: {
                    req.setAttribute("error", "dunno m8");
                    req.getRequestDispatcher("error.jsp").forward(req, res);

                    break;
                }
            }
        } catch (StorageException e) {
            System.out.println("------------------------------------->");
            e.printStackTrace();

            req.setAttribute("error", e);
            req.getRequestDispatcher("error.jsp").forward(req, res);
        }
    }
}