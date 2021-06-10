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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "comment_id",
    "text",
    "react",
    "timestamp",
    "user"
})

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @JsonIgnore
    @Transient
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateTest");

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    @JsonProperty("comment_id")
    private Integer commentId;

    @Column(name = "comment_text")
    @JsonProperty("text")
    private String text;

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY, cascade=CascadeType.PERSIST)
    @JsonProperty("react")
    private List<React2> react = new ArrayList<>();

    @Column(name = "timestamp")
    @JsonProperty("timestamp")
    private Integer timestamp;

    @Column(name = "user")
    @JsonProperty("user")
    private String user;

    @Transient
    @JsonIgnore
    private java.util.Date timestampformat;

    @Transient
    @JsonIgnore
    private static int commentID;

    public Comment() {
    }

    @JsonCreator
    //constructor for data.json 
    public Comment(@JsonProperty("comment_id") Integer commentId, @JsonProperty("text") String text, @JsonProperty("react") ArrayList<React2> react, @JsonProperty("timestamp") Integer timestamp, @JsonProperty("user") String user) {
        this.commentId = commentId;
        this.text = text;
        this.react = react;
        this.timestamp = timestamp;
        this.timestampformat = new java.util.Date((long) timestamp * 1000);
        this.user = user;
        if (react.size() == 3) {
            this.react.add(new React2("smile", 0));
            this.react.add(new React2("sad", 0));
            this.react.add(new React2("love", 0));
            this.react.add(new React2("cry", 0));
        } else if (react.size() == 2) {
            this.react.add(new React2("thumb up", 0));
            this.react.add(new React2("smile", 0));
            this.react.add(new React2("sad", 0));
            this.react.add(new React2("love", 0));
            this.react.add(new React2("cry", 0));
        }

    }

    //constructor for user input
    public Comment(String text, String user) {
        this.text = text;
        this.user = user;
        this.timestampformat = new java.util.Date();
        Integer i = Math.toIntExact(new java.util.Date().getTime() / 1000);
        this.timestamp = i;
    }

    @JsonProperty("comment_id")
    public Integer getCommentId() {
        return commentId;
    }

    @JsonProperty("comment_id")
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("react")
    public List<React2> getReact() {
        return react;
    }

    @JsonProperty("react")
    public void setReact(List<React2> react) {
        this.react = react;
    }

    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public static int getCommentID() {
        return commentID;
    }

    public static void setCommentID(int commentID) {
        Comment.commentID = commentID;
    }

    @PostLoad //it will auto call when reading data from mysql 
    public void SQLtoPOJO() {
        this.timestampformat = new java.util.Date((long) this.timestamp * 1000);
    }

    public Date getTimestampformat() {
        return timestampformat;
    }

    public void setTimestampformat(Date timestampformat) {
        this.timestampformat = timestampformat;
    }

    public String changeDateFormat() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        return ft.format(getTimestampformat());
    }

    @Override
    public String toString() {

        String result = "     Created on: " + changeDateFormat() + "    By: " + user + "\n" + text + "\n$$ " + "\n";
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM React2 c WHERE c.comment.commentId = :commentId";

        // Issue the query and get a matching Customer
        TypedQuery<React2> tq = em.createQuery(strQuery, React2.class);
        tq.setParameter("commentId", commentId);
        List<React2> reactList = new ArrayList<>();
        try {
            // Get matching customer object and output
            reactList = tq.getResultList();
            if (reactList.get(0).getCount() != 0) {
                result += String.format("%s", "Angry ") + "x" + reactList.get(0).getCount() + "  ";
            }
            if (reactList.get(1).getCount() != 0) {
                result += String.format("%s", "Happy ") + "x" + reactList.get(1).getCount() + "  ";
            }
            if (reactList.get(2).getCount() != 0) {
                result += String.format("%s", "Thumbs Up ") + "x" + reactList.get(2).getCount() + "  ";
            }
            if (reactList.get(3).getCount() != 0) {
                result += String.format("%s", "Smile ") + "x" + reactList.get(3).getCount() + "  ";
            }
            if (reactList.get(4).getCount() != 0) {
                result += String.format("%s", "Sad ") + "x" + reactList.get(4).getCount() + "  ";
            }
            if (reactList.get(5).getCount() != 0) {
                result += String.format("%s", "Love ") + "x" + reactList.get(5).getCount() + "  ";
            }
            if (reactList.get(6).getCount() != 0) {
                result += String.format("%s", "Cry ") + "x" + reactList.get(6).getCount() + "  ";
            }
       
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

        return result;
    }

    public static void addComment() throws IOException {
        Scanner input = new Scanner(System.in);

       System.out.println("Write something: (Enter '$undo' for undo, '$redo' for redo, '$end' for end)");        
        String sentence = "";
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
                System.out.println("         Comment added");
                System.out.println("------------------------------");
                 if(a.size()>1){
                        sentence=sentence+a.get(0)+"\n";
                        for (int i = 1; i < a.size()-1; i++) {
                            sentence = sentence+ a.get(i)+"\n";
                        }
                        sentence=sentence+a.get(a.size()-1);
                    }else{
                        sentence=sentence+a.get(0);
                    }

                    System.out.println(sentence);
                    System.out.println("------------------------------");
        String username = User.getLoginName();


        // The EntityManager class allows operations such as create, read, update, delete
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        // Used to issue transactions on the EntityManager
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

        } catch (NoResultException ex) {
            ex.printStackTrace();
        } 

        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new customer
            Comment m = new Comment(sentence, username);
            m.setIssue(projectList.get(Project.getProjectID() - 1).getIssues().stream().filter(issue -> issue.getId()==Issue.getIssueID()).findFirst().get());
            em.persist(m);

            // Save the customer object
            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            System.out.println("add comment sql error");
            ex.printStackTrace();

        } finally {
            // Close EntityManager
            System.out.println("successfuly add comment into database");
            em.close();
        }
        initializeReact();
    }

       public static void initializeReact() {
        // The EntityManager class allows operations such as create, read, update, delete
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        // Used to issue transactions on the EntityManager
        EntityTransaction et = null;
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM Issue c WHERE c.id IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        TypedQuery<Issue> tq2 = em.createQuery(strQuery2, Issue.class);
        List<Project> projectList = new ArrayList<>();
        List<Issue> issueList = new ArrayList<>();

        try {
            // Get matching customer object and output
            projectList = tq.getResultList();
            issueList = tq2.getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } 
        try {
            // Get transaction and start
            et = em.getTransaction();
            et.begin();

            // Create and set values for new customer
            React2 angry = new React2("angry", 0);
            React2 happy = new React2("happy", 0);
            React2 thumb = new React2("thumb up", 0);
            React2 smile = new React2("smile", 0);
            React2 sad = new React2("sad", 0);
            React2 love = new React2("love", 0);
            React2 cry = new React2("cry", 0);

            int commentIndex = issueList.get(Issue.getIssueID() - 1).getComments().size() - 1;
            angry.setComment(issueList.get(Issue.getIssueID() - 1).getComments().get(commentIndex));
            happy.setComment(issueList.get(Issue.getIssueID() - 1).getComments().get(commentIndex));
            thumb.setComment(issueList.get(Issue.getIssueID() - 1).getComments().get(commentIndex));
            smile.setComment(issueList.get(Issue.getIssueID() - 1).getComments().get(commentIndex));
            sad.setComment(issueList.get(Issue.getIssueID() - 1).getComments().get(commentIndex));
            love.setComment(issueList.get(Issue.getIssueID() - 1).getComments().get(commentIndex));
            cry.setComment(issueList.get(Issue.getIssueID() - 1).getComments().get(commentIndex));
            // Save the customer object
            em.persist(angry);
            em.persist(happy);
            em.persist(thumb);
            em.persist(smile);
            em.persist(sad);
            em.persist(love);
            em.persist(cry);

            et.commit();
        } catch (Exception ex) {
            // If there is an exception rollback changes
            if (et != null) {
                et.rollback();
            }
            System.out.println("add react sql error");
            ex.printStackTrace();

        } finally {
            // Close EntityManager
            em.close();
        }
    }

}
