import java.sql.*;
import java.util.Map;
import java.util.Scanner;

public class SQLManager {
    Scanner scanSQL = new Scanner(System.in);
    TaskController taskController = new TaskController();
    static final String url = "jdbc:oracle:thin:@sql.edu-netcracker.com:1251:xe";
    static final String username = "TLT_4";
    static final String password = "TLT_4";


    static public void writeSQL(Map<Integer, Task> taskList) {
        try{
            Class.forName("oracle.jdbc.OracleDriver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                for (Integer key : taskList.keySet()){
                    Task task = taskList.get(key);
                    String sql = "INSERT INTO lab2(id,name,description) VALUES (?,?,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.setInt (1,task.getId());
                    preparedStatement.setString (2,task.getName());
                    preparedStatement.setString (3,task.getDescription());
                    int rows = preparedStatement.executeUpdate();
                    System.out.printf("Added %d rows", rows);
                }


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

                    int num = resultSet.getInt(4);
                    String id = resultSet.getString(1);
                    String name = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    System.out.printf("Номер записи в таблице: %d \nНомер записи: %s \nИмя записи: %s \nОписание: %s\n \n",num ,id, name, description);
                }
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }


    public void deleteSQL() {

        System.out.println("Введиде числовой номер записи из бд для удаления данных");
        String num = scanSQL.next();
        try{
            Class.forName("oracle.jdbc.OracleDriver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                String sql = "DELETE FROM lab2 WHERE num = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString (1, num);


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
