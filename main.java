
package Issue;
import java.util.*;
public class main {

    public static void main(String args[]) {
       Scanner input=new Scanner(System.in);
       Issuequeue issue=new Issuequeue();
       ArrayList<String> comment=new ArrayList<>();
       String title="";
       String description="";
       String cname="";
       String aname="";
       String tag="";
       Integer priority=0;
       String status="";
       int count=1;
       
       System.out.println("Do u want create issue? Input c if u want.");
       String c=input.next();
     //System.out.println(c);
       input.nextLine();
       while(c.equals("c")){
           System.out.println("Enter Title : ");
           title=input.nextLine();
           
           //System.out.println(title);
           System.out.println("Enter description text : ");
           description=input.nextLine();
           //System.out.println(description);
           System.out.println("Enter cretor name : ");
           cname=input.nextLine();
           //System.out.println(cname);
           System.out.println("Do u want add assignee name ? input y if yes");
           String option=input.next();
           input.nextLine();
           //System.out.println(option);
           if(option.equals("Y")||option.equals("y")){
                System.out.println("Enter assignee name : ");
                aname=input.nextLine();
                System.out.println(aname);
            }
            System.out.println("Do u want add comment ? input y if yes");
            String option2=input.next();
            input.nextLine();
            //System.out.println(option2);
            if(option2.equals("Y")||option2.equals("y")){
                System.out.println("Add comment : ");
                String commentString=input.nextLine();
                //System.out.println(commentString);
                comment.add(commentString);
            }
            System.out.println("Do u want add tag ? input y if yes");
            String option3=input.next();
           // System.out.println(option3);
            input.nextLine();
            if(option2.equals("Y")||option2.equals("y")){
                System.out.println("Add tag : ");
                tag=input.nextLine();
             //   System.out.println(tag);
            }
            System.out.println("Add priority : ");
            priority=input.nextInt();
            input.nextLine();
            System.out.println("Add status : ");
            status=input.nextLine();
           // System.out.println(status);
            issue.offer(count,title,description,cname,aname,comment,tag,priority,status);
       
            aname="";
            tag="";
            status="";
            count++;
            System.out.println("Do u want create issue? Input c if u want.");
            c=input.next();      
            input.nextLine();
       }
       
       //issue.offer(1,"Title", "descriptionText","Ali", 2, "Open");
       //issue.offer(2, "Hi", "Nothing", "Abu", 4, "Closed");
       sortList(issue);
       Issuequeue priorityq=new Issuequeue();
       while(!issue.isEmpty()){
           priorityq.offer(issue.poll());
       }
       priorityq.display();
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
}
