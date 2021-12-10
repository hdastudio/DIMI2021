package utils;

import java.sql.*;

public class SQLHelper {

    private final String url = "jdbc:mysql://sql.edu-netcracker.com";
    private final String user = "TLT_24";
    private final String password = "TLT_24";

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public void connectToDB(){
        String query = "select * from task_list";
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int count = resultSet.getInt(1);
            }
        }catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }finally {
            try{connection.close();}catch (SQLException se){};
            try{statement.close();}catch (SQLException se){};
            try{resultSet.close();}catch (SQLException se){};
        }
    }
}
