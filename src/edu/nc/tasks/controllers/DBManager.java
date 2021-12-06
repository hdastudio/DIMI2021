package edu.nc.tasks.controllers;

import java.sql.*;

public class DBManager {
    private String address;
    private String port;
    private String username;
    private String password;
    private String SID;
    private String driver = "jdbc:oracle:thin";

    private Connection con;
    private Statement stat;

    public DBManager (String address, String port, String SID,
                      String username, String password) {

        this.address = address;
        this.port = port;
        this.username = username;
        this.password = password;
        this.SID = SID;
    }

    public DBManager (String address, String port, String SID,
                      String username, String password, String driver) {

        this.address = address;
        this.port = port;
        this.username = username;
        this.password = password;
        this.SID = SID;
        this.driver = driver;
    }

    protected void finalize() throws SQLException {
        this.con.close();
    }

    public void connect() throws SQLException{
        String url = driver + ":@" + address + ":" + port + ":" + SID;
        this.con = DriverManager.getConnection(url, username, password);
        this.stat = this.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        return;
    }

    public ResultSet executeQuery(String q) throws SQLException{
        return stat.executeQuery(q);
    }
}
