/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavenproject3;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author Asus User
 */
public class Issuequeue {

    private String queuename;
    //private PriorityQueue<Issue> list=new PriorityQueue<>();
    private ArrayList<Issue> list = new ArrayList<>();

    public Issuequeue() {
    }

    Issuequeue(String queuename) {
        this.queuename = queuename;
    }

    public boolean offer( String title, Integer priority,  ArrayList<String> tag, String descriptionText, String createdBy, String assignee, ArrayList<Comment> comments) {
        Issue obj = new Issue( title, priority, tag, descriptionText, createdBy, assignee, comments);
        //return list.offer(obj);
        return list.add(obj);
    }

    public void offer(Issue a) {
        //list.offer(a);
        list.add(a);
    }

    //storing data.json issue 
    public void offer(List<Issue> a) {
        //list.offer(a);
        for (int i = 0; i < a.size(); i++) {
            list.add(a.get(i));
        }
    }

    public boolean remove(Issue a) {
        return list.remove(a);
    }

    public Issue peek() {
        //return list.peek();
        return list.get(0);
    }

    public Issue poll() {
        return list.remove(0);
    }

    public void clear() {
        list.clear();
    }

    public int getSize() {
        return list.size();
    }

    public boolean contains(Issue a) {
        return list.contains(a);
    }

    public boolean isEmpty() {
        return list.isEmpty();

    }

    public void display() {
        int index = 0;
        System.out.println("Issue board");
        System.out.println("+------+-------------------------------------------+----------------+-------------------------------------+--------------+-------------------+----------------------+---------------------+");
        System.out.printf("%1s%3s%4s%21s%23s%11s%6s%20s%18s%11s%4s%11s%9s%15s%8s%15s%7s", "|", "ID", "|", "Title", "|", " Status", "|", "    Tag", "|", "Priority", "|", "Time", "|", "Assignee", "|", "CreatedBy", "|");
        System.out.println("\n+------+-------------------------------------------+----------------+-------------------------------------+--------------+-------------------+----------------------+---------------------+");
        if (list.size() == 0) {
            System.out.printf("%-3s%-4s%-4s%-40s%-4s%-13s%-4s%-34s%-10s%-5s%-3s%-17s%-2s%-21s%-2s%-20s%-3s", "|", "-", "|", "-", "|", "-", "|", "-", "|", "-", "|", "-", "|", "-", "|", "-", "|");
        } else {
            while (index < list.size()) {
                Issue temp = list.get(index);
                System.out.printf("%-3s%-4s%-4s%-40s%-4s%-13s%-4s%-34s%-10s%-5s%-3s%-17s%-2s%-21s%-2s%-20s%-3s", "|", temp.getId(), "|", temp.getTitle(), "|", temp.getStatus(), "|", temp.getTag(), "|", temp.getPriority(), "|", temp.changeDateFormat(), "|", temp.getAssignee(), "|", temp.getCreatedBy(), "|");
                index++;
                System.out.println("\n+------+-------------------------------------------+----------------+-------------------------------------+--------------+-------------------+----------------------+---------------------+");
            }
        }
    }

    public void displayIssueDetails(int issueNum) throws IOException {

        int index = 0;
        while (index < list.size()) {
            Issue temp = list.get(index);
            if (temp.getId() == issueNum) {
                if (User.loginName.equals(temp.getAssignee())) {
                    Issue.setIssueStatus("In Progress");
                }
                System.out.println("Issue ID: " + temp.getId() + "\tStatus: " + temp.getStatus());
                System.out.println("Tag: " + temp.getTag() + "\tPriority: " + temp.getPriority() + "\tCreated On: " + temp.changeDateFormat());
                System.out.println(temp.getTitle());
                System.out.println("Assigned to: " + temp.getAssignee() + "\tCreated By: " + temp.getCreatedBy());
                System.out.println("\nIssue Description");
                System.out.println("-----------------");
                System.out.println(temp.getDescriptionText());
                System.out.println("\nComments");
                System.out.println("---------");
                //System.out.println(temp.getComments());
                for (int i = 0; i < temp.getComments().size(); i++) {
                    
                    System.out.print("#"+(i+1));
                    System.out.println(temp.getComments().get(i));
                   // System.out.println(temp.getComments().get(i).getReact());
                }

                Scanner in = new Scanner(System.in);
                if (User.getLoginName().equals(temp.getAssignee()) || User.getLoginName().equals(temp.getCreatedBy())) {
                    System.out.println("Enter\n'r' to react\nor 'c' to comment\nor 'b' to issue dashboard");
                    System.out.println("or 's' to change status \nor 'e' to edit the issue ");

                    String input = in.next();
                    switch (input) {
                        case "r":
                            React.addReact();
                           // displayIssueDetails(issueNum);
                            break;
                        case "c":
                            Comment.addComment();
                            //displayIssueDetails(issueNum);
                            break;
                        case "b":
                            //Issue.displayIssueBoard();
                            break;
                        case "s":
                            if (User.getLoginName().equals(temp.getAssignee())) {
                                System.out.println("Enter '1' to Open, '2' to Close, '3' to Resolved");
                                int a = in.nextInt();
                                switch (a) {
                                    case 1:
                                        Issue.setIssueStatus("Open");
                                        break;
                                    case 2:
                                        Issue.setIssueStatus("Close");
                                        break;
                                    case 3:
                                        Issue.setIssueStatus("Resolved");
                                        break;
                                }
                                 //Issue.displayIssueBoard();
                            } else if (User.getLoginName().equals(temp.getCreatedBy())) {
                                System.out.println("Enter '1' to Open, '2' to Close");
                                int a = in.nextInt();
                                switch (a) {
                                    case 1:
                                        Issue.setIssueStatus("Open");
                                        break;
                                    case 2:
                                        Issue.setIssueStatus("Close");
                                        break;
                                }
                                 //Issue.displayIssueBoard();
                            }break;
                            
                        case "e":
                            System.out.println("Which part you want to edit ? ");
                            System.out.println("Enter '1'--Title '2'--Description '3'--Assignee Name '4'--Priority '5--Tag'");
                            int editoption=in.nextInt();
                            in.nextLine();
                            switch(editoption){
                                case 1:
                                    System.out.println("Enter new title : ");
                                    String newTitle=in.nextLine();
                                    Issue.setNewIssueTitle(newTitle);
                                    break;
                                case 2:
                                    String newDescription="";
                                    System.out.println("Enter new description text : ");
                                    UndoRedoStack<String> a = new UndoRedoStack<>();
                                    while (in.hasNext()) {
                                        String s1 = in.nextLine();
                                        if (s1.equals("end")) {
                                            break;
                                        } else if (s1.equals("undo")) {
                                            a.undo();
                                            System.out.println(a);
                                        } else if (s1.equals("redo")) {
                                            a.redo();
                                            System.out.println(a);
                                        } else {
                                            a.push(s1);
                                            System.out.println(a);
                                        }

                                    }
                                    for (int i = 0; i < a.size(); i++) {
                                        newDescription = newDescription +a.get(i)+"\n";
                                    }
                                    System.out.println(newDescription);
                                    Issue.setNewDescription(newDescription);
                                    break;
                                case 3:
                                    System.out.println("Enter new assignee name : ");
                                    String newAssignee=in.nextLine();
                                    Issue.setNewAssignee(newAssignee);
                                    break;
                                case 4:
                                    System.out.println("Enter new priority : ");
                                    int newPriority=in.nextInt();
                                    Issue.setNewPriority(newPriority);
                                    break;
                                case 5:
                                    ArrayList<String> tag = new ArrayList<>();
                                    System.out.println("Enter new tag : ");
                                    String newtag=in.nextLine();
                                    tag.add(newtag);
                                    Issue.setNewTag(tag);
                                    break;
                                    
                            }
                    }
                    Issue.displayIssueBoard();
                }
                else if (User.getLoginName().equals(temp.getAssignee()) || User.getLoginName().equals(temp.getCreatedBy())) {
                    System.out.println("Enter\n'r' to react\nor 'c' to comment\nor 'b' to issue dashboard");
                    System.out.println("or 's' to change status: ");
                    String input = in.next();
                    switch (input) {
                        case "r":
                            React.addReact();
                           // displayIssueDetails(issueNum);
                            break;
                        case "c":
                            Comment.addComment();
                            //displayIssueDetails(issueNum);
                            break;
                        case "b":
                            //Issue.displayIssueBoard();
                            break;
                        case "s":
                            if (User.getLoginName().equals(temp.getAssignee())) {
                                System.out.println("Enter '1' to Open, '2' to Close, '3' to Resolved");
                                int a = in.nextInt();
                                switch (a) {
                                    case 1:
                                        Issue.setIssueStatus("Open");
                                        break;
                                    case 2:
                                        Issue.setIssueStatus("Close");
                                        break;
                                    case 3:
                                        Issue.setIssueStatus("Resolved");
                                        break;
                                }
                                 //Issue.displayIssueBoard();
                            } else if (User.getLoginName().equals(temp.getCreatedBy())) {
                                System.out.println("Enter '1' to Open, '2' to Close");
                                int a = in.nextInt();
                                switch (a) {
                                    case 1:
                                        Issue.setIssueStatus("Open");
                                        break;
                                    case 2:
                                        Issue.setIssueStatus("Close");
                                        break;
                                }
                                 //Issue.displayIssueBoard();
                            }
                    }
                    Issue.displayIssueBoard();
                }else {
                    System.out.println("Enter\n'r' to react\nor 'c' to comment\nor 'b' to issue dashboard");
                    String input = in.next();
                    switch (input) {
                        case "r":
                            React.addReact();
                            //displayIssueDetails(issueNum);
                            break;
                        case "c":
                            Comment.addComment();
                            //displayIssueDetails(issueNum);
                            break;
                        case "b":
                            //Issue.displayIssueBoard();
                            break;
                    }
                    Issue.displayIssueBoard();
                }
                break;
            }
            index++;
        }
//
//        System.out.println("Enter\n'r' to react\nor 'c' to comment\nor 'b' to issue dashboard");
//        System.out.println("or 's' to change status: ");
//        Scanner in = new Scanner(System.in);
//        String input = in.next();
////        if (input.equals("y")) {
////            Comment.addComment();
////        }
//        switch (input) {
//            case "r":
//                React.addReact();
//                break;
//            case "c":
//                Comment.addComment();
//                break;
//            case "b":
//                Issue.displayIssueBoard();
//                break;
//            case "s":
//              if(User.getLoginName().equals(temp.getAssignee()))
//        }

    }

}
