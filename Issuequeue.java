/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

/**
 *
 * @author Asus User
 */
public class Issuequeue {
    private String queuename;
    //private PriorityQueue<Issue> list=new PriorityQueue<>();
    private ArrayList<Issue> list=new ArrayList<>();
    
    public Issuequeue() {
    }
    
    Issuequeue(String queuename){
        this.queuename=queuename;
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText, String cretor_user, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,priority,status);
        //return list.offer(obj);
        return list.add(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText, String cretor_user, String assignee_user, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,assignee_user,priority,status);
        //return list.offer(obj);
         return list.add(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText, String cretor_user, Integer priority,String tag, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,priority,tag,status);
        //return list.offer(obj);
         return list.add(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText,String cretor_user, ArrayList comment, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,comment,priority,status);
        //return list.offer(obj);
         return list.add(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText, String cretor_user, String assignee_user, ArrayList comment, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,assignee_user,comment,priority,status);
        //return list.offer(obj);
         return list.add(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText, String cretor_user, String assignee_user, String tag, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,assignee_user,tag,priority,status);
        //return list.offer(obj);
         return list.add(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText,  String cretor_user, ArrayList comment, String tag, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,comment,tag,priority,status);
        //return list.offer(obj);
        return list.add(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText,  String cretor_user, String assignee_user, ArrayList comment, String tag, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,assignee_user,comment,tag,priority,status);
        //return list.offer(obj);
         return list.add(obj);
    }
    
    public boolean offer(String issueTitle, String descriptionText,  String cretor_user, String assignee_user, ArrayList comment, String tag, Integer priority, String status){
        Issue obj=new Issue(issueTitle,descriptionText,cretor_user,assignee_user,comment,tag,priority,status);
        //return list.offer(obj);
         return list.add(obj);
    }
    
    public void offer(Issue a){
        //list.offer(a);
        list.add(a);
    }
    public boolean remove(Issue a){
        return list.remove(a);
    }
    
    public Issue peek(){
        //return list.peek();
        return list.get(0);
    }
    
    public Issue poll(){
        return list.remove(0);
    }
    
    public void clear(){
        list.clear();
    }
    
    public int getSize(){
        return list.size();
    }
    
    public boolean contains(Issue a){
        return list.contains(a);
    }
    public boolean isEmpty(){
        return list.isEmpty();
        
    }
    
    public void display(){
            int i=1;
            int index=0;
            System.out.println("Issue board");
            System.out.printf("%-15s%-25s%-15s%-15s%-15s%-35s%-15s%-15s\n","ID","Title","Status","Tag","Priority","Time","Assignee","CreatedBy");
            if(list.size()==0){
                System.out.printf("%-15s%-25s%-15s%-15s%-15s%-35s%-15s%-15s\n","-","-","-","-","-","-","-","-");
            }else{
                while(index<list.size()){    
                    Issue temp = list.get(index);
                    temp.setIssueNo(i);
                    System.out.printf("%-15s%-25s%-15s%-15s%-15s%-35s%-15s%-15s\n",temp.getIssueNo(),temp.getIssueTitle(),temp.getStatus(),temp.getTag(),temp.getPriority(),temp.changeDateFormat(),temp.getAssignee_user(),temp.getCretor_user());
                    i++;
                    index++;
                }
            }
        } 
    
    public void display(int issueNum){
        int index=0;
        while(index<list.size()){
            Issue temp=list.get(index);
            if(temp.getIssueNo()==issueNum){
                System.out.println("Issue ID: "+temp.getIssueNo() + "\tStatus: "+temp.getStatus());
                System.out.println("Tag: "+temp.getTag()+"\tPriority: "+temp.getPriority()+"\tCreated On: "+temp.changeDateFormat());
                System.out.println(temp.getIssueTitle());
                System.out.println("Assigned to: "+temp.getAssignee_user()+"\tCreated By: "+temp.getCretor_user());
                System.out.println("\nIssue Description");
                System.out.println("-----------------");
                System.out.println(temp.getDescriptionText());
                System.out.println("\nComments");
                System.out.println("---------");
                System.out.println(temp.getComment().toString());
            }
        index++;
        }
    }
}
    

