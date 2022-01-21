package edu.nc.tasks.controllers;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;

import edu.nc.tasks.utils.DBManager;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static java.lang.Integer.parseInt;

@WebServlet(name = "TaskController", value = "TaskController")
public class TasklistServlet extends HttpServlet {
    private TasklistManager controller;
    private DBManager db;

    public void init() {

        controller = new TasklistManager(new DBManager());

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        System.out.println("ewe");
        try {
            controller.openDB();

            HashMap<Integer, String> tasklist = controller.getTasks();

            controller.closeDB();

            req.setAttribute("tasklist", tasklist);
            req.getRequestDispatcher("showTasks.jsp").forward(req, res);

        } catch (SQLException e) {
            System.out.println("------------------------------------->");
            e.printStackTrace();
            //req.getRequestDispatcher("error.html").forward(req, res);

        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String action = req.getParameter("action");

        try {
            switch (action) {
                case "add": {

                    controller.openDB();

                    int taskn = parseInt(req.getParameter("taskn"));
                    String taskc = req.getParameter("taskc");

                    //check if task # already exists
                    if (controller.existsTask(taskn)) {
                        req.setAttribute("msg", "Задача с этим номером уже существует!");
                    } else {
                        controller.addTask(taskn, taskc);
                        req.setAttribute("msg", "Задача успешно добавлена!");
                    }

                    controller.closeDB();

                    req.getRequestDispatcher("addTask.jsp").forward(req, res);

                    break;
                }
                case "edit": {

                    controller.openDB();

                    int id = parseInt(req.getParameter("taskn"));
                    int oldid = parseInt(req.getParameter("oldn"));
                    String task = req.getParameter("taskc");

                    if (id == oldid) {

                        controller.editTask(id, task);

                        req.setAttribute("msg", "Задача успешно изменена!");

                        HashMap<Integer, String> tasklist = controller.getTasks();
                        req.setAttribute("tasklist", tasklist);

                        controller.closeDB();

                        req.getRequestDispatcher("showTasks.jsp").forward(req, res);

                    } else {

                        if (controller.existsTask(id)) {

                            req.setAttribute("msg", "Задача с этим номером уже существует!");
                            req.setAttribute("taskc", task);
                            req.setAttribute("taskn", id);

                            controller.closeDB();

                            req.getRequestDispatcher("editTask.jsp").forward(req, res);

                        } else {

                            controller.addTask(id, task);
                            controller.removeTask(oldid);

                            req.setAttribute("msg", "Задача успешно изменена!");

                            HashMap<Integer, String> tasklist = controller.getTasks();
                            req.setAttribute("tasklist", tasklist);

                            controller.closeDB();

                            req.getRequestDispatcher("showTasks.jsp").forward(req, res);

                        }

                    }

                }
                case "delete": {

                    int id = parseInt(req.getParameter("taskn"));

                    controller.openDB();

                    controller.removeTask(id);

                    req.setAttribute("msg", "Задача успешно удалена!");

                    HashMap<Integer, String> tasklist = controller.getTasks();
                    req.setAttribute("tasklist", tasklist);

                    controller.closeDB();

                    req.getRequestDispatcher("showTasks.jsp").forward(req, res);

                }
                default: {

                }
            }
        } catch (SQLException e) {
            System.out.println("------------------------------------->");
            e.printStackTrace();
            //req.getRequestDispatcher("error.html").forward(req, res);

        }

    }

}