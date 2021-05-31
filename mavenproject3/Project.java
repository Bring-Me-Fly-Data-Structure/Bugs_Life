//package com.example;
package mavenproject3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

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
import java.util.Collections;
import java.util.Scanner;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "issues"
})

@Entity
@Table(name = "project_table")
public class Project implements Serializable  {
        @JsonIgnore @Transient
        private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateTest");
    @JsonIgnore
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")  
    @JsonProperty("id")
    private Integer id;
    
    @Column(name = "project_name")  
    @JsonProperty("name")
    private String name;
    
    @OneToMany(mappedBy="project",fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
    @JsonProperty("issues")
    private List<Issue> issues = new ArrayList<>();
    
    @Transient
    @JsonIgnore
    private static int projectID;

    public Project() {

    }

    @JsonCreator
    public Project(@JsonProperty("id") Integer id, @JsonProperty("name") String name, @JsonProperty("issues") ArrayList<Issue> issues) {
        this.id = id;
        this.name = name;
        this.issues = issues;
    }

    public Project( String name) {
 
        this.name = name;
        this.issues = issues;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("issues")
    public List<Issue> getIssues() {
        return issues;
    }

    @JsonProperty("issues")
    public void setIssues(ArrayList<Issue> issues) {
        this.issues = issues;
    }

//    @Override
//    public int compareTo(Project o) {
//        return this.name.compareTo(o.name);
//    }

    public static int getProjectID() {
        return projectID;
    }

    public static void setProjectID(int projectID) {
        Project.projectID = projectID;
    }

    //methods
    public static void displayProject() throws IOException {
   
        Example e = new Example();
        Scanner in = new Scanner(System.in);
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    	
    	// the lowercase c refers to the object
    	// :custID is a parameterized query thats value is set below
    	String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";

    	// Issue the query and get a matching Customer
    	TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
    	List<Project> a= new ArrayList<>();
    	try {
 
    		// Get matching customer object and output
    		a = tq.getResultList();
 
    	}
    	catch(NoResultException ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		em.close();
    	}
    //    ObjectMapper objM = new ObjectMapper();
//        try {
//
//            Example base = objM.readValue(new File("C:\\Users\\richi\\Desktop\\UM folder\\Y1S2\\WIA1002 DS\\assignment\\localDatabase\\final.json"), Example.class);
//            ArrayList<Project> a = base.getProjects();

            e.addProject(a);
            System.out.println("Enter 'a' to sort Projects by alphanumerical \nor 'i' to sort by Project ID: ");
            String sortMethod = in.next();

            if (sortMethod.equals("a")) {
                e.sortAplhanumerically(a);
            } else if (sortMethod.equals("i")) {
                e.sortProjectID(a);
            }
            
            System.out.println("Do you want create project or check project? ");
            System.out.println("Enter 'c' to create new project.\nEnter 'id' to check project");
            String input = in.next();
            if (input.equals("c")) {
                addProjectByUser();
            } else if (Issue.isInteger(input)) {
                int id = Integer.parseInt(input);
                Project.setProjectID(id);
                Issue.displayIssueBoard();
            }
            // System.out.println("Enter selected project id to check project: ");
//        } catch (JsonProcessingException ex) {
//            System.out.println(" file input error");
//        }
    }

    public static void addProjectByUser() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter project name: ");
        String projectName = input.nextLine();
//        ObjectMapper mapper = new ObjectMapper();
//        //read file
//        Example root = mapper.readValue(new File("C:\\Users\\richi\\Desktop\\UM folder\\Y1S2\\WIA1002 DS\\assignment\\localDatabase\\final.json"), Example.class);
//        int projectId = root.getProjects().size();
//
//        //Update value in object
//        Project c = new Project(projectId + 1, projectName);
//        root.addProject(c);
//
//        String json = mapper.writeValueAsString(root);
//        try (FileWriter file = new FileWriter("C:\\Users\\richi\\Desktop\\UM folder\\Y1S2\\WIA1002 DS\\assignment\\localDatabase\\final.json")) {
//
//            file.write(json);
//            System.out.println("Successfully updated json object to file...!!");
//        }
        // The EntityManager class allows operations such as create, read, update, delete
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        // Used to issue transactions on the EntityManager
        EntityTransaction et = null;
 
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();
 
            // Create and set values for new customer
            Project c = new Project( projectName);
 
            // Save the customer object
            em.persist(c);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            System.out.println("New project added into database");
            em.close();
        }
        displayProject() ;
    }
    
}
