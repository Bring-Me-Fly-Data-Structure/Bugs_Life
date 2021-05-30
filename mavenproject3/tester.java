/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavenproject3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author richi
 */
public class tester {
    
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter '1' to login, '2' to register");
        int userInput = in.nextInt();
        if(userInput==1){
            User.login();
        }else if(userInput==2){
            User.register();
            User.login();
        } 
        
        while(!User.isLogin_status()){
            System.out.println("Please login to proceed....");
            User.login();
        }
        
        Project.displayProject();

    }


    
    

    
}
