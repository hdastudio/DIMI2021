package servlets;

import controllers.TaskController;
import models.Task;
import services.impl.DbTaskRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@WebServlet (urlPatterns = {"/tasks"})
public class TaskManagerHttpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    TaskController taskController;

    @Override
    public void init() throws ServletException {
        super.init();
        taskController = new TaskController(new DbTaskRepositoryImpl());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<Integer, Task> taskMap = taskController.getTaskList();

        List<Task> taskList = new ArrayList<Task>(taskMap.values());

        request.setAttribute("taskList", taskList);
        request.setAttribute("errorString", "Test Error");
/*        System.out.println(taskMap);*/

        request.getRequestDispatcher("/AllTasks.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

    }
}