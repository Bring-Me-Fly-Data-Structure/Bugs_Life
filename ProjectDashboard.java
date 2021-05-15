
package dsassignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProjectDashboard {
     Scanner sc = new Scanner(System.in);

    private ArrayList<String> projectName;
    private String issueID;
    
    public ProjectDashboard(){
        projectName = null;
    }
    
    public ProjectDashboard(ArrayList<String> pn){
        projectName = pn;
    }

    public ArrayList<String> getProjectName() {
        return projectName;
    }

    public void setProjectName(ArrayList<String> projectName) {
        this.projectName = projectName;
    }
    

    public void sortAplhanumerically(ArrayList<String> a) {
        Collections.sort(a);
        System.out.println("+------+----------------------+--------+");
        System.out.printf("%1s%3s%4s%15s%8s%6s%2s", "|","ID","|","Project Name", "|"," Issues","|");
        System.out.println();
        System.out.println("+------+----------------------+--------+");
        for (int i = 0; i < a.size(); i++) {
            System.out.printf("%1s%3s%4s%15s%8s%4s%5s", "|",(i+1),"|",a.get(i), "|","3","|");
            System.out.println();
            System.out.println("+------+----------------------+--------+");
        }
    }
    
    public void sortProjectID(ArrayList<String>a){
        //havent complete
        System.out.println("+------+----------------------+--------+");
        System.out.printf("%1s%3s%4s%15s%8s%6s%2s", "|","ID","|","Project Name", "|"," Issues","|");
        System.out.println();
        System.out.println("+------+----------------------+--------+");
        for (int i = 0; i < a.size(); i++) {
            System.out.printf("%1s%3s%4s%15s%8s%4s%5s", "|",(i+1),"|",a.get(i), "|","3","|");
            System.out.println();
            System.out.println("+------+----------------------+--------+");
        }
    }
    
    public boolean addProject(String str){
        if(projectName.add(str))
            return true;
        else
            return false;
    }
    
    public boolean removeProject(String str){
        if(projectName.remove(str))
            return true;
        else
            return false;
    }
    
    //sort by alphanumerically
    //number come first follow by alphabetical order
    /*public ArrayList <String> sortAscending(){
        Collections.sort(projectName);
        return projectName;
    }*/
    
    
}
