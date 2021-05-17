
package dsassignment;

import java.util.ArrayList;
import java.util.Scanner;

public class DSassignment {
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        
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
        
        //havent implement priority queue for issues
         ArrayList <String> issueTitle = new ArrayList<String>(){
            {
               add(" Can't display the table    ");
               add(" Can't open file            ");
               add(" Flash of unstyled content  ");
               add(" User is trapped on 404 page");
            }
        };
       
        ProjectDashboard board = new ProjectDashboard(projectName);
        IssueDashboard board1 = new IssueDashboard(issueTitle);
      
        //board.addProject(name);
        //board.removeProject("The Coding");
        
        System.out.println("Welcome to project dashboard!");
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
        int issueID = sc.nextInt();
        board1.setIssueID(issueID);
        board1.display(issueTitle);
        if(board1.getIssueID()==0){
            System.out.println("Enter 'c' to create issue: ");
            String c= sc.next();
            sc.nextLine();
            //lead to one create issue method?
        }else{
            System.out.println("Enter selected issue ID to check issue");
            System.out.println("or 's' to search");
            System.out.println("or 'c' to create issue: ");
            String choice=sc.next();
            sc.nextLine();
            
            if(choice.equals("c")){
                //lead to one create issue method?    
            }else if(choice.equals("s")){
                board1.search(issueTitle);
            }else{
                
            }
        }
        
        //the issues can be sorted by priority or timestamp
        System.out.println("The issues can be sorted by priority or timestamps");
        System.out.println("Option: 1.sort by priority  2.sort by timestamp");
        System.out.print("Your option: ");
        int choice1=sc.nextInt();
        
        if(choice1==1){
            board1.compareToPriority(board1);
            //print method
        }else{
            board1.compareToTime(board1);
            //print method
        }
        
        System.out.println("Filter issues: ");
        System.out.println("Option: 1.filter by status  2.filter by tag");
        int choice2=sc.nextInt();
        
        if(choice2==1){
            board1.filterbyStatus();
            //print method
        }else{
            board1.filterbyTag();
            //print method
        }
        
      
    }

}
