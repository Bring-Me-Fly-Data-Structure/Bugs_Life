/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavenproject3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Asus User
 */
public class access_control {

    private static PreparedStatement pS;

    private static ResultSet result;
    
    public static void adminLogReport() {
        System.out.println("******Access Control List******");
                try {
            Connection access = new Connection();

            String searchAccess = "SELECT * FROM `access_control`";
            pS = access.getConnection().prepareStatement(searchAccess);
            result = pS.executeQuery();
            while (result.next()) {
                String creator = result.getString("creator");
                String timestamp=result.getString("timestamp");
                String issueTitle=result.getString("issueTitle");
                String edittor=result.getString("allowed_edittor");
                String viewer=result.getString("allowed_viewer");
                String changeStatus=result.getString("allowed_changeStatus");
                
                System.out.println("Issue Title : "+issueTitle+"\nCreator : "+creator+"\nCreated at : "+timestamp+"\nAllowed Edittor : "+edittor+"\nAllowed Viewer : "+viewer+"\nAllowed People to Change Status : "+changeStatus);
                System.out.println("");
            }
            access.getConnection().close();
        } catch (SQLException ex) {
            System.out.println("myvideodisplay error");
        }
    }
    
    public static void main(String[] args) {
        adminLogReport();
    }
}