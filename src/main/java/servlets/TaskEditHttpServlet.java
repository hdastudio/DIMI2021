package servlets;

import controllers.TaskController;
import models.Task;
import services.impl.DbTaskRepositoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/editTasks"})
public class TaskEditHttpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    TaskController taskController;

    @Override
    public void init() throws ServletException {
        super.init();
        taskController = new TaskController(new DbTaskRepositoryImpl());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/EditTask.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("2312r2f3rtb5y");

        taskController.updateTask(id, name, description);
        response.sendRedirect(request.getContextPath() + "/tasks");
    }
}
