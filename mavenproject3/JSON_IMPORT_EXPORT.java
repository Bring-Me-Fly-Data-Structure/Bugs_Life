/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavenproject3;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author richi
 */
public class JSON_IMPORT_EXPORT {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernateTest");

    public static void main(String[] args) throws IOException {
        System.out.println("Enter '1' to import JSON to MySQL Database, '2' to export data from MySQL to JSON file");
        Scanner in = new Scanner(System.in);
        int input = in.nextInt();
        if(input==1){
            importJson();
        }else if(input ==2){
            exportJson();
        }
    }

    public static void exportJson() throws IOException {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM User c WHERE c.userid IS NOT NULL";
        // String strQuery3 = "SELECT c FROM React2 c WHERE c.react_id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        TypedQuery<User> tq2 = em.createQuery(strQuery2, User.class);
        //TypedQuery<React2> tq3 = em.createQuery(strQuery3, React2.class);
        List<Project> projectList = new ArrayList<>();
        List<User> listU = new ArrayList<>();
        //List<React2> listR = new ArrayList<>();
        try {
            // Get matching customer object and output
            projectList = tq.getResultList();
            listU = tq2.getResultList();
            // listR = tq3.getResultList();
            ObjectMapper mapper = new ObjectMapper();

            Example root = new Example();
            root.setProjects(projectList);
            root.setUsers(listU);
            String json = mapper.writeValueAsString(root);
            try (FileWriter file = new FileWriter("C:\\Users\\richi\\Desktop\\UM folder\\Y1S2\\WIA1002 DS\\assignment\\localDatabase\\final2.json")) {

                file.write(json);
                System.out.println("Successfully updated json object to file...!!");
            }
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

    }

    public static void importJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //read file
        Example root = mapper.readValue(new File("C:\\Users\\richi\\Desktop\\UM folder\\Y1S2\\WIA1002 DS\\assignment\\localDatabase\\import.json"), Example.class);
        List<Project> projectList = root.getProjects();
        List<User> userList = root.getUsers();
//        for (int i = 0; i < projectList.size(); i++) {
//            
//        }
//projectList.forEach(project-> project.getIssues().forEach(issue-> issue.getComments().forEach(comment -> comment.getReact().forEach(react -> react.setComment(comment)) )));
       
                EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        // Used to issue transactions on the EntityManager
        EntityTransaction et = null;
 
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();
             projectList.forEach(project -> project.getIssues().forEach(issue -> {issue.setProject(project); issue.getComments().forEach(comment -> {comment.setIssue(issue); comment.getReact().forEach(react -> react.setComment(comment));comment.setCommentId(null);}); issue.setId(null);}));
             projectList.forEach(project ->{project.setId(null);em.persist(project);});
             userList.forEach(user->{user.setUserid(null); em.persist(user);});
             

            // Save the customer object
            //em.persist(a);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            System.out.println("json data imported into database");
            em.close();
        }
        
    }
}
