package Utils;

import java.io.IOException;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;

public class DBConnectionUtil {
    public static Connection getConnection() throws SQLException, IOException {

        String url = "jdbc:oracle:thin:@//sql.edu-netcracker.com:1251/xe";
        String username = "TLT_25";
        String password = "TLT_25";
        String driverName = "oracle.jdbc.OracleDriver";
        try {
            TimeZone timeZone = TimeZone.getTimeZone("Europe/Moscow");
            TimeZone.setDefault(timeZone);
            Class.forName(driverName);
            return DriverManager.getConnection(url, username, password);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

}



