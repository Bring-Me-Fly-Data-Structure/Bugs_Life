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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author richi
 */
public class jsonExport {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernateTest");

    public static void main(String[] args) throws IOException {
        exportJson();
    }

    @Transactional
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
}
