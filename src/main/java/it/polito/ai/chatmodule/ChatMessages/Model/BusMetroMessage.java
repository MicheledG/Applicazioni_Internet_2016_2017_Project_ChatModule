package it.polito.ai.chatmodule.ChatMessages.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 * Created by france193 on 24/06/2017.
 */
public class BusMetroMessage {
    @Id
    private String id;
    @Indexed
    private Date timestamp;
    private String userMail;
    private String message;

    public BusMetroMessage() {
    }

    public BusMetroMessage(Date timestamp, String userMail, String message) {
        this.timestamp = timestamp;
        this.userMail = userMail;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BusMetroMessage{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", userMail='" + userMail + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
