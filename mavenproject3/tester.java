/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavenproject3;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author richi
 */
public class tester {

    public static void main(String[] args) throws IOException {
        homepage();
    }

    public static void homepage() throws IOException {
        Scanner in = new Scanner(System.in);

        while (true) {

            try {
                System.out.println("Enter '1' to login, '2' to register");
                int userInput = in.nextInt();
                if (userInput == 1) {
                    User.login();
                    break;
                } else if (userInput == 2) {
                    User.register();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid value!\n");
                in.next();
            }
        }

        while (!User.isLogin_status()) {
            System.out.println("Please login to proceed....");
            User.login();
        }
        if (User.getLoginName().equals("admin")) {
            adminHomepage();
        } else {
            Project.displayProject();
        }
    }

    public static void adminHomepage() throws IOException {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("\nEnter '1' to view access control list\nOr '2' to trace the abnormal activities\n'3' to generate report\n'4' to Import or Export JSON data\n'5' to view projects and issues");

                int userInput = in.nextInt();
                if (userInput == 1) {
                    access_control.adminLogReport();
                    adminHomepage();
                    break;
                } else if (userInput == 2) {
                    adminlog.adminLogReport();
                    adminHomepage();
                    break;
                } else if (userInput == 3) {
                    ReportGeneration.generateReport();
                    adminHomepage();
                    break;
                } else if (userInput == 4) {
                    JSON_IMPORT_EXPORT.importExport();
                    adminHomepage();
                    break;
                } else if (userInput == 5) {
                    Project.displayProject();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid value!\n");
                in.next();
            }
        }
    }

}
