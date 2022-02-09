package servlet;

import controller.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Task;

import java.io.IOException;
import java.util.Map;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {
    Controller controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        controller = new Controller();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, Task> taskList = controller.actionGetList();
        req.setAttribute("taskList", taskList);
        req.getRequestDispatcher("/jsp/displayTasks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method){
            case "createTask":
                controller.actionAdd(req.getParameter("taskName"), req.getParameter("taskDescription"));
                req.getRequestDispatcher("/jsp/success.jsp").forward(req, resp);
                break;
            case "updateTask":
                controller.actionUpdate(Integer.parseInt(req.getParameter("taskId")), req.getParameter("taskName"), req.getParameter("taskDescription"));
                req.getRequestDispatcher("/jsp/success.jsp").forward(req, resp);
                break;
            case "deleteTask":
                controller.actionDelete(Integer.parseInt(req.getParameter("taskId")));
                req.getRequestDispatcher("/jsp/success.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    public void destroy() {}
}
