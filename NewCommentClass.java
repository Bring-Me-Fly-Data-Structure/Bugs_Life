/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sem.pkg2.assignment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Ker Xin
 */
public class NewCommentClass <T> extends User_Interface {
    private String Reaction,Comment_user;
    String time="",commentText="";
    int b=0,c=0,d=0,e=0,f=0,g=0,h=0, CommentID=1,cloneID=1;
  
    ArrayList<String> first=new ArrayList<>();
    Scanner s=new Scanner(System.in);

    //Every time a new comment is created, comment ID will increase
    public NewCommentClass() { 
        for (int i = 0; i < 50; i++) {
            System.out.print(" --- "); 
        } System.out.println("");
    }
       
    //User give comment
    public String giveComment(){
        System.out.println("Leave your comment here ");
        commentText=s.nextLine();
        
        //Store comment into arrayList
        first.add(commentText);
        return" ";
    }
     
    public void CommentID() {
        System.out.println("Comment ID: "+CommentID);
        this.CommentID++;
        this.cloneID=this.CommentID;
    }
       
    //Time and date when user comment
    public void Timestamp() {
        LocalDate myObj1 = LocalDate.now();
        LocalTime myObj2 = LocalTime.now();
        time=myObj1+"  "+myObj2;
    }
    
    //giveReaction
    public void printReaction(){
        System.out.println("List of emoji: like, smile, happy, sad, cry, angry, love");
        System.out.println("Enter emoji: ");
        String emoji=s.next();
        this.Reaction=emoji;
    }
    
    //Increase number of emoji
    public void noOfEmoji(){
        switch(this.Reaction){
            case "happy":
                b++;
                break;
            case "smile":
                c++;
                break;
            case "sad":
                d++;
                break;
            case "angry":
                e++;
                break;
            case "love":
                f++;
                break;
            case "cry":
                g++;
                break;
            case "like":
                h++;
                break;
            default:
                System.out.println(" ");
        }
    }  
    
     //Print emoji when creating comment
    //need to change output front to Segoe UI Symbol 19 in order to see the emoji
    public String emoji(){ 
        System.out.print(String.format("%c",  0x0001F44D)+"x"+h+"  ");
        System.out.print(String.format("%c", 0x0001F60A )+"x"+c+"  ");
        System.out.print(String.format("%c", 0x0001F601) + "x" + b+"  "); 
        System.out.print(String.format("%c", 0x0001F622 )+"x"+d +"  ");
        System.out.print(String.format("%c", 0x0001F62D)+"x"+g+"  ");
        System.out.print(String.format("%c", 0x0001F620 )+"x"+e+"  ");
        System.out.print(String.format("%c", 0x00002764 )+"x"+f+"  ");
        System.out.println("");
        return" ";
        }
        
    public void displayComment() {
        Timestamp();
        
        //print comment 
        System.out.print(user_name+"\t\t");
        System.out.println(time);
        System.out.println(this.commentText);
        emoji(); 
        System.out.println("");
    }
   
    public void giveReaction() {
        
        printReaction();
        noOfEmoji();
        
        int clone=this.cloneID-1;
        System.out.println("");
        System.out.println("Comment ID: " +clone);
        System.out.print(user_name+"\t\t");
        System.out.println(time);
        System.out.println(this.commentText);
        emoji();
        s.nextLine();
        System.out.println("");

    }
    
}