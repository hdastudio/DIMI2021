import java.sql.*;
import java.util.Map;
import java.util.Scanner;

public class SQLManager {
    Scanner scanSQL = new Scanner(System.in);
    ConsoleView consoleView;
    TaskController taskController = new TaskController();
    String url = "jdbc:oracle:thin:@sql.edu-netcracker.com:1251:xe";
    String username = "TLT_4";
    String password = "TLT_4";

    public void writeSQL(Map<Integer, Task> taskList) {
        try{
            Class.forName("oracle.jdbc.OracleDriver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "INSERT INTO lab2(text) VALUES (?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString (1, String.valueOf(taskController.taskList));

                int rows = preparedStatement.executeUpdate();
                System.out.printf("Added %d rows", rows);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }

    public void readSQL() {
        try{

            Class.forName("oracle.jdbc.OracleDriver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM lab2");
                while(resultSet.next()){

                    String text = resultSet.getString(1);
                    int id = resultSet.getInt(2);
                    System.out.printf("%s. %d \n",text ,id);
                }
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }


    public void deleteSQL() {

        System.out.println("Введиде числовой номер записи данных из бд для удаления");
        int num = scanSQL.nextInt();
        try{
            Class.forName("oracle.jdbc.OracleDriver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "DELETE FROM lab2 WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt (1, num);


                int rows = preparedStatement.executeUpdate();
                System.out.printf("%d row(s) deleted", rows);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
}
