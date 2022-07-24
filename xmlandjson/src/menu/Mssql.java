package menu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mssql {
    public static Connection getMssqlConnection() throws SQLException{
        String dbURL = "jdbc:sqlsever://localhost:1433;databaseName=menu";
        String userName = "sa";
        String password = "sa";
        Connection conn = DriverManager.getConnection(dbURL,userName,password);
        return conn;
    }
    public static void main(String[] args) throws SQLException {
        if (getMssqlConnection() != null){
            System.out.println("ket noi thanh cong");
        }
    }
}