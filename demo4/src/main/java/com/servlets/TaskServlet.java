package com.servlets;

import java.io.IOException;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.DAO;
import Utils.DAOImpl;
import model.Tasks;

public class TaskServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    RequestDispatcher dispatcher = null;
    DAO dao = null;

    public TaskServlet() {
        dao = new DAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "LIST";
        }

        switch (action) {

            case "LIST":
                listTasks(request, response);
                break;

            case "EDIT":
                getTaskById(request, response);
                break;

            case "DELETE":
                deleteTask(request, response);
                break;

            default:
                listTasks(request, response);
                break;

        }

    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        if (dao.delete(Integer.parseInt(id))) {
            request.setAttribute("NOTIFICATION", "Task Deleted Successfully!");
        }

        listTasks(request, response);
    }

    private void getTaskById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        Tasks task = dao.get(Integer.parseInt(id));

        request.setAttribute("task", task);

        dispatcher = request.getRequestDispatcher("/task-form.jsp");

        dispatcher.forward(request, response);
    }

    private void listTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Tasks> theList = dao.get();

        request.setAttribute("list", theList);

        dispatcher = request.getRequestDispatcher("/task-list.jsp");

        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        Tasks tasks = new Tasks();
        tasks.setTaskName(request.getParameter("name"));
        tasks.setTaskDescription(request.getParameter("description"));

        if (id == null|| id.isEmpty() ) {

            if (dao.save(tasks)) {
                request.setAttribute("NOTIFICATION", "Task Saved Successfully!");
            }

        } else {

            tasks.setTaskId(Integer.parseInt(id));
            if (dao.update(tasks)) {
                request.setAttribute("NOTIFICATION", "Task Updated Successfully!");
            }

        }

        listTasks(request, response);
    }

}