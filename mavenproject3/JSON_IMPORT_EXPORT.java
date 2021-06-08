package mavenproject3;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
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

    private static PreparedStatement pS;
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernateTest");

    public static void importExport() throws IOException {
        Scanner in = new Scanner(System.in);
        while (true) {

            try {
                System.out.println("Enter '1' to import JSON to MySQL Database, '2' to export data from MySQL to JSON file");
                int input = in.nextInt();
                if (input == 1) {
                    importJson();
                    break;
                } else if (input == 2) {
                    exportJson();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid value!\n");
                in.next();
            }
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
        int sizeProject = root.getProjects().size();
        for (int i = 0; i < sizeProject; i++) {
            int sizeIssue = root.getProjects().get(i).getIssues().size();
            for (int j = 0; j < sizeIssue; j++) {
                String title = root.getProjects().get(i).getIssues().get(j).getTitle();
                String ctime = root.getProjects().get(i).getIssues().get(j).changeDateFormat();
                String cname = root.getProjects().get(i).getIssues().get(j).getCreatedBy();
                String allowedEdittor = cname;
                String viewer = "All User";
                String aname = root.getProjects().get(i).getIssues().get(j).getAssignee();
                String changeStatus = "";
                if (aname.equals("") || aname.equals(" ")) {
                    changeStatus = cname;
                } else {
                    changeStatus = aname + " & " + cname;
                }
                Connection userSQL = new Connection();
                try {

                    //? is unspecified value, to substitute in an integer, string, double or blob value.
                    String register = "INSERT INTO access_control (creator,issueTitle,timestamp,allowed_edittor,allowed_viewer,allowed_changeStatus) VALUES(?,?,?,?,?,?)";

                    //insert record of register 
                    pS = userSQL.getConnection().prepareStatement(register);

                    // create the mysql insert preparedstatement
                    //.setString : placeholders that are only replaced with the actual values inside the system
                    pS.setString(1, cname);
                    pS.setString(2, title);
                    pS.setString(3, ctime);
                    pS.setString(4, allowedEdittor);
                    pS.setString(5, viewer);
                    pS.setString(6, changeStatus);

                    pS.executeUpdate(); //return int value

                    System.out.println("Access Control List update successfully ");

                } catch (SQLException e) {
                    System.out.println("Failed to update Access COntrol List. Try again!");
                }
            }
        }
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
            projectList.forEach(project -> project.getIssues().forEach(issue -> {
                issue.setProject(project);
                issue.getComments().forEach(comment -> {
                    comment.setIssue(issue);
                    comment.getReact().forEach(react -> react.setComment(comment));
                    comment.setCommentId(null);
                });
                issue.setId(null);
            }));
            projectList.forEach(project -> {
                project.setId(null);
                em.persist(project);
            });
            userList.forEach(user -> {
                user.setUserid(null);
                em.persist(user);
            });

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
