package it.polito.ai.chat.model.messages;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by france193 on 24/06/2017.
 */
@Document(collection = "TrafficMessage")
public class TrafficMessage extends Message {
    public TrafficMessage(Date timestamp, String userMail, String content) {
        super(timestamp, userMail, content);
    }

    @Override
    public String toString() {
        return "TrafficMessage{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", userMail='" + userMail + '\'' +
                ", message='" + content + '\'' +
                '}';
    }
}
