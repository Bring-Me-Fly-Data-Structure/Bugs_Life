/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavenproject3;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "reaction",
    "count"
})

@Entity
@Table(name = "reaction")
public class React2 implements Serializable {
     @JsonIgnore    // Create an EntityManagerFactory when you start the application
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateTest");
    
    @JsonIgnore
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "react_id")
    @JsonIgnore
    private int react_id;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;  
    
    @Column(name = "reaction")     
    @JsonProperty("reaction")
    private String reaction;
    
    @Column(name = "count")      
    @JsonProperty("count")
    private Integer count;

    public React2() {
    }

    public React2(String reaction, Integer count) {
        this.reaction = reaction;
        this.count = count;
    }

    @JsonProperty("reaction")
    public String getReaction() {
        return reaction;
    }

    @JsonProperty("reaction")
    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    public int getReact_id() {
        return react_id;
    }

    public void setReact_id(int react_id) {
        this.react_id = react_id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

//    public void reaction(String react,int commentID){
//        //read json file
//        //find comment id
//        //write reaction... >> json file
//    }
    @Override
    public String toString() {

        return "React{" + "reaction=" + getReaction() + ", count=" + getCount() + '}';
    }

    //method
    public static void addReact() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a comment ID to react: ");
        Comment.setCommentID(input.nextInt());
        String username = User.getLoginName();
        int reactionIndex = 0;
        input.nextLine();
        System.out.println("Which reaction you want to reaction ?");
        System.out.println("Enter '1'--angry '2'--happy '3'--thumb up '4'--smile '5'--sad '6'--love '7'--cry");
        int reaction = input.nextInt();
        switch (reaction) {
            case 1:
                reactionIndex = 0;
                break;
            case 2:
                reactionIndex = 1;
                break;
            case 3:
                reactionIndex = 2;
                break;

            case 4:
                reactionIndex = 3;
                break;
            case 5:
                reactionIndex = 4;
                break;
            case 6:
                reactionIndex = 5;
                break;
            case 7:
                reactionIndex = 6;
                break;
        }
//        ObjectMapper mapper = new ObjectMapper();
//        //read file
//        Example root = mapper.readValue(new File("C:\\Users\\richi\\Desktop\\UM folder\\Y1S2\\WIA1002 DS\\assignment\\localDatabase\\final.json"), Example.class);
//        //Update value in object
//        int count = root.getProjects().get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getComments().get(Comment.getCommentID() - 1).getReact().get(reactionIndex).getCount();
//        root.getProjects().get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getComments().get(Comment.getCommentID() - 1).getReact().get(reactionIndex).setCount(count + 1);
//        String json = mapper.writeValueAsString(root);
//
//        try (FileWriter file = new FileWriter("C:\\Users\\richi\\Desktop\\UM folder\\Y1S2\\WIA1002 DS\\assignment\\localDatabase\\final.json")) {
//
//            file.write(json);
//            System.out.println("Successfully updated json object to file...!!");
//        }

       EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        List<Project> projectList = new ArrayList<>();

        try {
            // Get matching customer object and output
            projectList = tq.getResultList();
          //  System.out.println(projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getComments().get(Comment.getCommentID() - 1).getReact());
            

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
           // em.close();
        }
        
        
    	React2 r = new React2();
 
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();
 
            int count = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getComments().get(Comment.getCommentID() - 1).getReact().get(reactionIndex).getCount();
        //    projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getComments().get(Comment.getCommentID() - 1).getReact().get(reactionIndex).setCount(count + 1);

        r = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getComments().get(Comment.getCommentID() - 1).getReact().get(reactionIndex);
       // r = em.find(React.class,r.react_id );    
//            System.out.println("*****");
//            System.out.println(r.react_id.getReaction());
        r.setCount(count+1);
      //  r.setComment(projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getComments().get(Comment.getCommentID() - 1));
            // Save the customer object
            em.persist(r);

            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            System.out.println("react sql error");
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            System.out.println("react succesful?");
            em.close();
        }
    }


}
