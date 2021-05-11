/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue;
//hello
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.PriorityQueue;

/**
 *
 * @author Asus User
 */
public class Issue implements Comparable<Issue> {
    private int issueNo;
    private String issueTitle;
    private String descriptionText;
    private java.util.Date timestamp;
    private String cretor_user;
    private String assignee_user;
    private ArrayList comment;
    private String tag;
    private Integer priority;
    private String status;

    public Issue(int issueNo, String issueTitle, String descriptionText, String cretor_user, Integer priority, String status) {
        this.issueNo = issueNo;
        this.issueTitle = issueTitle;
        this.descriptionText = descriptionText;
        this.timestamp = timestamp;
        this.cretor_user = cretor_user;
        this.priority = priority;
        this.status = status;
        this.assignee_user=null;
        this.comment=null;
        this.tag=null;
    }

    public Issue(int issueNo, String issueTitle, String descriptionText, String cretor_user, String assignee_user, Integer priority, String status) {
        this.issueNo = issueNo;
        this.issueTitle = issueTitle;
        this.descriptionText = descriptionText;
        this.timestamp = new java.util.Date();
        this.cretor_user = cretor_user;
        this.assignee_user = assignee_user;
        this.priority = priority;
        this.status = status;
        this.comment=null;
        this.tag=null;
    }

    public Issue(int issueNo, String issueTitle, String descriptionText,String cretor_user, ArrayList comment, Integer priority, String status) {
        this.issueNo = issueNo;
        this.issueTitle = issueTitle;
        this.descriptionText = descriptionText;
        this.timestamp = new java.util.Date();
        this.cretor_user = cretor_user;
        this.comment = comment;
        this.priority = priority;
        this.status = status;
        this.assignee_user=null;
        this.tag=null;
    }

    public Issue(int issueNo, String issueTitle, String descriptionText, String cretor_user,Integer priority, String tag,String status) {
        this.issueNo = issueNo;
        this.issueTitle = issueTitle;
        this.descriptionText = descriptionText;
        this.timestamp = new java.util.Date();
        this.cretor_user = cretor_user;
        this.priority = priority;
        this.tag=tag;
        this.status = status;
        this.assignee_user=null;
        this.comment=null;
    }
    
    public Issue(int issueNo, String issueTitle, String descriptionText, String cretor_user, String assignee_user, ArrayList comment, Integer priority, String status) {
        this.issueNo = issueNo;
        this.issueTitle = issueTitle;
        this.descriptionText = descriptionText;
        this.timestamp = new java.util.Date();
        this.cretor_user = cretor_user;
        this.assignee_user = assignee_user;
        this.comment = comment;
        this.priority = priority;
        this.status = status;
        this.tag=null;
    }

    public Issue(int issueNo, String issueTitle, String descriptionText, String cretor_user, String assignee_user, String tag, Integer priority, String status) {
        this.issueNo = issueNo;
        this.issueTitle = issueTitle;
        this.descriptionText = descriptionText;
        this.timestamp = new java.util.Date();
        this.cretor_user = cretor_user;
        this.assignee_user = assignee_user;
        this.tag = tag;
        this.priority = priority;
        this.status = status;
        this.comment=null;
    }

    public Issue(int issueNo, String issueTitle, String descriptionText,  String cretor_user, ArrayList comment, String tag, Integer priority, String status) {
        this.issueNo = issueNo;
        this.issueTitle = issueTitle;
        this.descriptionText = descriptionText;
        this.timestamp = new java.util.Date();
        this.cretor_user = cretor_user;
        this.comment = comment;
        this.tag = tag;
        this.priority = priority;
        this.status = status;
        this.assignee_user=null;
    }

    public Issue(int issueNo, String issueTitle, String descriptionText,  String cretor_user, String assignee_user, ArrayList comment, String tag, Integer priority, String status) {
        this.issueNo = issueNo;
        this.issueTitle = issueTitle;
        this.descriptionText = descriptionText;
        this.timestamp = new java.util.Date();
        this.cretor_user = cretor_user;
        this.assignee_user = assignee_user;
        this.comment = comment;
        this.tag = tag;
        this.priority = priority;
        this.status = status;
    }
    
    public int getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(int issueNo) {
        this.issueNo = issueNo;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public String getCretor_user() {
        return cretor_user;
    }

    public void setCretor_user(String cretor_user) {
        this.cretor_user = cretor_user;
    }

    public String getAssignee_user() {
        return assignee_user;
    }

    public void setAssignee_user(String assignee_user) {
        this.assignee_user = assignee_user;
    }

    public ArrayList getComment() {
        return comment;
    }

    public void setComment(ArrayList comment) {
        this.comment = comment;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(Issue object) {
        if (this.getPriority() < object.getPriority()) {
            return 1;
        } else if (this.getPriority() > object.getPriority()) {
            return -1;
        } else {
            return 0;
        }
    }
    
    
    
}
