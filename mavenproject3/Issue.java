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
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
// Issue class that implements Serializable library
public class Issue implements Serializable {

    // instant variables
    @JsonIgnore
    @Transient
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateTest");

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
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

    private static Integer priority1 = 0;
    private static String aname = "";
    private static ArrayList<String> tag1 = new ArrayList<>();

    // empty constructor
    public Issue() {

    }

    // constructor for user input data
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

    //----- accessor and mutator-----
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

    // it will auto call when reading data from mysql 
    @PostLoad
    public void SQLtoPOJO() {
        this.timestampformat = new java.util.Date((long) this.timestamp * 1000);
        this.tag = new ArrayList<>(Arrays.asList(this.tag2.split(" ")));
    }

    // change the date format 
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

    // add Issue after created by the create method
    public static void addIssue() throws IOException {
        Scanner input = new Scanner(System.in);
        Issuequeue i = new Issuequeue();
        i.offer(create(input));
        displayIssueBoard();
    }

    // sort the Issues in the issuequeue by timestamp
    public static Issuequeue sortTime(Issuequeue list) {
        ArrayList<Issue> sortList = new ArrayList<>();
        while (!list.isEmpty()) {
            sortList.add(list.poll());
        }
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

    // sort the Issues in the issuequeue by issue id
    public static Issuequeue sortID(Issuequeue list) {
        ArrayList<Issue> sortList = new ArrayList<>();
        while (!list.isEmpty()) {
            sortList.add(list.poll());
        }
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

    // sort the Issues in the issuequeue by priority
    public static Issuequeue sortPriority(Issuequeue list) {
        ArrayList<Issue> sortList = new ArrayList<>();
        while (!list.isEmpty()) {
            sortList.add(list.poll());
        }
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

    // create the Issue by the user input
    public static Issue create(Scanner input) throws IOException {
        ArrayList<Comment> comment = new ArrayList<>();
        String title = "";
        String description = "";
        String cname = User.getLoginName();
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
        assigneeName();

        // The EntityManager class allows operations such as create, read, update, delete
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :ID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Project
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        List<Project> projectList = new ArrayList<>();

        try {
            // Get matching project object and output
            projectList = tq.getResultList();

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
        }

        // Used to issue transactions on the EntityManager
        EntityTransaction et = null;
        Issue i = new Issue();
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new Issue 
            i = new Issue(title, priority1, tag1, description, cname, aname, comment);
            i.setProject(projectList.get(Project.getProjectID() - 1));

            // Save the issue object
            em.persist(i);
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
    
    // recursion method for adding assignee name used when user invalid input
    public static void assigneeName() {
        Scanner input = new Scanner(System.in);
        try {

            System.out.println("Do u want add assignee name ? input '1' if yes ,'2' if no");
            int option = input.nextInt();
            input.nextLine();
            if (option == 1) {
                System.out.println("Enter assignee name : ");
                aname = input.nextLine();
                tag();
            } else if (option == 2) {
                tag();
            } else if (option != 1 && option != 2) {
                System.out.println("Invalid input. Please try again.");
                System.out.println("");
                assigneeName();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            System.out.println("");
            assigneeName();
        }
    }

    // recursion method for adding tag and priority when user invalid input
    public static void tag() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Do u want add tag ? input '1' if yes, '2' if no");
            int option3 = input.nextInt();
            if (option3 == 1) {
                System.out.println("Enter number of tag: ");
                int numT = input.nextInt();
                //buffer
                input.nextLine();
                for (int i = 0; i < numT; i++) {
                    System.out.println("Add tag : ");
                    tag1.add(input.nextLine());
                }
                System.out.println("Add priority : ");
                priority1 = input.nextInt();
                input.nextLine();

            } else if (option3 == 2) {
                System.out.println("Add priority : ");
                priority1 = input.nextInt();
                input.nextLine();

            } else if (option3 != 1 && option3 != 2) {
                System.out.println("Invalid input. Please try again.");
                System.out.println("");
                tag();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            System.out.println("");
            tag();
        }
    }

    // determine whether value of string is an integer
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    // display Issue Board
    public static void displayIssueBoard() throws IOException {
        Scanner input = new Scanner(System.in);
        Issuequeue i = new Issuequeue();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :ID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Project
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        List<Project> projectList = new ArrayList<>();
        try {
            // Get matching project object and output
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
                default:
                    System.out.println("Invalid input. Please try again.");
                    displayIssueBoard();
                    break;
            }
            i.display();
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
                try {
                    Connection changelogSQL = new Connection();
                    //? is unspecified value, to substitute in an integer, string, double or blob value.
                    String changelog = "SELECT * FROM `changelog` WHERE `project_id`= ? ";

                    //insert record of changelog 
                    pS = changelogSQL.getConnection().prepareStatement(changelog);

                    pS.setInt(1, Project.getProjectID());
                    result = pS.executeQuery();
                    // create the mysql insert preparedstatement
                    //.setString : placeholders that are only replaced with the actual values inside the system
                    while (result.next()) {
                        String edit_time = result.getString("edit_time");
                        String edittor = result.getString("edittor");
                        String projectName = result.getString("project_name");
                        int projectID = result.getInt("project_id");
                        String issueName = result.getString("issue_name");
                        int issueID = result.getInt("issue_id");
                        String detail = result.getString("detail");
                        System.out.println("Editted Time : " + edit_time + "\nEdittor : " + edittor + "\nProject Name : " + projectName + "   Project ID : " + projectID + "\nIssue Name : " + issueName + "   Issue ID : " + issueID + "\nDetail : \n" + detail);
                        System.out.println("");
                    }
                } catch (SQLException ex) {
                    System.out.println("displaychangelog error");
                }
                while (true) {
                    try {
                        System.out.println("Enter ‘1’ to return issue dashboard");
                        int userInput = input.nextInt();
                        if (userInput == 1) {
                            i.display();
                            function(i);
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please try again.");
                        input.next();
                    }
                }
            } else if (!option.equals("s") || !option.equals("c") || !option.equals("b") || !option.equals("h") || isInteger(option)) {
                System.out.println("Invalid input. Please try again.");
                System.out.println("");
                function(i);
            }

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }
    
    // recursion method for Issue Board if invalid input from user
    public static void function(Issuequeue i) throws IOException {
        Scanner input = new Scanner(System.in);
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
            try {
                Connection changelogSQL = new Connection();
                //? is unspecified value, to substitute in an integer, string, double or blob value.
                String changelog = "SELECT * FROM `changelog` WHERE `project_id`= ? ";
                //insert record of changelog
                pS = changelogSQL.getConnection().prepareStatement(changelog);
                pS.setInt(1, Project.getProjectID());
                result = pS.executeQuery();
                // create the mysql insert preparedstatement
                //.setString : placeholders that are only replaced with the actual values inside the system
                while (result.next()) {
                    String edit_time = result.getString("edit_time");
                    String edittor = result.getString("edittor");
                    String projectName = result.getString("project_name");
                    int projectID = result.getInt("project_id");
                    String issueName = result.getString("issue_name");
                    int issueID = result.getInt("issue_id");
                    String detail = result.getString("detail");
                    System.out.println("Editted Time : " + edit_time + "\nEdittor : " + edittor + "\nProject Name : " + projectName + "   Project ID : " + projectID + "\nIssue Name : " + issueName + "   Issue ID : " + issueID + "\nDetail : \n" + detail);
                    System.out.println("");
                }
            } catch (SQLException ex) {
                System.out.println("displaychangelog error");
            }
            while (true) {
                try {
                    System.out.println("Enter ‘1’ to return issue dashboard");
                    int userInput = input.nextInt();
                    if (userInput == 1) {
                        i.display();
                        function(i);
                        break;
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please try again.");
                    input.next();
                }
            }
        } else if (!option.equals("s") || !option.equals("c") || !option.equals("b") || !option.equals("h") || isInteger(option)) {
            System.out.println("Invalid input. Please try again.");
            System.out.println("");
            function(i);
        }

    }

    // change the old status to new status of selected issue
    public static void setIssueStatus(String newStatus) throws IOException {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Issue a = null;
        // the lowercase c refers to the object
        // :ID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM Issue c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Project
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
         // Issue the query and get a matching Issue
        TypedQuery<Issue> tq2 = em.createQuery(strQuery2, Issue.class);
        List<Project> projectList = new ArrayList<>();
        List<Issue> issueList = new ArrayList<>();
        String projectName = "";
        int projectID = -1;
        String issueTitle = "";
        int issueID = -1;
        String oldstatus = "";
        try {
            // Get matching project object, issue object and output
            projectList = tq.getResultList();
            issueList = tq2.getResultList();
            projectName = projectList.get(Project.getProjectID() - 1).getName();
            projectID = projectList.get(Project.getProjectID() - 1).getId();
            issueTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
            issueID = issueList.get(Issue.getIssueID() - 1).getId();
            oldstatus = issueList.get(Issue.getIssueID() - 1).getStatus();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
        }
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find issue and make changes
            a = em.find(Issue.class, Issue.getIssueID());
            a.setStatus(newStatus);
            Integer i = Math.toIntExact(new java.util.Date().getTime() / 1000);
            a.setStatusTimestamp(i);

            // Save the issue object
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
            String changelog = "INSERT INTO changelog (project_name,project_id,issue_name,issue_id,detail,edit_time,edittor) VALUES(?, ?,?,?,?,?,?)";

            // insert record of changelog 
            pS = userSQL.getConnection().prepareStatement(changelog);

            // create the mysql insert preparedstatement
            //.setString : placeholders that are only replaced with the actual values inside the system
            pS.setString(1, projectName);
            pS.setInt(2, projectID);
            pS.setString(3, issueTitle);
            pS.setInt(4, issueID);
            pS.setString(5, detail);
            pS.setString(6, newtimestamp);
            pS.setString(7, User.getLoginName());
            pS.executeUpdate(); 
            System.out.println("change log update (status) Successfully ");
        } catch (SQLException e) {
            System.out.println("Failed to update changelog (status). Try again!");
        }
    }
    
    // change the old issue title to new issue title of selected issue
    public static void setNewIssueTitle(String newTitle) throws IOException {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Issue a = null;
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM Issue c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Project
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        // Issue the query and get a matching Issue
        TypedQuery<Issue> tq2 = em.createQuery(strQuery2, Issue.class);
        List<Project> projectList = new ArrayList<>();
        List<Issue> issueList = new ArrayList<>();
        String projectName = "";
        int projectID = -1;
        String issueTitle = "";
        int issueID = -1;
        String oldTitle = "";
        try {
            // Get matching project object, issue object and output
            projectList = tq.getResultList();
            projectName = projectList.get(Project.getProjectID() - 1).getName();
            projectID = projectList.get(Project.getProjectID() - 1).getId();
            issueTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
            issueID = issueList.get(Issue.getIssueID() - 1).getId();
            oldTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
        }
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find Issue and make changes
            a = em.find(Issue.class, Issue.getIssueID());
            a.setTitle(newTitle);

            // Save the Issue object
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
            String changelog = "INSERT INTO changelog (project_name,project_id,issue_name,issue_id,detail,edit_time,edittor) VALUES(?, ?,?,?,?,?,?)";

            //insert record of changelog 
            pS = userSQL.getConnection().prepareStatement(changelog);

            // create the mysql insert preparedstatement
            //.setString : placeholders that are only replaced with the actual values inside the system
            pS.setString(1, projectName);
            pS.setInt(2, projectID);
            pS.setString(3, newTitle);
            pS.setInt(4, issueID);
            pS.setString(5, detail);
            pS.setString(6, newtimestamp);
            pS.setString(7, User.getLoginName());

            pS.executeUpdate(); 

            System.out.println("change log update (title) Successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to update changelog (title). Try again!");
        }
    }
    
    // change the old description to new description of selected issue
    public static void setNewDescription(String newDescription) throws IOException {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Issue a = null;
        // the lowercase c refers to the object
        // :ID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM Issue c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Project
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        // Issue the query and get a matching Issue
        TypedQuery<Issue> tq2 = em.createQuery(strQuery2, Issue.class);
        List<Project> projectList = new ArrayList<>();
        List<Issue> issueList = new ArrayList<>();
        String projectName = "";
        int projectID = -1;
        String issueTitle = "";
        int issueID = -1;
        String oldDescription = "";
        try {
            // Get matching project object, issue object and output
            projectList = tq.getResultList();
            projectName = projectList.get(Project.getProjectID() - 1).getName();
            projectID = projectList.get(Project.getProjectID() - 1).getId();
            issueTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
            issueID = issueList.get(Issue.getIssueID() - 1).getId();
            oldDescription = issueList.get(Issue.getIssueID() - 1).getDescriptionText();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            //  em.close();
        }
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find issue and make changes
            a = em.find(Issue.class, Issue.getIssueID());
            a.setDescriptionText(newDescription);

            // Save the issue object
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
            String changelog = "INSERT INTO changelog (project_name,project_id,issue_name,issue_id,detail,edit_time,edittor) VALUES(?, ?,?,?,?,?,?)";

            //insert record of changelog 
            pS = userSQL.getConnection().prepareStatement(changelog);

            // create the mysql insert preparedstatement
            //.setString : placeholders that are only replaced with the actual values inside the system
            pS.setString(1, projectName);
            pS.setInt(2, projectID);
            pS.setString(3, issueTitle);
            pS.setInt(4, issueID);
            pS.setString(5, detail);
            pS.setString(6, newtimestamp);
            pS.setString(7, User.getLoginName());

            pS.executeUpdate();

            System.out.println("change log update (description) Successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to update changelog (description). Try again!");
        }
    }
    
    // change the old assignee name to new assignee name or add on assignee name of selected issue
    public static void setNewAssignee(String newAssignee) throws IOException {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Issue a = null;
        // the lowercase c refers to the object
        // :ID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM Issue c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Project
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        // Issue the query and get a matching Issue
        TypedQuery<Issue> tq2 = em.createQuery(strQuery2, Issue.class);
        List<Project> projectList = new ArrayList<>();
        List<Issue> issueList = new ArrayList<>();
        String projectName = "";
        int projectID = -1;
        String issueTitle = "";
        int issueID = -1;
        String oldAssignee = "";
        try {
            // Get matching project object, issue project and output
            projectList = tq.getResultList();
            projectName = projectList.get(Project.getProjectID() - 1).getName();
            projectID = projectList.get(Project.getProjectID() - 1).getId();
            issueTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
            issueID = issueList.get(Issue.getIssueID() - 1).getId();
            oldAssignee = issueList.get(Issue.getIssueID() - 1).getAssignee();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            //  em.close();
        }
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find issue and make changes
            a = em.find(Issue.class, Issue.getIssueID());
            a.setAssignee(newAssignee);

            // Save the issue object
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
            String changelog = "INSERT INTO changelog (project_name,project_id,issue_name,issue_id,detail,edit_time,edittor) VALUES(?, ?,?,?,?,?,?)";

            //insert record of changelog 
            pS = userSQL.getConnection().prepareStatement(changelog);

            // create the mysql insert preparedstatement
            //.setString : placeholders that are only replaced with the actual values inside the system
            pS.setString(1, projectName);
            pS.setInt(2, projectID);
            pS.setString(3, issueTitle);
            pS.setInt(4, issueID);
            pS.setString(5, detail);
            pS.setString(6, newtimestamp);
            pS.setString(7, User.getLoginName());

            pS.executeUpdate(); 

            System.out.println("change log update (assignee) Successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to update changelog (assignee). Try again!");
        }
    }
    
    // change the old priority to new priority of selected issue
    public static void setNewPriority(int newPriority) throws IOException {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Issue a = null;
        // the lowercase c refers to the object
        // :ID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM Issue c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Project
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        // Issue the query and get a matching Issue
        TypedQuery<Issue> tq2 = em.createQuery(strQuery2, Issue.class);
        List<Project> projectList = new ArrayList<>();
        List<Issue> issueList = new ArrayList<>();
        String projectName = "";
        int projectID = -1;
        String issueTitle = "";
        int issueID = -1;
        int oldPrio = -1;
        try {
            // Get matching project object, issue object and output
            projectList = tq.getResultList();
            projectName = projectList.get(Project.getProjectID() - 1).getName();
            projectID = projectList.get(Project.getProjectID() - 1).getId();
            issueTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
            issueID = issueList.get(Issue.getIssueID() - 1).getId();
            oldPrio = issueList.get(Issue.getIssueID() - 1).getPriority();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            //  em.close();
        }
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find issue and make changes
            a = em.find(Issue.class, Issue.getIssueID());
            a.setPriority(newPriority);

            // Save the issue object
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
            String changelog = "INSERT INTO changelog (project_name,project_id,issue_name,issue_id,detail,edit_time,edittor) VALUES(?, ?,?,?,?,?,?)";

            //insert record of changelog 
            pS = userSQL.getConnection().prepareStatement(changelog);

            // create the mysql insert preparedstatement
            //.setString : placeholders that are only replaced with the actual values inside the system
            pS.setString(1, projectName);
            pS.setInt(2, projectID);
            pS.setString(3, issueTitle);
            pS.setInt(4, issueID);
            pS.setString(5, detail);
            pS.setString(6, newtimestamp);
            pS.setString(7, User.getLoginName());

            pS.executeUpdate(); 

            System.out.println("change log update (assignee) Successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to update changelog (assignee). Try again!");
        }
    }
    
    // change the old tag to new tag of selected issue
    public static void setNewTag(ArrayList newTag) throws IOException {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        Issue a = null;
        // the lowercase c refers to the object
        // :ID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM Issue c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Project
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
         // Issue the query and get a matching Issue
        TypedQuery<Issue> tq2 = em.createQuery(strQuery2, Issue.class);
        List<Project> projectList = new ArrayList<>();
        List<Issue> issueList = new ArrayList<>();
        String projectName = "";
        int projectID = -1;
        String issueTitle = "";
        int issueID = -1;
        ArrayList<String> oldTag = new ArrayList<>();

        try {
            // Get matching project object, issue object and output
            projectList = tq.getResultList();
            projectName = projectList.get(Project.getProjectID() - 1).getName();
            projectID = projectList.get(Project.getProjectID() - 1).getId();
            issueTitle = issueList.get(Issue.getIssueID() - 1).getTitle();
            issueID = issueList.get(Issue.getIssueID() - 1).getId();
            oldTag = issueList.get(Issue.getIssueID() - 1).getTag();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            //  em.close();
        }
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Find issue and make changes
            a = em.find(Issue.class, Issue.getIssueID());
            a.setTag(newTag);

            // Save the issue object
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
            String changelog = "INSERT INTO changelog (project_name,project_id,issue_name,issue_id,detail,edit_time,edittor) VALUES(?, ?,?,?,?,?,?)";

            //insert record of changelog 
            pS = userSQL.getConnection().prepareStatement(changelog);

            // create the mysql insert preparedstatement
            //.setString : placeholders that are only replaced with the actual values inside the system
            pS.setString(1, projectName);
            pS.setInt(2, projectID);
            pS.setString(3, issueTitle);
            pS.setInt(4, issueID);
            pS.setString(5, detail);
            pS.setString(6, newtimestamp);
            pS.setString(7, User.getLoginName());

            pS.executeUpdate(); 

            System.out.println("change log update (assignee) Successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to update changelog (assignee). Try again!");
        }
    }
}
