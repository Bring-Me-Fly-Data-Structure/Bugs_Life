package mavenproject3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author richi
 */
public class adminlog {

    private static PreparedStatement pS;

    private static ResultSet result;
    
    public static void adminLogReport() {
        System.out.println("******Admin Log******");
                try {
            Connection adminlogSQL = new Connection();

            String searchAdminLOG = "SELECT * FROM `adminlog`";
            pS = adminlogSQL.getConnection().prepareStatement(searchAdminLOG);
            result = pS.executeQuery();
            while (result.next()) {
                String username = result.getString("username");
                String timestamp=result.getString("timestamp");
                String status=result.getString("status");
                String reason=result.getString("reason");
                
                System.out.println("Login/Register Time : "+timestamp+"\nUsername : "+username+"\nStatus : "+status+"\nReason : "+reason);;
                System.out.println("");
            }
            adminlogSQL.getConnection().close();
        } catch (SQLException ex) {
            System.out.println("myvideodisplay error");
        }
    }
    
    public static void main(String[] args) {
        adminLogReport();
    }
}