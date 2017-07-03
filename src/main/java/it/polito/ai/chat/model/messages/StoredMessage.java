package it.polito.ai.chat.model.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 * Created by france193 on 25/06/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StoredMessage {
    @Id
    protected String id;
    @Indexed
    protected Date timestamp;
    protected String username;
    protected String content;

    public StoredMessage() {
    }

    public StoredMessage(Date timestamp, String username, String content) {
        this.timestamp = timestamp;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
