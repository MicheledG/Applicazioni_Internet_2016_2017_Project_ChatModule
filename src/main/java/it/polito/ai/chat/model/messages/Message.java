package it.polito.ai.chat.model.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 * Created by france193 on 25/06/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    @Id
    protected String id;
    @Indexed
    protected Date timestamp;
    protected String userMail;
    protected String content;

    public Message() {
    }

    public Message(Date timestamp, String userMail, String content) {
        this.timestamp = timestamp;
        this.userMail = userMail;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", userMail='" + userMail + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
