
package mavenproject3;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

public class ReportGeneration {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("hibernateTest");

    //weekly report generation method
    public static void generateReport() {
        long numResolved = 0;
        long numInProgress = 0;
        long numOpen = 0;

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        // the lowercase c refers to the object
        String strQuery = "SELECT c FROM Project c WHERE c.id IS NOT NULL";
        String strQuery2 = "SELECT c FROM User c WHERE c.userid IS NOT NULL";

        // Issue the query and get a matching project, user
        TypedQuery<Project> tq = em.createQuery(strQuery, Project.class);
        TypedQuery<User> tq2 = em.createQuery(strQuery2, User.class);
        List<Project> projectList = new ArrayList<>();
        List<User> listU = new ArrayList<>();
        try {
            // Get matching project and user objects and output
            projectList = tq.getResultList();
            listU = tq2.getResultList();
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
                    numSolvedbyAssignee[j] += findTopPerformer(projectList.get(i).getIssues(), usersName.get(j), "Resolved");
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
            System.out.println("\n***************Weekly Report***************");
            System.out.println("From " + changeDateFormat(monday) + " to " + changeDateFormat(new Date()) + "\n*******************************************\n");
            System.out.println("Number of resolved issues: " + numResolved);
            System.out.println("Number of In Progress issues: " + numInProgress);
            System.out.println("Number of unresolved issues: " + numUnresolved);
            if (max == 0) {
                System.out.println("Top performer of the week: nobody");
            } else {
                System.out.println("Top performer of the week: " + usersName.get(maxIndex));
            }
            if (outer.isEmpty()) {
                System.out.println("Most frequent label of the week: no label found");
            } else {
                //get the occurences for each tag
                Map<String, Long> occurrences = outer.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
                long maxKey = Collections.max(occurrences.values());
                List<String> keys = new ArrayList<>();
                for (Entry<String, Long> entry : occurrences.entrySet()) {
                    if (entry.getValue() == maxKey) {
                        keys.add(entry.getKey());
                    }
                }
                System.out.print("Most frequent label of the week: ");
                for (int i = 0; i < keys.size(); i++) {
                    System.out.print("\"" + keys.get(i) + "\" ");
                }
            }
        } catch (NoResultException ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    //method to count total number of a specific status in all issues
    public static long findStatus(List<Issue> list, String status) {
        return list.stream().filter(listObj -> status.equals(listObj.getStatus())).count();
    }

    //method to count total number of a specific status in all issues in current week
    public static long findWeeklyStatus(List<Issue> list, String status) {
        return list.stream().filter(listObj -> status.equals(listObj.getStatus()) && (checkThisWeek(listObj.getStatusTimestamp()))).count();
    }

    //method to count total number of resolved issues for a user in current week
    public static long findTopPerformer(List<Issue> list, String usersname, String status) {
        return list.stream().filter(listObj -> status.equals(listObj.getStatus()) && (listObj.getAssignee().equals(usersname)) && (checkThisWeek(listObj.getStatusTimestamp()))).count();
    }

    //method to find all the tag used in current week
    public static void findTag(List<String> outer, List<Issue> list) {
        list.stream().filter(listObj -> checkThisWeek(listObj.getTimestamp())).forEach(listObj -> outer.addAll(listObj.getTag()));
    }

    //method to check if the timestamp is this week or not
    public static boolean checkThisWeek(long unixtimestamp) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date monday = c.getTime();
        Date nextMonday = new Date(monday.getTime() + 7 * 24 * 60 * 60 * 1000);
        Date today = new Date((long) unixtimestamp * 1000);

        return today.after(monday) && today.before(nextMonday);
    }

    //method to change date format
    public static String changeDateFormat(Date a) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        return ft.format(a);
    }

}
