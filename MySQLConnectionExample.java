import java.sql.*;
public class MySQLConnectionExample {

    public static void main(String args[]) throws SQLException {
        Connection mysqlCon = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            mysqlCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/first", "admin", "swathi");
        } catch (Exception e) {
            System.out.println(e);
        }
  
}


}