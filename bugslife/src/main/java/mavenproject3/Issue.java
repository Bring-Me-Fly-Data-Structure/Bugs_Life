//package com.example;
package mavenproject3;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "title",
    "priority",
    "status",
    "tag",
    "descriptionText",
    "createdBy",
    "assignee",
    "timestamp",
    "comments",
    "statusTimestamp"
})
@Entity
@Table(name = "issue_table")
public class Issue implements Serializable {

    @JsonIgnore
    @Transient
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateTest");

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;

    @JsonIgnore
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_id")
    @JsonProperty("id")
    private Integer id;

    @Column(name = "issue_title")
    @JsonProperty("title")
    private String title;

    @Column(name = "priority")
    @JsonProperty("priority")
    private Integer priority;

    @Column(name = "status")
    @JsonProperty("status")
    private String status;

    @Transient
    @JsonProperty("tag")
    private ArrayList<String> tag;

    @Column(name = "tag")
    @JsonIgnore
    private String tag2 = "";

    @Column(name = "description")
    @JsonProperty("descriptionText")
    private String descriptionText;

    @Column(name = "creator")
    @JsonProperty("createdBy")
    private String createdBy;

    @Column(name = "assignee")
    @JsonProperty("assignee")
    private String assignee;

    @Column(name = "timestamp")
    @JsonProperty("timestamp")
    private Integer timestamp;

    @Column(name = "statusTimestamp")
    @JsonProperty("statusTimestamp")
    private Integer statusTimestamp;

    @OneToMany(mappedBy = "issue", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    // @Transient
    @JsonProperty("comments")
    private List<Comment> comments = new ArrayList<>();

    @Transient
    @JsonIgnore
    private java.util.Date timestampformat;

    @Transient
    @JsonIgnore
    private static int issueID;

    @Transient
    @JsonIgnore
    private static PreparedStatement pS;

    @Transient
    @JsonIgnore
    private static ResultSet result;

    public Issue() {

    }

    //constructor for user input data
    public Issue(String title, Integer priority, ArrayList<String> tag, String descriptionText, String createdBy, String assignee, ArrayList<Comment> comments) {
        // this.id = id;
        this.title = title;
        this.priority = priority;
        this.status = "Open";
        this.tag = tag;
        for (int i = 0; i < tag.size(); i++) {
            this.tag2 += tag.get(i) + " ";
        }
        this.descriptionText = descriptionText;
        this.createdBy = createdBy;
        this.assignee = assignee;
        this.comments = comments;
        this.timestampformat = new java.util.Date();
        Integer i = Math.toIntExact(new java.util.Date().getTime() / 1000);
        this.timestamp = i;
        this.statusTimestamp = i;
    }

    @JsonCreator
    //constructor for reading data.json
    public Issue(@JsonProperty("id") Integer id, @JsonProperty("title") String title, @JsonProperty("priority") Integer priority, @JsonProperty("status") String status, @JsonProperty("tag") ArrayList<String> tag, @JsonProperty("descriptionText") String descriptionText, @JsonProperty("createdBy") String createdBy, @JsonProperty("assignee") String assignee, @JsonProperty("timestamp") Integer timestamp, @JsonProperty("comments") ArrayList<Comment> comments, @JsonProperty("statusTimestamp") Integer statusTimestamp) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.status = status;
        this.tag = tag;
        this.descriptionText = descriptionText;
        this.createdBy = createdBy;
        this.assignee = assignee;
        this.timestamp = timestamp;
        this.statusTimestamp = statusTimestamp;
        this.timestampformat = new java.util.Date((long) timestamp * 1000);
        for (int i = 0; i < tag.size(); i++) {
            this.tag2 += tag.get(i) + " ";
        }
        this.comments = comments;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("priority")
    public Integer getPriority() {
        return priority;
    }

    @JsonProperty("priority")
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("tag")
    public ArrayList<String> getTag() {
        return tag;
    }

    @JsonProperty("tag")
    public void setTag(ArrayList<String> tag) {
        this.tag = tag;
    }

    @JsonProperty("descriptionText")
    public String getDescriptionText() {
        return descriptionText;
    }

    @JsonProperty("descriptionText")
    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    @JsonProperty("createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("assignee")
    public String getAssignee() {
        return assignee;
    }

    @JsonProperty("assignee")
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("comments")
    public List<Comment> getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getTimestampformat() {
        return timestampformat;
    }

    @JsonProperty("statusTimestamp")
    public Integer getStatusTimestamp() {
        return statusTimestamp;
    }

    @JsonProperty("statusTimestamp")
    public void setStatusTimestamp(Integer statusTimestamp) {
        this.statusTimestamp = statusTimestamp;
    }

    @PostLoad //it will auto call when reading data from mysql 
    public void SQLtoPOJO() {
        this.timestampformat = new java.util.Date((long) this.timestamp * 1000);
        this.tag = new ArrayList<>(Arrays.asList(this.tag2.split(" ")));
    }

    public String changeDateFormat() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        return ft.format(getTimestampformat());
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public static int getIssueID() {
        return issueID;
    }

    public static void setIssueID(int issueID) {
        Issue.issueID = issueID;
    }
//
//    @Override
//    public int compareTo(Issue object) {
//        int result = 0;
//        if (this.getPriority() < object.getPriority()) {
//            result = 2;
//        } else if (this.getPriority() > object.getPriority()) {
//            result = -1;
//        } else if (this.getPriority() == object.getPriority()) {
//            if (this.getTimestampformat().compareTo(object.getTimestampformat()) > 0) {
//                result = 1;
//            } else {
//                result = 0;
//            }
//        }
//        return result;
//    }

    //main method call addIssue() enough
    public static void addIssue() throws IOException, ParseException {
        //declare variable
        Scanner input = new Scanner(System.in);

        //display issue board
        Issuequeue i = new Issuequeue();

        //action for issue board
        i.offer(create(input));
        System.out.println("--------------------------------------");
        System.out.println("Add issue successfully");
        displayIssueBoard();
    }

    public static Issuequeue sortTime(Issuequeue list) {
        ArrayList<Issue> sortList = new ArrayList<>();
        while (!list.isEmpty()) {
            sortList.add(list.poll());
        }
        // Collections.sort(sortList);
        Comparator<Issue> timeOrder = new Comparator<>() {
            @Override
            public int compare(Issue s1, Issue e2) {
                return s1.getTimestamp().compareTo(e2.getTimestamp());
            }
        };
        Collections.sort(sortList, timeOrder);
        for (int i = 0; i < sortList.size(); i++) {
            list.offer(sortList.get(i));
        }
        return list;
    }

    public static Issuequeue sortID(Issuequeue list) {
        ArrayList<Issue> sortList = new ArrayList<>();
        while (!list.isEmpty()) {
            sortList.add(list.poll());
        }
        // Collections.sort(sortList);
        Comparator<Issue> timeOrder = new Comparator<>() {
            @Override
            public int compare(Issue s1, Issue e2) {
                return s1.getId().compareTo(e2.getId());
            }
        };
        Collections.sort(sortList, timeOrder);
        for (int i = 0; i < sortList.size(); i++) {
            list.offer(sortList.get(i));
        }
        return list;
    }

    public static Issuequeue sortPriority(Issuequeue list) {
        ArrayList<Issue> sortList = new ArrayList<>();
        while (!list.isEmpty()) {
            sortList.add(list.poll());
        }
        // Collections.sort(sortList);
        Comparator<Issue> timeOrder = new Comparator<>() {
            @Override
            public int compare(Issue s1, Issue e2) {
                return e2.getPriority().compareTo(s1.getPriority());
            }
        };
        Collections.sort(sortList, timeOrder);
        for (int i = 0; i < sortList.size(); i++) {
            list.offer(sortList.get(i));
        }
        return list;
    }

    public static Issue create(Scanner input) throws IOException {

        ArrayList<Comment> comment = new ArrayList<>();
        String title = "";
        String description = "";
        String cname = User.getLoginName();
        String aname = "";
        ArrayList<String> tag = new ArrayList<>();
        //String tag ="";
        Integer priority = 0;
        // String status = "";
        // ArrayList<React> b = new ArrayList<>();
        System.out.println("Enter Title : ");
        title = input.nextLine();

        System.out.println("Enter description text : (Enter '$undo' for undo, '$redo' for redo, '$end' for end)");
        UndoRedoStack<String> a = new UndoRedoStack<>();
        while (input.hasNext()) {
            String s1 = input.nextLine();
            if (s1.equals("$end")) {
                break;
            } else if (s1.equals("$undo")) {
                a.undo();
                System.out.println(a);
            } else if (s1.equals("$redo")) {
                a.redo();
                System.out.println(a);
            } else {
                a.push(s1);
                System.out.println(a);
            }
        }
        System.out.println("------------------------------");
        System.out.println("Description text");
        System.out.println("------------------------------");
        if (a.size() > 1) {
            description = description + a.get(0) + "\n";
            for (int i = 1; i < a.size() - 1; i++) {
                description = description + a.get(i) + "\n";
            }
            description = description + a.get(a.size() - 1);
        } else {
            description = description + a.get(0);
        }

        System.out.println(description);
        System.out.println("------------------------------");
//        System.out.println("Enter cretor name : ");
//        cname = input.nextLine();
        while (true) {
            try {

                System.out.println("Do u want add assignee name ? input '1' if yes ,'2' if no");
                String ans = input.nextLine();
                if (isInteger(ans)) {
                    int option = Integer.parseInt(ans);
                    //input.nextLine();
                    if (option == 1) {
                        System.out.println("Enter assignee name : ");
                        aname = input.nextLine();
                        break;
                    } else if (option == 2) {
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                System.out.println("");
            }
        }
//        System.out.println("Do u want add assignee name ? input y if yes");
//        String option = input.next();
//        input.nextLine();
//        if (option.equals("Y") || option.equals("y")) {
//            System.out.println("Enter assignee name : ");
//            aname = input.nextLine();
//        }
//
//        System.out.println("Do u want add tag ? input y if yes");
//        String option3 = input.next();
//        //input.nextLine();
//        if (option3.equals("Y") || option3.equals("y")) {
//            System.out.println("Enter number of tag: ");
//            int numT = input.nextInt();
//            //buffer
//            input.nextLine();
//            for (int i = 0; i < numT; i++) {
//                System.out.println("Add tag : ");
//                tag.add(input.nextLine());
//            }
//        }
        outer:
        while (true) {
            try {
                System.out.println("Do u want add tag ? input '1' if yes, '2' if no");
                String option = input.nextLine();
                if (isInteger(option)) {
                    int option3 = Integer.parseInt(option);
                    if (option3 == 1) {
                        while (true) {
                            try {
                                
                                System.out.println("Enter number of tag: ");
                                String numT = input.nextLine();
                                //buffer
                                //input.nextLine();
                                if(isInteger(numT)){
                                    int numTT=Integer.parseInt(numT);
                                    for (int i = 0; i < numTT; i++) {
                                    System.out.println("Add tag : ");
                                    tag.add(input.nextLine());
                                }
                                break outer;  
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please try again.");
                                System.out.println("");
                            }
                            //break;
                        }
                        //break;
                    } else if (option3 == 2) {
                        break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                System.out.println("");
            }
        }
        while (true) {
            try {
                System.out.println("Add priority : ");
                String num = input.nextLine();
                //input.nextLine();
                if (isInteger(num)) {
                    priority = Integer.parseInt(num);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                System.out.println("");
            }

        }
         Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        String timestamp = ft.format(date);
        Connection userSQL = new Connection();
        String allowedEdittor = cname;
                String viewer = "All User";
                String changeStatus = "";
                if (aname.equals("") || aname.equals(" ")) {
                    changeStatus = cname;
                } else {
                    changeStatus = aname + " & " + cname;
                }
                //importing access_control data
                try {

                    //? is unspecified value, to substitute in an integer, string, double or blob value.
                    String register = "INSERT INTO access_control (creator,issueTitle,timestamp,allowed_edittor,allowed_viewer,allowed_changeStatus) VALUES(?,?,?,?,?,?)";

                    //insert record of register 
                    pS = userSQL.getConnection().prepareStatement(register);

                    // create the mysql insert preparedstatement
                    //.setString : placeholders that are only replaced with the actual values inside the system
                    
                    pS.setString(1, aname);
                    pS.setString(2, title);
                    pS.setString(3, timestamp);
                    pS.setString(4, allowedEdittor);
                    pS.setString(5, viewer);
                    pS.setString(6, changeStatus);

                    pS.executeUpdate(); //return int value

                    System.out.println("Access Control List update successfully ");

                } catch (SQLException e) {
                    System.out.println("Failed to update Access COntrol List. Try again!");
                }
            
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        List<Project> projectList = new ArrayList<>();

        try {
            // Get matching customer object and output
            projectList = tq.getResultList();

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            //em.close();
        }

        // Used to issue transactions on the EntityManager
        EntityTransaction et = null;
        Issue i = new Issue();
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new Issue
            i = new Issue(title, priority, tag, description, cname, aname, comment);
            i.setProject(projectList.get(Project.getProjectID() - 1));
            //  projectList.get(Project.getProjectID() - 1).getIssues().add(i);
            // Save the customer object
            em.persist(i);
            // em.persist(projectList.get(Project.getProjectID() - 1)); //not sure
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        } finally {
            System.out.println("issue added to database.");
            // Close EntityManager
            em.close();
        }
        System.out.println("--------------------------------------");
        System.out.println("Add issue successfully");
        return i;
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void displayIssueBoard() throws IOException, ParseException {
        Scanner input = new Scanner(System.in);
        //display issue board
        Issuequeue i = new Issuequeue();
//        ObjectMapper objM = new ObjectMapper();
//        try {
//
//            Example base = objM.readValue(new File("C:\\Users\\richi\\Desktop\\UM folder\\Y1S2\\WIA1002 DS\\assignment\\localDatabase\\final.json"), Example.class);
//            List<Issue> a = base.getProjects().get(Project.getProjectID() - 1).getIssues();
//            i.offer(a);
//            // i.display(1);
//        } catch (JsonProcessingException ex) {
//            System.out.println(" file input error");
//        }
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        List<Project> projectList = new ArrayList<>();
        try {
            // Get matching customer object and output
            projectList = tq.getResultList();
            i.offer(projectList.get(Project.getProjectID() - 1).getIssues());

            System.out.println("Enter 'p' to sort Issues by priority\nor 't' to sort by timestamp\nor 'i' to sort by issue id");
            String sortMethod = input.next();
            switch (sortMethod) {
                case "p":
                    sortPriority(i);
                    break;
                case "t":
                    sortTime(i);
                    break;
                case "i":
                    sortID(i);
                    break;
            }
            //System.out.println(projectList.get(Project.getProjectID() - 1).getIssues().get(0).getTimestampformat()); //testing
            i.display();            //fix indexing problems

            System.out.println("Enter selected issue ID to check issue \nor 's' to search \nor 'c' to create issue\nor 'h' to check the changelog \nor 'b' to return to project dashboard:");
            String option = input.next();
            input.nextLine();
            if (isInteger(option)) {
                int numIndex = Integer.parseInt(option);
                Issue.setIssueID(numIndex);
                i.displayIssueDetails(numIndex);
            } else if (option.equals("s")) {
                FuzzySearch.search();
            } else if (option.equals("c")) {
                addIssue();
            } else if (option.equals("b")) {
                Project.displayProject();
            } else if (option.equals("h")) {
                changelog.viewChangelogbyProject();
               // changelog.viewChangelogbyProject();
                while (true) {
                    try {
                        System.out.println("Enter '1' to return issue dashboard");
                        int userInput = input.nextInt();
                        if (userInput == 1) {
                            i.display();
                            while(true){
                                
                            try{
                            System.out.println("Enter selected issue ID to check issue \nor 's' to search \nor 'c' to create issue\nor 'h' to check the changelog \nor 'b' to return to project dashboard:");
                            option = input.next();
                            input.nextLine();
                            if (isInteger(option)) {
                                int numIndex = Integer.parseInt(option);
                                Issue.setIssueID(numIndex);
                                i.displayIssueDetails(numIndex);
                            } else if (option.equals("s")) {
                                FuzzySearch.search();
                                break;
                            } else if (option.equals("c")) {
                                addIssue();
                                break;
                            } else if (option.equals("b")) {
                                Project.displayProject();
                                break;
                            }else if (option.equals("h")) {
                                changelog.viewChangelogbyProject();
                                break;
                            }
                            }catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please try again.");
                        input.next();
                            }
                            }
                        }
                            
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please try again.");
                        input.next();
                    }
                }
            }
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

//        int prevIssueSize = 0;
//        if (Project.getProjectID() > 1) {
//                    
//            for (int j = 0; j < Project.getProjectID() - 1; j++) {
//                prevIssueSize+=projectList.get(j).getIssues().size();
//            }
//        }
    }

    public static void setIssueStatus(String newStatus) throws IOException {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Issue a = null;
        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM Issue c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        TypedQuery<Issue> tq2 = em.createQuery(strQuery2, Issue.class);
        List<Project> projectList = new ArrayList<>();
        List<Issue> issueList = new ArrayList<>();
        String projectName = "";
        int projectID = -1;
        String issueTitle = "";
        int issueID = -1;
        String oldstatus = "";
        try {
            // Get matching customer object and output
            projectList = tq.getResultList();
            issueList = tq2.getResultList();
            projectName = projectList.get(Project.getProjectID() - 1).getName();
            projectID = projectList.get(Project.getProjectID() - 1).getId();
            issueTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
            issueID = issueList.get(Issue.getIssueID() - 1).getId();
            oldstatus = issueList.get(Issue.getIssueID() - 1).getStatus();
//            issueTitle =projectList.get(Project.getProjectID() - 1).getIssues().stream().filter(issue -> issue.getId()==Issue.getIssueID()).findFirst().get().getTitle();
//            issueID =projectList.get(Project.getProjectID() - 1).getIssues().stream().filter(issue -> issue.getId()==Issue.getIssueID()).findFirst().get().getId();
//            oldstatus =projectList.get(Project.getProjectID() - 1).getIssues().stream().filter(issue -> issue.getId()==Issue.getIssueID()).findFirst().get().getStatus();

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            //  em.close();
        }
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find customer and make changes
            a = em.find(Issue.class, Issue.getIssueID());
            a.setStatus(newStatus);
            Integer i = Math.toIntExact(new java.util.Date().getTime() / 1000);
            a.setStatusTimestamp(i);

            // Save the customer object
            em.persist(a);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            System.out.println("change issue status sql error");
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        String newtimestamp = ft.format(date);
        String detail = "Changes status from \"" + oldstatus + "\" to \"" + newStatus + "\"";

        Connection userSQL = new Connection();
        try {

            //? is unspecified value, to substitute in an integer, string, double or blob value.
            String register = "INSERT INTO changelog (project_name,project_id,issue_name,issue_id,detail,edit_time,edittor) VALUES(?, ?,?,?,?,?,?)";

            //insert record of register 
            pS = userSQL.getConnection().prepareStatement(register);

            // create the mysql insert preparedstatement
            //.setString : placeholders that are only replaced with the actual values inside the system
            pS.setString(1, projectName);
            pS.setInt(2, projectID);
            pS.setString(3, issueTitle);
            pS.setInt(4, issueID);
            pS.setString(5, detail);
            pS.setString(6, newtimestamp);
            pS.setString(7, User.getLoginName());

            pS.executeUpdate(); //return int value

            System.out.println("change log update (status) Successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to update changelog (status). Try again!");
        }
    }

    public static void setNewIssueTitle(String newTitle) throws IOException {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Issue a = null;
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM Issue c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        TypedQuery<Issue> tq2 = em.createQuery(strQuery2, Issue.class);
        List<Project> projectList = new ArrayList<>();
        List<Issue> issueList = new ArrayList<>();
        String projectName = "";
        int projectID = -1;
        String issueTitle = "";
        int issueID = -1;
        String oldTitle = "";
        try {
            // Get matching customer object and output
            projectList = tq.getResultList();
            issueList = tq2.getResultList();
            projectName = projectList.get(Project.getProjectID() - 1).getName();
            projectID = projectList.get(Project.getProjectID() - 1).getId();
            issueTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
            issueID = issueList.get(Issue.getIssueID() - 1).getId();
            oldTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
//            issueTitle=projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getTitle();
//            issueID = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getId();
//            oldTitle = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getTitle();

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            //  em.close();
        }
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find customer and make changes
            a = em.find(Issue.class, Issue.getIssueID());
            a.setTitle(newTitle);

            // Save the customer object
            em.persist(a);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            System.out.println("change issue status sql error");
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        String newtimestamp = ft.format(date);
        String detail = "Changes title from \"" + oldTitle + "\" to \"" + newTitle + "\"";

        Connection userSQL = new Connection();
        try {

            //? is unspecified value, to substitute in an integer, string, double or blob value.
            String register = "INSERT INTO changelog (project_name,project_id,issue_name,issue_id,detail,edit_time,edittor) VALUES(?, ?,?,?,?,?,?)";

            //insert record of register 
            pS = userSQL.getConnection().prepareStatement(register);

            // create the mysql insert preparedstatement
            //.setString : placeholders that are only replaced with the actual values inside the system
            pS.setString(1, projectName);
            pS.setInt(2, projectID);
            pS.setString(3, newTitle);
            pS.setInt(4, issueID);
            pS.setString(5, detail);
            pS.setString(6, newtimestamp);
            pS.setString(7, User.getLoginName());

            pS.executeUpdate(); //return int value

            System.out.println("change log update (title) Successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to update changelog (title). Try again!");
        }
    }

    public static void setNewDescription(String newDescription) throws IOException {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Issue a = null;
        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM Issue c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        TypedQuery<Issue> tq2 = em.createQuery(strQuery2, Issue.class);
        List<Project> projectList = new ArrayList<>();
        List<Issue> issueList = new ArrayList<>();
        String projectName = "";
        int projectID = -1;
        String issueTitle = "";
        int issueID = -1;
        String oldDescription = "";
        try {
            // Get matching customer object and output
            projectList = tq.getResultList();
            issueList = tq2.getResultList();
            projectName = projectList.get(Project.getProjectID() - 1).getName();
            projectID = projectList.get(Project.getProjectID() - 1).getId();
            issueTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
            issueID = issueList.get(Issue.getIssueID() - 1).getId();
            oldDescription = issueList.get(Issue.getIssueID() - 1).getDescriptionText();
//            issueTitle = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getTitle();
//            issueID = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getId();
//            oldDescription = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getDescriptionText();

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            //  em.close();
        }
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find customer and make changes
            a = em.find(Issue.class, Issue.getIssueID());
            a.setDescriptionText(newDescription);

            // Save the customer object
            em.persist(a);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            System.out.println("change issue status sql error");
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        String newtimestamp = ft.format(date);
        String detail = "Changes description from \"" + oldDescription + "\" to \"" + newDescription + "\"";

        Connection userSQL = new Connection();
        try {

            //? is unspecified value, to substitute in an integer, string, double or blob value.
            String register = "INSERT INTO changelog (project_name,project_id,issue_name,issue_id,detail,edit_time,edittor) VALUES(?, ?,?,?,?,?,?)";

            //insert record of register 
            pS = userSQL.getConnection().prepareStatement(register);

            // create the mysql insert preparedstatement
            //.setString : placeholders that are only replaced with the actual values inside the system
            pS.setString(1, projectName);
            pS.setInt(2, projectID);
            pS.setString(3, issueTitle);
            pS.setInt(4, issueID);
            pS.setString(5, detail);
            pS.setString(6, newtimestamp);
            pS.setString(7, User.getLoginName());

            pS.executeUpdate(); //return int value

            System.out.println("change log update (description) Successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to update changelog (description). Try again!");
        }
    }

    public static void setNewAssignee(String newAssignee) throws IOException {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Issue a = null;
        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM Issue c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        TypedQuery<Issue> tq2 = em.createQuery(strQuery2, Issue.class);
        List<Project> projectList = new ArrayList<>();
        List<Issue> issueList = new ArrayList<>();
        String projectName = "";
        int projectID = -1;
        String issueTitle = "";
        int issueID = -1;
        String oldAssignee = "";
        try {
            // Get matching customer object and output
            projectList = tq.getResultList();
            issueList = tq2.getResultList();
            projectName = projectList.get(Project.getProjectID() - 1).getName();
            projectID = projectList.get(Project.getProjectID() - 1).getId();
            issueTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
            issueID = issueList.get(Issue.getIssueID() - 1).getId();
            oldAssignee = issueList.get(Issue.getIssueID() - 1).getAssignee();
//            issueTitle = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getTitle();
//            issueID = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getId();
//            oldAssignee = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getAssignee();

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            //  em.close();
        }
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find customer and make changes
            a = em.find(Issue.class, Issue.getIssueID());
            a.setAssignee(newAssignee);

            // Save the customer object
            em.persist(a);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            System.out.println("change issue status sql error");
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        String newtimestamp = ft.format(date);
        String detail = "Changes assignee from \"" + oldAssignee + "\" to \"" + newAssignee + "\"";

        Connection userSQL = new Connection();
        try {

            //? is unspecified value, to substitute in an integer, string, double or blob value.
            String register = "INSERT INTO changelog (project_name,project_id,issue_name,issue_id,detail,edit_time,edittor) VALUES(?, ?,?,?,?,?,?)";

            //insert record of register 
            pS = userSQL.getConnection().prepareStatement(register);

            // create the mysql insert preparedstatement
            //.setString : placeholders that are only replaced with the actual values inside the system
            pS.setString(1, projectName);
            pS.setInt(2, projectID);
            pS.setString(3, issueTitle);
            pS.setInt(4, issueID);
            pS.setString(5, detail);
            pS.setString(6, newtimestamp);
            pS.setString(7, User.getLoginName());

            pS.executeUpdate(); //return int value

            System.out.println("change log update (assignee) Successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to update changelog (assignee). Try again!");
        }
    }

    public static void setNewPriority(int newPriority) throws IOException {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Issue a = null;
        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM Issue c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        TypedQuery<Issue> tq2 = em.createQuery(strQuery2, Issue.class);
        List<Project> projectList = new ArrayList<>();
        List<Issue> issueList = new ArrayList<>();
        String projectName = "";
        int projectID = -1;
        String issueTitle = "";
        int issueID = -1;
        int oldPrio = -1;
        try {
            // Get matching customer object and output
            projectList = tq.getResultList();
            issueList = tq2.getResultList();
            projectName = projectList.get(Project.getProjectID() - 1).getName();
            projectID = projectList.get(Project.getProjectID() - 1).getId();
            issueTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
            issueID = issueList.get(Issue.getIssueID() - 1).getId();
            oldPrio = issueList.get(Issue.getIssueID() - 1).getPriority();
//            issueTitle = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getTitle();
//            issueID = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getId();
//            oldPrio = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getPriority();

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            //  em.close();
        }
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find customer and make changes
            a = em.find(Issue.class, Issue.getIssueID());
            a.setPriority(newPriority);

            // Save the customer object
            em.persist(a);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            System.out.println("change issue status sql error");
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        String newtimestamp = ft.format(date);
        String detail = "Changes priority from \"" + oldPrio + "\" to \"" + newPriority + "\"";

        Connection userSQL = new Connection();
        try {

            //? is unspecified value, to substitute in an integer, string, double or blob value.
            String register = "INSERT INTO changelog (project_name,project_id,issue_name,issue_id,detail,edit_time,edittor) VALUES(?, ?,?,?,?,?,?)";

            //insert record of register 
            pS = userSQL.getConnection().prepareStatement(register);

            // create the mysql insert preparedstatement
            //.setString : placeholders that are only replaced with the actual values inside the system
            pS.setString(1, projectName);
            pS.setInt(2, projectID);
            pS.setString(3, issueTitle);
            pS.setInt(4, issueID);
            pS.setString(5, detail);
            pS.setString(6, newtimestamp);
            pS.setString(7, User.getLoginName());

            pS.executeUpdate(); //return int value

            System.out.println("change log update (assignee) Successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to update changelog (assignee). Try again!");
        }
    }

    public static void setNewTag(ArrayList newTag) throws IOException {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Issue a = null;
        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM Issue c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        TypedQuery<Issue> tq2 = em.createQuery(strQuery2, Issue.class);
        List<Project> projectList = new ArrayList<>();
        List<Issue> issueList = new ArrayList<>();
        String projectName = "";
        int projectID = -1;
        String issueTitle = "";
        int issueID = -1;
        // String oldTag="";
        ArrayList<String> oldTag = new ArrayList<>();

        try {
            // Get matching customer object and output
            projectList = tq.getResultList();
            issueList = tq2.getResultList();
            projectName = projectList.get(Project.getProjectID() - 1).getName();
            projectID = projectList.get(Project.getProjectID() - 1).getId();
            issueTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
            issueID = issueList.get(Issue.getIssueID() - 1).getId();
            oldTag = issueList.get(Issue.getIssueID() - 1).getTag();
//            issueTitle = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getTitle();
//            issueID = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getId();
//            oldTag = projectList.get(Project.getProjectID() - 1).getIssues().get(Issue.getIssueID() - 1).getTag();

//            for (int i = 0; i < oldTag.size(); i++) {
//                oldTag+=oldTag.get(i)+" ";
//            }
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            //  em.close();
        }
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find customer and make changes
            a = em.find(Issue.class, Issue.getIssueID());
            a.setTag(newTag);

            // Save the customer object
            em.persist(a);
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            System.out.println("change issue status sql error");
            ex.printStackTrace();
        } finally {
            // Close EntityManager
            em.close();
        }

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        String newtimestamp = ft.format(date);
        String detail = "Changes tag from \"" + oldTag.toString() + "\" to \"" + newTag.toString() + "\"";

        Connection userSQL = new Connection();
        try {

            //? is unspecified value, to substitute in an integer, string, double or blob value.
            String register = "INSERT INTO changelog (project_name,project_id,issue_name,issue_id,detail,edit_time,edittor) VALUES(?, ?,?,?,?,?,?)";

            //insert record of register 
            pS = userSQL.getConnection().prepareStatement(register);

            // create the mysql insert preparedstatement
            //.setString : placeholders that are only replaced with the actual values inside the system
            pS.setString(1, projectName);
            pS.setInt(2, projectID);
            pS.setString(3, issueTitle);
            pS.setInt(4, issueID);
            pS.setString(5, detail);
            pS.setString(6, newtimestamp);
            pS.setString(7, User.getLoginName());

            pS.executeUpdate(); //return int value

            System.out.println("change log update (assignee) Successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to update changelog (assignee). Try again!");
        }
    }
}