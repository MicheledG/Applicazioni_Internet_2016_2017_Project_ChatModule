package it.polito.ai.chatmodule.ChatMessages.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by france193 on 24/06/2017.
 */
@Document(collection = "BusMetroMessage")
public class BusMetroMessage extends Message {
    public BusMetroMessage(Date timestamp, String userMail, String message) {
        super(timestamp, userMail, message);
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
