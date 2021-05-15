/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author richi
 */
public class jsonToPOJO {
    public static void main(String[] args) throws IOException {
         ObjectMapper objM = new ObjectMapper();
         try{

             Example base = objM.readValue(new File("C:\\Users\\richi\\Desktop\\UM folder\\Y1S2\\WIA1002 DS\\assignment\\data.json"), Example.class) ;
             List<Issue> a =base.getProjects().get(0).getIssues();
             Issuequeue i = new Issuequeue();

//             for (int j = 0; j < a.size(); j++) {
//                 i.offer(a.get(j));
//             }
             i.offer(a);
             i.display(2);
         }catch (JsonProcessingException ex) {
            System.out.println(" file input error");
        }

    }
}
