package Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Utils.DBConnectionUtil;
import model.Tasks;

public class DAOImpl implements DAO {
    Connection connection = null;
    ResultSet resultSet = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;

    @Override
    public List<Tasks> get() {

        List<Tasks> list = null;
        Tasks tasks = null;

        try {

            list = new ArrayList<Tasks>();
            String sql = "SELECT * FROM TASKS";
            connection = DBConnectionUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                tasks = new Tasks();
                tasks.setTaskId(resultSet.getInt("TASKID"));
                tasks.setTaskName(resultSet.getString("TASKNAME"));
                tasks.setTaskDescription(resultSet.getString("TASKDESCRIPTION"));
                list.add(tasks);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Tasks get(int id) {
        Tasks tasks = null;
        try {
            tasks = new Tasks();
            String sql = "SELECT * FROM TASKS where taskid=" + id;
            connection = DBConnectionUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                tasks.setTaskId(resultSet.getInt("TASKID"));
                tasks.setTaskName(resultSet.getString("TASKNAME"));
                tasks.setTaskDescription(resultSet.getString("TASKDESCRIPTION"));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    @Override
    public boolean save(Tasks t) {
        boolean flag = false;
        try {
            String sql = "INSERT INTO TASKS(TASKID, TASKNAME, TASKDESCRIPTION) VALUES (task_id_seq.nextval,?,?)";
            connection = DBConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, t.getTaskName());
            preparedStatement.setString(2, t.getTaskDescription());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean delete(int id) {
        boolean flag = false;
        try {
            String sql = "DELETE FROM TASKS where taskid=" + id;
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean update(Tasks tasks) {
        boolean flag = false;
        try {
            String sql = "UPDATE TASKS SET taskname = '" + tasks.getTaskName() + "', "
                    + "taskdescription = '" + tasks.getTaskDescription() + "' where taskid=" + tasks.getTaskId();
            connection = DBConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            flag = true;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

}
