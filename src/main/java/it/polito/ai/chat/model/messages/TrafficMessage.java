package it.polito.ai.chat.model.messages;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by france193 on 24/06/2017.
 */
@Document(collection = "TrafficMessage")
public class TrafficMessage extends StoredMessage {
    public TrafficMessage(Date timestamp, String username, String content) {
        super(timestamp, username, content);
    }

    @Override
    public String toString() {
        return "TrafficMessage{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", username='" + username + '\'' +
                ", message='" + content + '\'' +
                '}';
    }
}
