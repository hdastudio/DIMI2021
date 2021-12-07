package edu.nc.tasks.controllers;

import java.sql.*;

/**
 * The database controller
 */
public class DBManager {
    private String address;
    private String port;
    private String username;
    private String password;
    private String SID;
    private String driver = "jdbc:oracle:thin";

    private Connection con;
    private Statement stat;

    /**
     * Instantiates a new database manager
     *
     * @param address  the address
     * @param port     the port
     * @param SID      the sid
     * @param username the username
     * @param password the password
     */
    public DBManager (String address, String port, String SID,
                      String username, String password) {

        this.address = address;
        this.port = port;
        this.username = username;
        this.password = password;
        this.SID = SID;

        return;
    }

    /**
     * Instantiates a new database manager
     *
     * @param address  the address
     * @param port     the port
     * @param SID      the sid
     * @param username the username
     * @param password the password
     * @param driver   the driver
     */
    public DBManager (String address, String port, String SID,
                      String username, String password, String driver) {

        this.address = address;
        this.port = port;
        this.username = username;
        this.password = password;
        this.SID = SID;
        this.driver = driver;

        return;
    }

    //destructor (deprecated?)
    protected void finalize() throws SQLException {

        this.con.close();

        return;
    }

    /**
     * Connects to the database
     *
     * @throws SQLException if a database access error occurs
     */
    public void connect() throws SQLException{

        String url = driver + ":@" + address + ":" + port + ":" + SID;
        this.con = DriverManager.getConnection(url, username, password);
        this.stat = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        return;
    }

    /**
     * Executes an SQL query
     *
     * @param q the query
     * @return the ResultSet object containing the results
     *         of the query
     * @throws SQLException if a database access error occurs
     */
    public ResultSet executeQuery(String q) throws SQLException {

        return stat.executeQuery(q);
    }

    /**
     * Gets all tasks from the database
     *
     * @return the ResultSet object containing all the
     *         rows in the database
     * @throws SQLException if a database access error occurs
     */
    public ResultSet getAllTasks() throws SQLException {

        return stat.executeQuery("select * from sss_tasks");
    }

    /**
     * Checks if the specified row exists
     * in the database
     *
     * @param row the id of the row
     * @return true if the row exists;
     *         false if the row doesn't exist
     * @throws SQLException if a database access error occurs
     */
    public boolean rowExists(int row) throws SQLException {

        ResultSet rs = stat.executeQuery("select * from sss_tasks where idnum = " + row);
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Inserts a row into the database
     *
     * @param key  the id of the row
     * @param task the task
     * @throws SQLException if a database access error occurs
     */
    public void insert(int key, String task) throws SQLException {

        String qs = "insert into sss_tasks values(?, ?)";
        PreparedStatement q = con.prepareStatement(qs);
        q.setInt(1, key);
        q.setString(2, task);

        q.executeUpdate();

        return;
    }

    /**
     * Updates the specified row in the database
     *
     * @param key  the id of the row
     * @param task the task
     * @throws SQLException if a database access error occurs
     */
    public void update(int key, String task)  throws SQLException {

        String qs = "update sss_tasks set task=? where idnum=?";
        PreparedStatement q = con.prepareStatement(qs);
        q.setString(1, task);
        q.setInt(2, key);

        q.executeUpdate();

        return;
    }

    /**
     * Deletes the specified row from the database
     *
     * @param key the id of the row
     * @throws SQLException if a database access error occurs
     */
    public void delete(int key) throws SQLException {

        String qs = "delete from sss_tasks where idnum=?";
        PreparedStatement q = con.prepareStatement(qs);
        q.setInt(1, key);

        q.executeUpdate();

        return;
    }

}
