/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Issue;

import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

/**
 *
 * @author Asus User
 */
public class Issuequeue {
    private String queuename;
    private PriorityQueue<Issue> list=new PriorityQueue<>();

    public Issuequeue() {
    }
    
    
    Issuequeue(String queuename){
        this.queuename=queuename;
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText, String cretor_user, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,priority,status);
        return list.offer(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText, String cretor_user, String assignee_user, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,assignee_user,priority,status);
        return list.offer(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText, String cretor_user, Integer priority,String tag, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,priority,tag,status);
        return list.offer(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText,String cretor_user, ArrayList comment, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,comment,priority,status);
        return list.offer(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText, String cretor_user, String assignee_user, ArrayList comment, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,assignee_user,comment,priority,status);
        return list.offer(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText, String cretor_user, String assignee_user, String tag, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,assignee_user,tag,priority,status);
        return list.offer(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText,  String cretor_user, ArrayList comment, String tag, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,comment,tag,priority,status);
        return list.offer(obj);
    }
    
    public boolean offer(int issueNo, String issueTitle, String descriptionText,  String cretor_user, String assignee_user, ArrayList comment, String tag, Integer priority, String status){
        Issue obj=new Issue(issueNo,issueTitle,descriptionText,cretor_user,assignee_user,comment,tag,priority,status);
        return list.offer(obj);
    }
    
    public void offer(Issue a){
        list.offer(a);
    }
    public boolean remove(Issue a){
        return list.remove(a);
    }
    
    public Issue peek(){
        return list.peek();
    }
    
    public Issue poll(){
        return list.poll();
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
//        System.out.println("----" + queuename + "----");
//          if (list.isEmpty()) {
//            System.out.println("Empty stack");
//        } else {
//            for (int i =0;i<list.size()-1; i--) {
            while(!list.isEmpty()){    
                Issue temp = list.poll();
                System.out.print(temp.getIssueNo() + " ");
                System.out.print(temp.getIssueTitle() + " ");
                System.out.print(temp.getDescriptionText()+ " ");
                System.out.print(temp.getTimestamp()+" ");
                System.out.print(temp.getCretor_user()+" ");
                System.out.print(temp.getAssignee_user()+" ");
                System.out.print(temp.getComment()+" ");
                System.out.print(temp.getTag()+" ");
                System.out.print(temp.getPriority()+" ");
                System.out.println(temp.getStatus()+" ");
                System.out.println();
//    private int issueNo;
//    private String issueTitle;
//    private String descriptionText;
//    private java.util.Date timestamp;
//    private String cretor_user;
//    private String assignee_user;
//    private ArrayList comment;
//    private String tag;
//    private Integer priority;
//    private String status;
            }
        } 
    }
    

