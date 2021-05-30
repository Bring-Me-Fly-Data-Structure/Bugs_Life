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
 * @author richi
 */
public class changelog {

    private static PreparedStatement pS;

    private static ResultSet result;
    
    public static void main(String[] args) {
        System.out.println("******Change History******");
                try {
            Connection changelogSQL = new Connection();

            String searchChangeLOG = "SELECT * FROM `changelog`";
            pS = changelogSQL.getConnection().prepareStatement(searchChangeLOG);
            result = pS.executeQuery();
            while (result.next()) {
                String edit_time = result.getString("edit_time");
                String edittor = result.getString("edittor");
                String projectName = result.getString("project_name");
                int projectID = result.getInt("project_id");
                String issueName = result.getString("issue_name");
                int issueID = result.getInt("issue_id");
                String detail = result.getString("detail");
                
                System.out.println("Editted Time : "+edit_time+"\nEdittor : "+edittor+"\nProject Name : "+projectName+"   Project ID : "+projectID+"\nIssue Name : "+issueName+"   Issue ID : "+issueID+"\nDetail : \n"+detail);
                System.out.println("");
            }
            changelogSQL.getConnection().close();
        } catch (SQLException ex) {
            System.out.println("myvideodisplay error");
        }
    }
}
