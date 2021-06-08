/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavenproject3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "changelog_id",
    "project_name",
    "project_id",
    "issue_name",
    "issue_id",
    "detail",
    "edit_time",
    "edittor"
})

public class changelog {

    @JsonProperty("changelog_id")
    private Integer changelogId;
    @JsonProperty("project_name")
    private String projectName;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("issue_name")
    private String issueName;
    @JsonProperty("issue_id")
    private Integer issueId;
    @JsonProperty("detail")
    private String detail;
    @JsonProperty("edit_time")
    private String editTime;
    @JsonProperty("edittor")
    private String edittor;

    public changelog() {
    }

    @JsonCreator
    public changelog(@JsonProperty("changelog_id") Integer changelogId, @JsonProperty("project_name") String projectName, @JsonProperty("project_id") Integer projectId, @JsonProperty("issue_name") String issueName, @JsonProperty("issue_id") Integer issueId, @JsonProperty("detail") String detail, @JsonProperty("edit_time") String editTime, @JsonProperty("edittor") String edittor) {
        this.changelogId = changelogId;
        this.projectName = projectName;
        this.projectId = projectId;
        this.issueName = issueName;
        this.issueId = issueId;
        this.detail = detail;
        this.editTime = editTime;
        this.edittor = edittor;
    }

    @JsonProperty("changelog_id")
    public Integer getChangelogId() {
        return changelogId;
    }

    @JsonProperty("changelog_id")
    public void setChangelogId(Integer changelogId) {
        this.changelogId = changelogId;
    }

    @JsonProperty("project_name")
    public String getProjectName() {
        return projectName;
    }

    @JsonProperty("project_name")
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @JsonProperty("project_id")
    public Integer getProjectId() {
        return projectId;
    }

    @JsonProperty("project_id")
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @JsonProperty("issue_name")
    public String getIssueName() {
        return issueName;
    }

    @JsonProperty("issue_name")
    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    @JsonProperty("issue_id")
    public Integer getIssueId() {
        return issueId;
    }

    @JsonProperty("issue_id")
    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    @JsonProperty("detail")
    public String getDetail() {
        return detail;
    }

    @JsonProperty("detail")
    public void setDetail(String detail) {
        this.detail = detail;
    }

    @JsonProperty("edit_time")
    public String getEditTime() {
        return editTime;
    }

    @JsonProperty("edit_time")
    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    @JsonProperty("edittor")
    public String getEdittor() {
        return edittor;
    }

    @JsonProperty("edittor")
    public void setEdittor(String edittor) {
        this.edittor = edittor;
    }
    @JsonIgnore
    private static PreparedStatement pS;
    @JsonIgnore
    private static ResultSet result;

    public static void viewChangelog() {
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

                System.out.println("Editted Time : " + edit_time + "\nEdittor : " + edittor + "\nProject Name : " + projectName + "   Project ID : " + projectID + "\nIssue Name : " + issueName + "   Issue ID : " + issueID + "\nDetail : \n" + detail);
                System.out.println("");
            }
            changelogSQL.getConnection().close();
        } catch (SQLException ex) {
            System.out.println("display changelog error");
        }
    }

}
