/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mavenproject3;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author richi
 */
public class ReportGeneration {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateTest");
    public static void main(String[] args) throws IOException {
        long numResolved = 0;
        long numInProgress = 0;
        long numOpen = 0;

//        // long numFront = 0;
//        ObjectMapper mapper = new ObjectMapper();
//        //read file
//        Example root = mapper.readValue(new File("C:\\Users\\richi\\Desktop\\UM folder\\Y1S2\\WIA1002 DS\\assignment\\localDatabase\\final.json"), Example.class);
                EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        // :custID is a parameterized query thats value is set below
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM User c WHERE c.userid IS NOT NULL";

        // Issue the query and get a matching Customer
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        TypedQuery<User> tq2 = em.createQuery(strQuery2, User.class);
        List<Project> projectList = new ArrayList<>();
        List<User> listU = new ArrayList<>();
        try {
            // Get matching customer object and output
            projectList = tq.getResultList();
            listU = tq2.getResultList();
            //testing
//            System.out.println("***********");
//            System.out.println(projectList.get(0).getIssues().get(0).getComments().get(0).getReact());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }

        ArrayList<String> usersName = new ArrayList<>();
        ArrayList<String> outer = new ArrayList<>();
        //store user name as a list
        for (int i = 0; i < listU.size(); i++) {
            usersName.add(listU.get(i).getUsername());
        }

        //array of number of issue solved, each index here is same with username arraylist index
        long[] numSolvedbyAssignee = new long[usersName.size()];

        for (int i = 0; i < projectList.size(); i++) {
            numResolved += findWeeklyStatus(projectList.get(i).getIssues(), "Resolved");
            numInProgress += findWeeklyStatus(projectList.get(i).getIssues(), "In Progress");
            numOpen += findWeeklyStatus(projectList.get(i).getIssues(), "Open");
            findTag(outer, projectList.get(i).getIssues());

            //loop through each issue, find number of issue solved by each user, store it in array 
            for (int j = 0; j < usersName.size(); j++) {
                numSolvedbyAssignee[j] = findTopPerformer(projectList.get(j).getIssues(), usersName.get(j), "Resolved");
            }
        }

        //find the index of highest solved , then the use the index to determine the top performer
        long max = numSolvedbyAssignee[0];
        int maxIndex = 0;
        for (int i = 1; i < numSolvedbyAssignee.length; i++) {
            if (numSolvedbyAssignee[i] > max) {
                max = numSolvedbyAssignee[i];
                maxIndex = i;
            }
        }

        //report output
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date monday = c.getTime();
        long numUnresolved = numOpen + numInProgress;
        System.out.println("***************Weekly Report***************");
        System.out.println("From " + changeDateFormat(monday) + " to " + changeDateFormat(new Date()) + "\n*******************************************");
        System.out.println("Number of resolved issues: " + numResolved);
        System.out.println("Number of In Progress issues: " + numInProgress);
        System.out.println("Number of unresolved issues: " + numUnresolved);
        if (max == 0) {
            System.out.println("Top performer of the week: nobody");
        } else {
            System.out.println("Top performer of the week: " + usersName.get(maxIndex));
        }
        //   System.out.println(outer.toString());
        if (outer.isEmpty()) {
            System.out.println("Most frequent label of the week: no label found");
        } else {
            Map<String, Long> occurrences = outer.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
            long maxKey = Collections.max(occurrences.values());
            List<String> keys = new ArrayList<>();
            for (Entry<String, Long> entry : occurrences.entrySet()) {
                if (entry.getValue() == maxKey) {
                    keys.add(entry.getKey());
                }
            }
            System.out.println(occurrences);
            System.out.print("Most frequent label of the week: ");
            for (int i = 0; i < keys.size(); i++) {
                System.out.print("\"" + keys.get(i) + "\" ");
            }
        }
    }

    public static long findStatus(List<Issue> list, String status) {
        return list.stream().filter(listObj -> status.equals(listObj.getStatus())).count();
    }

    public static long findWeeklyStatus(List<Issue> list, String status) {
        return list.stream().filter(listObj -> status.equals(listObj.getStatus()) && (checkThisWeek(listObj.getTimestamp()))).count();
    }

    public static long findTopPerformer(List<Issue> list, String usersname, String status) {

        return list.stream().filter(listObj -> status.equals(listObj.getStatus()) && (listObj.getAssignee().equals(usersname)) && (checkThisWeek(listObj.getTimestamp()))).count();
    }

    public static void findTag(List<String> outer, List<Issue> list) {

        // list.stream().filter(listObj -> listObj.getTag());
        //list.stream().forEach(listObj -> outer.addAll(listObj.getTag()));
        list.stream().filter(listObj -> checkThisWeek(listObj.getTimestamp())).forEach(listObj -> outer.addAll(listObj.getTag()));
    }

    public static boolean checkThisWeek(long unixtimestamp) {
        Calendar c = Calendar.getInstance();
//        System.out.println(c.getTime().getTime()/1000);
//        System.out.println(new Date().getTime()/1000);
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date monday = c.getTime();
        Date nextMonday = new Date(monday.getTime() + 7 * 24 * 60 * 60 * 1000);
        //  System.out.println(nextMonday);
        Date today = new Date((long) unixtimestamp * 1000);

        return today.after(monday) && today.before(nextMonday);
    }

    public static String changeDateFormat(Date a) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        return ft.format(a);
    }

}
