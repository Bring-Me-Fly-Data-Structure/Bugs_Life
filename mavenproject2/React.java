
//package com.example;
package com.mycompany.mavenproject2;
//import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "reaction",
    "count"
})
//@Generated("jsonschema2pojo")
public class React {

    @JsonProperty("reaction")
    private String reaction;
    @JsonProperty("count")
    private Integer count;

    @JsonProperty("reaction")
    public String getReaction() {
        return reaction;
    }

    @JsonProperty("reaction")
    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

}
