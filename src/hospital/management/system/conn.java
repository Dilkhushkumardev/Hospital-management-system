package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class conn {
    public Connection connection;
    public Statement statement;

    public conn(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_management_system", "root", "Dilkhush@30");
            statement = connection.createStatement();
        } catch (Exception e){
            System.err.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
