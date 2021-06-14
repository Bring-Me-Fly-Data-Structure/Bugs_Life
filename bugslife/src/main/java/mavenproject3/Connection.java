package mavenproject3;

import java.sql.*;

public class Connection {

    protected static java.sql.Connection con;

    public java.sql.Connection getConnection() {
        try {
            //    Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugs_life", "root", "");
            //Statement st = conn.createStatement();
            /*
            for debugging purpose, can be removed
            System.out.println("Connected to database in ConnectionMySQL.getConnection()");
             */
            return con;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
