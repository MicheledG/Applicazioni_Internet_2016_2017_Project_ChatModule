package it.polito.ai.chatmodule.repositories.messages;

import org.springframework.data.mongodb.repository.MongoRepository;

import it.polito.ai.chatmodule.model.messages.TrafficMessage;

/**
 * Created by france193 on 24/06/2017.
 */
public interface TrafficMessageRepository extends MongoRepository<TrafficMessage, String> {
    //@Query(value = "{}", fields = "{}")
    //public List<TrafficMessage> findTrafficMessages(Pageable pageable);
}
