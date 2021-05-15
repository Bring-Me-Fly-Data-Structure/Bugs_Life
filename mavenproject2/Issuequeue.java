/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

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

    public boolean offer(String title, Integer priority, String status, List<String> tag, String descriptionText, String createdBy, String assignee, ArrayList<Comment> comments) {
        Issue obj = new Issue(title, priority, status, tag, descriptionText, createdBy, assignee, comments);
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
        int i = 1;
        int index = 0;
        System.out.println("Issue board");
        System.out.printf("%-15s%-25s%-15s%-15s%-15s%-35s%-15s%-15s\n", "ID", "Title", "Status", "Tag", "Priority", "Time", "Assignee", "CreatedBy");
        if (list.size() == 0) {
            System.out.printf("%-15s%-25s%-15s%-15s%-15s%-35s%-15s%-15s\n", "-", "-", "-", "-", "-", "-", "-", "-");
        } else {
            while (index < list.size()) {
                Issue temp = list.get(index);
                temp.setId(i);
                System.out.printf("%-15s%-25s%-15s%-15s%-15s%-35s%-15s%-15s\n", temp.getId(), temp.getTitle(), temp.getStatus(), temp.getTag(), temp.getPriority(), temp.changeDateFormat(), temp.getAssignee(), temp.getCreatedBy());
                i++;
                index++;
            }
        }
    }

    public void display(int issueNum) {
        int index = 0;
        while (index < list.size()) {
            Issue temp = list.get(index);
            if (temp.getId() == issueNum) {
                System.out.println("Issue ID: " + temp.getId() + "\tStatus: " + temp.getStatus());
                System.out.println("Tag: " + temp.getTag() + "\tPriority: " + temp.getPriority() + "\tCreated On: " + temp.changeDateFormat());
                System.out.println(temp.getTitle());
                System.out.println("Assigned to: " + temp.getAssignee() + "\tCreated By: " + temp.getCreatedBy());
                System.out.println("\nIssue Description");
                System.out.println("-----------------");
                System.out.println(temp.getDescriptionText());
                System.out.println("\nComments");
                System.out.println("---------");
                System.out.println(temp.getComments().toString());
            }
            index++;
        }
    }
}
