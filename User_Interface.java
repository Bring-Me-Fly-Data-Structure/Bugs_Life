
import java.util.Scanner;
import java.sql.*;

public class User_Interface {

    private static PreparedStatement pS;
    private static ResultSet result;
    private static String user_name;
    private static int ID;
    //method to register
    
    public static void register() {
        //user register

        String name, email, pass, pass2;

        Scanner s = new Scanner(System.in);
        System.out.print("\nEnter username: ");
        name = s.nextLine();

        while (checkUsername(name)) {
            System.out.println("username has been taken, please try another one.");
            System.out.print("\nEnter another username: ");
            name = s.nextLine();
        }

        email = email();
        System.out.println();
        System.out.println("User name: " + name);
        System.out.println("Email: " + email);
        pass = password();
        Register(name, email, pass);
        System.out.println();
       // System.out.println("Please log in to you account");
       // login();
    }

// method to confirm the password
    public static String password() {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter password: ");
        String pass = s.nextLine();
        System.out.print("Reconfirm the password: ");
        String pass2 = s.nextLine();
        while (!pass.equals(pass2)) {
            System.out.println("Password does not match, please try again");
            System.out.print("Enter password: ");
            pass = s.nextLine();
            System.out.print("Reconfirm the password: ");
            pass2 = s.nextLine();
        }
        return pass;
    }

    //check whether the email fullfil requirements
    public static String email() {
        Scanner s = new Scanner(System.in);
        System.out.println("Your email should be in the form of user@emailhost.com");
        System.out.println("Eg: abc@ ymail.com");
        System.out.println("Please take note that user should not contain character '@' ");
        System.out.print("Enter email: ");
        String email = s.nextLine();

        while (checkmail(email)) {
            System.out.println("\nInvalid form of email");
            System.out.println("Please try again");
            System.out.print("Enter email: ");
            email = s.nextLine();
        }

        while (checkEmail(email)) {
            System.out.println("\nThe email has been registered");
            System.out.println("Please use another email ");
            System.out.print("Enter email: ");
            email = s.nextLine();
            while (checkmail(email)) {
                System.out.println("\nInvalid form of email");
                System.out.println("Please try again");
                System.out.print("Enter email: ");
                email = s.nextLine();
            }
        }

        return email;

    }

    //check whether email fullfil requirement
    public static boolean checkmail(String email) {
        boolean result = false;
        boolean check1 = false;
        int j = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@' && i > 0) {
                j += 1;
            }
        }
        if (j == 1) {
            check1 = true;
        }
        int check = 0;
        boolean check2 = true;
        if (check1 && check2) {
            result = false;
        } else {
            result = true;
        }
        return result;
    }

    public static void Register(String name, String email, String password) {
        //connect register form to MySQL
        Connection a = new Connection();
        try {

            //? is unspecified value, to substitute in an integer, string, double or blob value.
            String register = "INSERT INTO user (username,email,password) VALUES(?, ?, ?)";

            //insert record of register 
            pS = a.getConnection().prepareStatement(register);

            // create the mysql insert preparedstatement
            //.setString : placeholders that are only replaced with the actual values inside the system
            pS.setString(1, name);
            pS.setString(2, email);
            pS.setString(3, password);

            pS.executeUpdate(); //return int value

            System.out.println("Registered Successfully ");

        } catch (SQLException e) {
            System.out.println("Failed to register. Try again!");
        }
        try {

            String sql = "SELECT `user_ID` FROM `user` WHERE `email`='" + email + "'";
            pS = a.getConnection().prepareStatement(sql);
            result = pS.executeQuery();

            while (result.next()) {
                ID = result.getInt("user_ID");
            }

        } catch (SQLException ex) {
            System.out.println("get user id error");
        }
        try {

            String upload = "INSERT INTO `channel_subscription`(`channel_id`, `subscribers`) VALUES (" + ID + "," + 0 + ")";
            pS = a.getConnection().prepareStatement(upload);
            pS.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("initialize subs error");
        }

    }

    //method to check duplicate email
    public static boolean checkEmail(String email) {
        boolean dupEmail = false;
        try {
            Connection b = new Connection();

            String sql = "SELECT * FROM `user` WHERE `email`='" + email + "'";
            pS = b.getConnection().prepareStatement(sql);
            //  pS.setString(1, email);

            result = pS.executeQuery();

            if (result.next()) {
                //exist
                dupEmail = true;
            } else {
                dupEmail = false;
            }

        } catch (SQLException ex) {
            System.out.println("checkRegister error");
        }
        return dupEmail;
    }

    //method to check duplicate username
    public static boolean checkUsername(String username) {
        boolean dupUser = false;
        try {
            Connection c = new Connection();

            String sql = "SELECT * FROM `user` WHERE `username`='" + username + "'";
            pS = c.getConnection().prepareStatement(sql);
            // pS.setString(1, username);

            result = pS.executeQuery();

            if (result.next()) {
                //exist
                dupUser = true;
            } else {
                dupUser = false;
            }

        } catch (SQLException ex) {
            System.out.println("checkRegister error");
        }
        return dupUser;
    }

    //method to login
    public static void login() {
        Scanner s = new Scanner(System.in);
        try {
            Connection a = new Connection();

            String login = "SELECT * FROM `user` WHERE `email`= ? AND `password`= ?";
            pS = a.getConnection().prepareStatement(login);
            String email, pass;
            System.out.print("\nEnter email: ");
            email = s.nextLine();
            System.out.print("Enter password: ");
            pass = s.nextLine();

            pS.setString(1, email);
            pS.setString(2, pass);

            result = pS.executeQuery();

            if (result.next()) {
                //exist
                user_name = result.getString("username");
                ID = result.getInt("user_ID");
                System.out.println("\n****************************************");
                System.out.println("Welcome back " + user_name + "!");
                System.out.println("****************************************");
                // login_status = true;
                //tester.homepage();

            } else {
                // nope
                System.out.println("Invalid pass or username");
            }

        } catch (SQLException ex) {
            System.out.println("connection error");
        }
    }

}
