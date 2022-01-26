package edu.nc.tasks.storages;

import edu.nc.tasks.models.Task;
import edu.nc.tasks.utils.JDBCException;
import edu.nc.tasks.utils.StorageException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage implements Storage<Task, Integer> {
    private String address = "sql.edu-netcracker.com";
    private String port = "1251";
    private String username = "TLT_25";
    private String password = "TLT_25";
    private String SID = "xe";
    private String driver = "jdbc:oracle:thin";

    private String table = "temp_user";

    private Connection con;
    private Statement stat;

    public TaskStorage() {}

    public void connect() throws StorageException {
        String url = driver + ":@" + address + ":" + port + ":" + SID;

        try {
            this.con = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new JDBCException("Couldn't connect to the database");
        }
        try {
            this.stat = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (SQLException e) {
            throw new JDBCException("Couldn't create a statement");
        }

        return;
    }

    public void disconnect() throws StorageException {
        try {
            con.close();
        } catch (SQLException e) {
            throw new JDBCException("Couldn't disconnect from the database");
        }

        return;
    }

    public void insert(Task task) throws StorageException {
        this.connect();

        String qs;
        PreparedStatement q;
        try {
            qs = "insert into " + table + " values(?, ?)";
            q = con.prepareStatement(qs);
            q.setInt(1, task.getId());
            q.setString(2, task.getTask());
        } catch (SQLException e) {
            throw new JDBCException("Couldn't assemble statement");
        }
        try {
            q.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException("Couldn't insert task '" + task.getTask() + "'");
        }

        this.disconnect();

        return;
    }

    public void update(Task task) throws StorageException {
        this.connect();

        String qs;
        PreparedStatement q;
        try {
            qs = "update " + table + " set task=? where idnum=?";
            q = con.prepareStatement(qs);
            q.setString(1, task.getTask());
            q.setInt(2, task.getId());
        } catch (SQLException e) {
            throw new JDBCException("Couldn't assemble statement");
        }
        try {
            q.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException("Couldn't update task to '" + task.getTask() + "'");
        }

        this.disconnect();

        return;
    }

    public void delete(Integer id) throws StorageException {
        this.connect();

        String qs;
        PreparedStatement q;
        try {
            qs = "delete from " + table + " where idnum=?";
            q = con.prepareStatement(qs);
            q.setInt(1, id);
        } catch (SQLException e) {
            throw new JDBCException("Couldn't assemble statement");
        }
        try {
            q.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException("Couldn't delete task with id = " + id);
        }

        this.disconnect();

        return;
    }

    public boolean exists(Integer id) throws StorageException {
        this.connect();

        String qs;
        PreparedStatement q;
        ResultSet task;
        try {
            qs = "select * from " + table + " where idnum=?";
            q = con.prepareStatement(qs);
            q.setInt(1, id);
        } catch (SQLException e) {
            throw new JDBCException("Couldn't assemble statement");
        }
        try {
            task = q.executeQuery();

            if (task.next()) {
                this.disconnect();
                return true;
            } else {
                this.disconnect();
                return false;
            }
        } catch (SQLException e) {
            throw new StorageException("Couldn't get task with id = " + id);
        }
    }

    public Task get(Integer id) throws StorageException {
        this.connect();

        String qs;
        PreparedStatement q;
        ResultSet task;
        Task res;
        try {
            qs = "select * from " + table + " where idnum=?";
            q = con.prepareStatement(qs);
            q.setInt(1, id);
        } catch (SQLException e) {
            throw new StorageException("Couldn't assemble statement");
        }
        try {
            task = q.executeQuery();
        } catch (SQLException e) {
            throw new StorageException("Couldn't get task with id = " + id);
        }
        try {
            res = new Task(task.getInt(1), task.getString(2));
        } catch (SQLException e) {
            throw new StorageException("Couldn't read returned task");
        }

        this.disconnect();

        return res;
    }

    public List<Task> getAll() throws StorageException {
        this.connect();

        ArrayList<Task> res = new ArrayList<>();
        ResultSet rs;
        try {
            rs = stat.executeQuery("select * from " + table);
        } catch (SQLException e) {
            throw new StorageException("Couldn't get all tasks");
        }
        try {
            while(rs.next()) {
                res.add(new Task(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new StorageException("Couldn't convert tasklist to List");
        }

        this.disconnect();

        return res;
    }
}
