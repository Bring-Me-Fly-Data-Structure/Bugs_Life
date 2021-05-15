/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class main2 {

    public static void main(String args[]) {
       //declare variable
        Scanner input=new Scanner(System.in);
        
        //display issue board
        Issuequeue i=new Issuequeue();
//        i.offer(1,"Title", "descriptionText","Ali", 2, "Open");
//        i.offer(2, "Hi", "Nothing", "Abu", 4, "Closed");
        sortList(i);
        i.display();
        
        //action for issue board
        if(i.getSize()==0){
            System.out.println("Enter 'c' to create issue: ");
            String c=input.next();
            input.nextLine();
            if(c.equals("c")){
                i.offer(create(input));
            }
            System.out.println("--------------------------------------");
            System.out.println("Add issue successfully");
            sortList(i);
            i.display();
            System.out.println("Enter selected issue ID to check issue");
            int numIndex=input.nextInt();
            i.display(numIndex);
        }else{
            System.out.println("Enter selected issue ID to check issue \nor 's' to search \nor 'c' to create issue:");
            String option=input.next();
            input.nextLine();
            if(isInteger(option)){
                int numIndex=Integer.parseInt(option);
                i.display(numIndex);
            }else if(option.equals("s")){
                System.out.println("Sedang dikaji");
            }else if(option.equals("c")){
                i.offer(create(input));
                System.out.println("--------------------------------------");
                System.out.println("Add issue successfully");
                sortList(i);
                i.display();
            }
        }
        }
    
    public static Issuequeue sortList(Issuequeue list) {
        ArrayList<Issue> sortList = new ArrayList<>();
        while(!list.isEmpty()){
           sortList.add(list.poll());
        }
        Collections.sort(sortList);
        for (int i = 0; i < sortList.size(); i++) {
            list.offer(sortList.get(i));
        }
        return list;
    }
     
    public static Issue create(Scanner input){
       
        //Issuequeue i=new Issuequeue();
        ArrayList<Comment> comment=new ArrayList<>();
        String title="";
        String description="";
        String cname="";
        String aname="";
        ArrayList<String> tag=null;
        Integer priority=0;
        String status="";
        
            System.out.println("Enter Title : ");
            title=input.nextLine();
            System.out.println("Enter description text : ");
            UndoRedoStack<String> a=new UndoRedoStack<>();
            while(input.hasNext()){
                String s1=input.nextLine();
                if(s1.equals("end"))break;
                else if(s1.equals("undo")){
                    a.undo();
                    System.out.println(a);
                }
                else if(s1.equals("redo")){
                    a.redo();
                    System.out.println(a);
                }
                else{
                    a.push(s1);
                    System.out.println(a);
                }
           
            }
            for (int i = 0; i < a.size(); i++) {
                description=description+"\n"+a.get(i);
            }
            System.out.println(description);
            System.out.println("Enter cretor name : ");
            cname=input.nextLine();
            System.out.println("Do u want add assignee name ? input y if yes");
            String option=input.next();
            input.nextLine();
            if(option.equals("Y")||option.equals("y")){
                System.out.println("Enter assignee name : ");
                aname=input.nextLine();
            }
            System.out.println("Do u want add comment ? input y if yes");
            String option2=input.next();
            input.nextLine();
            if(option2.equals("Y")||option2.equals("y")){
                System.out.println("Add comment : ");
                String commentString=input.nextLine();
               // comment.add(new Comment());
                
            }
            System.out.println("Do u want add tag ? input y if yes");
            String option3=input.next();
            input.nextLine();
            if(option2.equals("Y")||option2.equals("y")){
                System.out.println("Add tag : ");
                tag.add(input.nextLine());
            }
            System.out.println("Add priority : ");
            priority=input.nextInt();
            input.nextLine();
            System.out.println("Add status : ");
            status=input.nextLine();

            Issue i=new Issue(title,priority,status,tag,description,cname,aname,comment);
            return i;
    }
    
    public static boolean isInteger( String input ) { 
        try { 
            Integer.parseInt( input ); 
            return true; 
        } 
        catch( Exception e ) {  
            return false;
        } 
    } 
}
