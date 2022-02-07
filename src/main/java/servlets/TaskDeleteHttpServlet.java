package servlets;

import controllers.TaskController;
import services.impl.DbTaskRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/deleteTask"})
public class TaskDeleteHttpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    TaskController taskController;

    @Override
    public void init() throws ServletException {
        super.init();
        taskController = new TaskController(new DbTaskRepositoryImpl());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        taskController.deleteTask(id);
        request.getRequestDispatcher("/DeleteTask.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
    }
}
