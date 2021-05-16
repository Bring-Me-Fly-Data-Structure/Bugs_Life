
package com.mycompany.mavenproject2;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "comment_id",
    "text",
    "react",
    "timestamp",
    "user"
})
//@Generated("jsonschema2pojo")
public class Comment {

    @JsonProperty("comment_id")
    private Integer commentId;
    @JsonProperty("text")
    private String text;
    @JsonProperty("react")
    private List<React> react ;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("user")
    private String user;

    @JsonIgnore
    private java.util.Date timestampformat;
    
    public Comment() {
    }

    @JsonCreator
    //constructor for data.json 
    public Comment(@JsonProperty("comment_id") Integer commentId, @JsonProperty("text") String text, @JsonProperty("react") List<React> react, @JsonProperty("timestamp") Integer timestamp, @JsonProperty("user") String user) {
        this.commentId = commentId;
        this.text = text;
        this.react = react;
        this.timestamp = timestamp;
        this.timestampformat = new java.util.Date((long) timestamp * 1000);
        this.user = user;

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
    public List<React> getReact() {
        return react;
    }

    @JsonProperty("react")
    public void setReact(List<React> react) {
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
        String result = "#" + commentId + "     Created on: " + changeDateFormat() + "    By: " + user + "\n" + text + "\n$$ " + react + "\n";
        // return "Comment{" + "commentId=" + commentId + ", text=" + text + ", react=" + react + ", timestamp=" + timestamp + ", user=" + user + '}';
        return result;
    }

    
    
}
