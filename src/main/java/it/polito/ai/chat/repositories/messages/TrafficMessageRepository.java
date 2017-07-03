package it.polito.ai.chat.repositories.messages;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import it.polito.ai.chat.model.messages.TrafficMessage;

/**
 * Created by france193 on 24/06/2017.
 */
@Repository
public interface TrafficMessageRepository extends MongoRepository<TrafficMessage, String> {
    //@Query(value = "{}", fields = "{}")
    //public List<TrafficMessage> findTrafficMessages(Pageable pageable);
}
