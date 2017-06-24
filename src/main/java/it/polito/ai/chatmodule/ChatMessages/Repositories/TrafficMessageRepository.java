package it.polito.ai.chatmodule.ChatMessages.Repositories;

import it.polito.ai.chatmodule.ChatMessages.Model.TrafficMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by france193 on 24/06/2017.
 */
public interface TrafficMessageRepository extends MongoRepository<TrafficMessage, String> {
    public List<TrafficMessage> findTopByOrderByCreatedDesc();
}
