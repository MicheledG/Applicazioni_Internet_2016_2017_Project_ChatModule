package it.polito.ai.chat.model.messages;


import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

//this class represent the anonymous message, MongoMessage as Rest Resource
public class MessageResource extends ResourceSupport {
    private String id;
    private Date timestamp;
    private String content;

    public String get_Id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
