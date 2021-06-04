
package mavenproject3;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.ArrayList;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "projects",
    "users"
})

public class Example {

    @JsonProperty("projects")
    private List<Project> projects = new ArrayList<>();
    @JsonProperty("users")
    private List<User> users = new ArrayList<>();

    @JsonProperty("projects")
    public List<Project> getProjects() {
        return projects;
    }

    @JsonProperty("projects")
    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @JsonProperty("users")
    public List<User> getUsers() {
        return users;
    }

    @JsonProperty("users")
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void sortAplhanumerically(List<Project> a) {
        System.out.println("Project board");
        System.out.println("-------------");
       // Collections.sort(a);
        System.out.println("+------+----------------------+--------+");
        System.out.printf("%1s%3s%4s%15s%8s%6s%2s", "|", "ID", "|", "Project Name", "|", " Issues", "|");
        System.out.println();
        System.out.println("+------+----------------------+--------+");
        for (int i = 0; i < a.size(); i++) {
            System.out.printf("%1s%3s%4s%15s%8s%4s%5s", "|", (a.get(i).getId()), "|", a.get(i).getName(), "|", a.get(i).getIssues().size(), "|");
            System.out.println();
            System.out.println("+------+----------------------+--------+");
        }
    }

    public void sortProjectID(List<Project> a) {
        System.out.println("Project board");
        System.out.println("-------------");
        System.out.println("+------+----------------------+--------+");
        System.out.printf("%1s%3s%4s%15s%8s%6s%2s", "|", "ID", "|", "Project Name", "|", " Issues", "|");
        System.out.println();
        System.out.println("+------+----------------------+--------+");
        for (int i = 0; i < a.size(); i++) {
            System.out.printf("%1s%3s%4s%15s%8s%4s%5s", "|", (i + 1), "|", a.get(i).getName(), "|", a.get(i).getIssues().size(), "|");
            System.out.println();
            System.out.println("+------+----------------------+--------+");
        }
    }

    public boolean addProject(Project str) {
        if (projects.add(str)) {
            return true;
        } else {
            return false;
        }
    }

    public void addProject(List<Project> str) {
        for (int i = 0; i < str.size(); i++) {
            projects.add(str.get(i));
        }
    }

    public boolean removeProject(Project str) {
        if (projects.remove(str)) {
            return true;
        } else {
            return false;
        }
    }

    

}
