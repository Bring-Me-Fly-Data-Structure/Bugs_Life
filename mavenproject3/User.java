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
import java.util.List;
import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;
import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userid",
    "username",
    "password"
})
@Entity
@Table(name = "user")
public class User implements Serializable{
    


  //  @OneToMany(mappedBy="userComment",fetch = FetchType.EAGER)
    @JsonIgnore
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_ID")  
    @JsonProperty("userid")
    private Integer userid;
    
    @Column(name = "username") 
    @JsonProperty("username")
    private String username;
    
    @Column(name = "password") 
    @JsonProperty("password")
    private String password;

    @Transient
    @JsonIgnore
    public static String loginName;
    
    @Transient    
    @JsonIgnore
    public static boolean login_status = false;
    
    @Transient
    @JsonIgnore
    public static int numberSolved;

    @JsonIgnore
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernateTest");

    public User() {
    }

    @JsonCreator 
    public User(@JsonProperty("userid") Integer userid, @JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    
    @JsonProperty("userid")
    public Integer getUserid() {
        return userid;
    }

    @JsonProperty("userid")
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    public static String getLoginName() {
        return loginName;
    }

    public static void setLoginName(String loginName) {
        User.loginName = loginName;
    }

    public static boolean isLogin_status() {
        return login_status;
    }

    public static void setLogin_status(boolean login_status) {
        User.login_status = login_status;
    }

    public static int getNumberSolved() {
        return numberSolved;
    }

    public static void setNumberSolved(int numberSolved) {
        User.numberSolved = numberSolved;
    }

    //methods 
    public static void register() throws IOException {

        Scanner in = new Scanner(System.in);
//        ObjectMapper mapper = new ObjectMapper();
//        //read file
//        Example root = mapper.readValue(new File("C:\\Users\\richi\\Desktop\\UM folder\\Y1S2\\WIA1002 DS\\assignment\\localDatabase\\data6.json"), Example.class);
//        List<User> userList = root.getUsers();
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    	String strQuery = "SELECT c FROM User c WHERE c.userid IS NOT NULL";
    	
    	// Issue the query and get a matching User
    	TypedQuery<User> tq = em.createQuery(strQuery, User.class);
    	List<User> userList = new ArrayList<>();
    	try {
    		// Get matching user objects
    		userList = tq.getResultList();
 
    	}
    	catch(NoResultException ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		//em.close();
    	}


        System.out.println("Enter username: ");
        String username = in.nextLine();

        while (true) {
            //obtain the first object from userlist with same username
            User obj = findUsername(userList, username);

            if (userList.contains(obj)) {
                System.out.println("Username has been taken, please input new username: ");
                username = in.next();
            } else {
                System.out.println("Enter password: ");
                String password = in.next();
//                User a = new User(userList.size() + 1, username, password);
                  // Create and set values for new user
                  User b = new User( username, password);

                //Update value in object
//                root.getUsers().add(a);
//                String json = mapper.writeValueAsString(root);
                
                
                // The EntityManager class allows operations such as create, read, update, delete

                // Used to issue transactions on the EntityManager
                EntityTransaction et = null;

                try {
                    // Get transaction and start
                    et = em.getTransaction();
                    et.begin();


                    // Save the user object
                    em.persist(b);
                    et.commit();
                } catch (Exception ex) {
                    // If there is an exception rollback changes
                    if (et != null) {
                        et.rollback();
                    }
                    ex.printStackTrace();
                } finally {
                    System.out.println("Successfully registered into database.");
                    // Close EntityManager
                    em.close();
                }
//                //store
//                try (FileWriter file = new FileWriter("C:\\Users\\richi\\Desktop\\UM folder\\Y1S2\\WIA1002 DS\\assignment\\localDatabase\\data6.json")) {
//                    file.write(json);
//                    System.out.println("Successfully updated json object to file...!!");
//                }
                break;
            }

        }
    }

    public static void login() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = input.nextLine();
        System.out.println("Enter password: ");
        String password = input.nextLine();
        ObjectMapper objM = new ObjectMapper();
        
    	EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    	
    	// the lowercase c refers to the object
    	// :custID is a parameterized query thats value is set below
    	String strQuery = "SELECT c FROM User c WHERE c.userid IS NOT NULL";
    	
    	// Issue the query and get a matching User
    	TypedQuery<User> tq = em.createQuery(strQuery, User.class);
    	List<User> userList = new ArrayList<>();
    	try {
    		// Get matching user objects
    		userList = tq.getResultList();
 
    	}
    	catch(NoResultException ex) {
    		ex.printStackTrace();
    	}
    	finally {
    		em.close();
    	}
//            Example base = objM.readValue(new File("C:\\Users\\richi\\Desktop\\UM folder\\Y1S2\\WIA1002 DS\\assignment\\localDatabase\\data6.json"), Example.class);
//            List<User> userList = base.getUsers();
//
              //obtain the first object from userlist with same username
             User obj = findUsername(userList, username);

            if (userList.contains(obj)) {
                if (obj.getPassword().equals(password)) {
                    System.out.println("Welcome back " + userList.get(userList.indexOf(obj)).getUsername());
                    setLogin_status(true);
                    setLoginName(userList.get(userList.indexOf(obj)).getUsername());
                } else {
                    System.out.println("Incorrect password");
                }
            } else {
                 System.out.println("username not found, Please register");
                Register();
            }
    }
            
            public static void Register ()throws IOException {
                Scanner input = new Scanner(System.in);
                System.out.println("Do you want register ? Enter 'y' if yes, 'n' if no");
                String Doregister = input.next();
                if (Doregister.equals("y")) {
                    register();
                }else if(Doregister.equals("n")){
                    System.exit(0);
                }else if(!Doregister.equals("y")&&!Doregister.equals("n")){
                    System.out.println("Invalid input. Please try again");
                    System.out.println("");
                    Register();
                }
            }

    public static void logout() {
        setLogin_status(false);
        setLoginName(null);
    }

    public static User findUsername(List<User> list, String username) {
        return list.stream().filter(userObj -> username.equals(userObj.getUsername())).findFirst().orElse(null);
    }

}
