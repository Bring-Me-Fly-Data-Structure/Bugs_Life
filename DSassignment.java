/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsassignment;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author User
 */
public class DSassignment {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //array list of project name
        ArrayList <String> projectName = new ArrayList<String>() {
            {
                add("Table Widget");
                add("The Coding");
                add("Whistler");
                add("Coding Region");
                add("Swicth & Swift");
                add("Debug Entity");
                add("Hack Inversion");
                add("App Monster");
                add("Zip Demons");
                add("Explode Legacy");
            }
        };
        
        //System.out.println("Enter new project name: ");
        //String name= sc.nextLine();
        
        
         ArrayList <String> issueTitle = new ArrayList<String>(){
            {
                add(" Can't display the table    ");
                add(" Can't open file            ");
                add(" Can't access to the website");
                add(" Calculation error          ");
                add(" File not found             ");
            }
        };
       
        ProjectDashboard board = new ProjectDashboard(projectName);
      
        //board.addProject(name);
        //board.removeProject("The Coding");
        
        System.out.println("The project can be sorted by alphanumerically or by project ID");
        System.out.println("Option: 1.sort by alphanumerically  2.sort by project ID");
        System.out.print("Your option: ");
        int option= sc.nextInt();
        
        if(option==1){         
           board.sortAplhanumerically(projectName);
        }else{
            board.sortProjectID(projectName);   //havent complete
        }
                 
        System.out.print("Enter selected issue ID to check project: ");
        int id=sc.nextInt();
        }
}
       
  
      


