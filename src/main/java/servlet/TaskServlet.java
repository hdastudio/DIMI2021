package servlet;

import controller.Controller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Controller")
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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method){
            case "createTask":
                controller.actionAdd(req.getParameter("taskName"), req.getParameter("taskDescription"));
                req.getRequestDispatcher("addTask.jsp").forward(req, resp);
                break;
            case "updateTask":
                break;
            case "displayTask":
                PrintWriter out = resp.getWriter();
                out.write(controller.actionDisplayTask(Integer.parseInt(req.getParameter("taskId"))));
                break;
        }
    }

    @Override
    public void destroy() {}
}
