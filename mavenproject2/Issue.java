//package com.example;
package com.mycompany.mavenproject2;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;
//import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    "comments"
})
//@Generated("jsonschema2pojo")
public class Issue implements Comparable<Issue> {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("priority")
    private Integer priority;
    @JsonProperty("status")
    private String status;
    @JsonProperty("tag")
    private List<String> tag;
    @JsonProperty("descriptionText")
    private String descriptionText;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("assignee")
    private String assignee;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("comments")
    private ArrayList<Comment> comments;

    private java.util.Date timestampformat;

    public Issue() {

    }

    //constructor for user input data
    public Issue(String title, Integer priority, String status, List<String> tag, String descriptionText, String createdBy, String assignee, ArrayList<Comment> comments) {
        this.title = title;
        this.priority = priority;
        this.status = status;
        this.tag = tag;
        this.descriptionText = descriptionText;
        this.createdBy = createdBy;
        this.assignee = assignee;
        this.comments = comments;
        this.timestampformat = new java.util.Date();
    }

    @JsonCreator
    //constructor for reading data.json
    public Issue(@JsonProperty("id")Integer id,@JsonProperty("title") String title,@JsonProperty("priority") Integer priority,@JsonProperty("status") String status,@JsonProperty("tag") List<String> tag,@JsonProperty("descriptionText") String descriptionText,@JsonProperty("createdBy") String createdBy,@JsonProperty("assignee") String assignee,@JsonProperty("timestamp") Integer timestamp,@JsonProperty("comments") ArrayList<Comment> comments) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.status = status;
        this.tag = tag;
        this.descriptionText = descriptionText;
        this.createdBy = createdBy;
        this.assignee = assignee;
        this.timestampformat = new java.util.Date((long) timestamp * 1000);
        this.comments = comments;
    }

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
    public List<String> getTag() {
        return tag;
    }

    @JsonProperty("tag")
    public void setTag(List<String> tag) {
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
    public ArrayList<Comment> getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public Date getTimestampformat() {
        return timestampformat;
    }

    public String changeDateFormat() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        return ft.format(getTimestampformat());
    }

    @Override
    public int compareTo(Issue object) {
        int result = 0;
        if (this.getPriority() < object.getPriority()) {
            result = 2;
        } else if (this.getPriority() > object.getPriority()) {
            result = -1;
        } else if (this.getPriority() == object.getPriority()) {
            if (this.getTimestampformat().compareTo(object.getTimestampformat()) > 0) {
                result = 1;
            } else {
                result = 0;
            }
        }
        return result;
    }
}
