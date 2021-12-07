package edu.nc.tasks;

import edu.nc.tasks.controllers.DBManager;
import edu.nc.tasks.controllers.TasklistManager;
import edu.nc.tasks.models.Tasklist;
import edu.nc.tasks.views.ConsoleMenu;

import java.io.IOException;
import java.sql.*;

/**
 * The App class implements a console application
 * which allows the user to create, store, and modify a
 * simple list of tasks.
 * <p>
 * By default the list is loaded from and stored in an
 * .xml file, but the user can also choose to save the list
 * as a .json or a .csv file.
 *
 * @author Sigeeva Sofia
 * @version 1.1
 */
public class App {

    /**
     * The entry point of application.
     *
     * @param args input arguments
     */
    public static void main(String[] args) throws IOException, SQLException {

        //mvc components
        Tasklist tasks = new Tasklist();
        ConsoleMenu view = new ConsoleMenu();
        DBManager db = new DBManager("sql.edu-netcracker.com", "1251", "xe", "TLT_20", "TLT_20");
        TasklistManager controller = new TasklistManager(tasks, view, db);

        view.callMenu(controller);

        return;
    }
}