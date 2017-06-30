package it.polito.ai.chatmodule.model.messages;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by france193 on 24/06/2017.
 */
@Document(collection = "BikeTripMessage")
public class BikeTripMessage extends Message {
    public BikeTripMessage(Date timestamp, String userMail, String message) {
        super(timestamp, userMail, message);
    }

    @Override
    public String toString() {
        return "BikeTripMessage{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", userMail='" + userMail + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
