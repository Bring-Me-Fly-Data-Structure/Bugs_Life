
package com.mycompany.mavenproject2;
import java.util.List;
//import javax.annotation.Generated;
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
    private List<React> react = null;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("user")
    private String user;

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

    //need to
    @Override
    public String toString() {
        return "Comment{" + "commentId=" + commentId + ", text=" + text + ", react=" + react + ", timestamp=" + timestamp + ", user=" + user + '}';
    }

    
    
}
