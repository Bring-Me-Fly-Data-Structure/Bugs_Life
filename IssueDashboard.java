
package dsassignment;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class IssueDashboard {
    Scanner sc= new Scanner(System.in);
    
    private java.util.Date timestamp;
    private int issueID;
    private ArrayList<String> issueTitle;
    private ArrayList<String>projectName;
    private int priority;
    private String status;
    //String[]status={"Open","In progress", "Reopened","Resolved","Closed"};
    private String tag;
    private String assignee;
    private String creator;
    private String title;
    private String descriptiveText;
    private ArrayList comment;
    
     public IssueDashboard(){
        issueTitle = null;
    }
     

    public IssueDashboard(int issueID) {
        this.issueID = issueID;
    }

    public IssueDashboard(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }
   
    public IssueDashboard (ArrayList<String> issueT){
        issueTitle = issueT;
    }

    public ArrayList<String> getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(ArrayList<String> issueTitle) {
        this.issueTitle = issueTitle;
    }

    public int getIssueID() {
        return issueID;
    }

    public void setIssueID(int issueID) {
        this.issueID = issueID;
    }

    public int getPriority() {
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptiveText() {
        return descriptiveText;
    }

    public void setDescriptiveText(String descriptiveText) {
        this.descriptiveText = descriptiveText;
    }

    public ArrayList getComment() {
        return comment;
    }

    public void setComment(ArrayList comment) {
        this.comment = comment;
    }
    
    
    
    public int compareToPriority(IssueDashboard o){
        int result=0;
        if(this.getPriority()>o.getPriority()){
            result=1;
        }else if(this.getPriority()<o.getPriority()){
            result= -1;
        }else{
            result=0;
        }
        return result;
    }
    
    public int compareToTime(IssueDashboard o){
        int result=0;
        if(this.getTimestamp().compareTo(o.getTimestamp())>0){
            result=1;
        }else if (this.getTimestamp().compareTo(o.getTimestamp())>0){
            result = -1;
        }else{
            result=0;
        }
        return result;
    }
  
    public void display(){
        int i=1;
        System.out.println("Issue board");
        System.out.println("+------+----------------------------------+----------------+----------+-------------+-------------------+--------------+-------------+");
        System.out.printf("%1s%3s%4s%18s%17s%9s%8s%7s%4s%11s%3s%11s%9s%11s%4s%10s%4s", "|","ID","|","Title", "|"," Status","|","Tag","|","Priority","|","Time","|","Assignee","|","CreatedBy","|");
        System.out.println();
        System.out.println("+------+----------------------------------+----------------+----------+-------------+-------------------+--------------+-------------+");
    
        if(issueTitle.size()==0){
            System.out.printf("%1s%3s%4s%18s%17s%9s%8s%7s%4s%11s%3s%11s%9s%11s%4s%10s%4s", "|","-","|","-", "|","-","|","-","|","-","|","-","|","-","|","-","|");
        }else{
            if(i< issueTitle.size()){
             issueTitle.get(i);
             //this.setIssueID(i);
             System.out.printf("%1s%3s%4s%18s%7s%5s%6s%5s%3s%9s%5s%8s%12s%9s%6s%8s%6s","|",this.issueID,"|",this.getIssueTitle(),"|",this.getStatus(),"|",this.getTag(),"|",this.getPriority(),"|",this.getTimestamp(),"|",this.getAssignee(),"|",this.getCreator(),"|");
             i++;
            }
        System.out.println("\n+------+----------------------------------+----------------+----------+-------------+-------------------+--------------+-------------+");
        }
    }
    
    //investigating
    public void filterbyStatus(){
        
        if(this.getStatus().contains("Open")){
            //System.out.println("list of issue title that has open status");
        }
        
        if(this.getStatus().contains("In Progress")){
           //System.out.println("list of issue title that has in progress status");
        }
        
        if(this.getStatus().contains("Reopened")){
           //System.out.println("list of issue title that has reopened status”);
        }
        
        if(this.getStatus().contains("Resolved")){
           //System.out.println("list of issue title that has resolved status");
        }
        
        if(this.getStatus().contains("Closed")){
           //System.out.println("list of issue title that has closed status");
        }       
    }
    
    public void filterbyTag(){
        
        if(this.getTag().contains("Frontend")){
           //System.out.println("list of issue title that has frontend as tag); 
        }
        
        if(this.getTag().contains("Backend")){
           //System.out.println("list of issue title that has backend as tag);
        }
    }
    
    public boolean search(){
        System.out.print("Option: 1.Search by title  2.Search by descriptive text  3.Search by comment");
        int choice=sc.nextInt();
        
        if(choice==1){
            //investigating
        }else if (choice==2){
            //investigating
        }else if(choice==3){
           //investigating 
        }else{
            System.out.println("Results not found");
        }
        return true;
    }
    
    
}
