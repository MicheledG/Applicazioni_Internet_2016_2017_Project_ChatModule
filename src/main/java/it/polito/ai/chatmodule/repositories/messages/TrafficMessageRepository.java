package it.polito.ai.chatmodule.repositories.messages;

import it.polito.ai.chatmodule.model.messages.TrafficMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by france193 on 24/06/2017.
 */
@Repository
public interface TrafficMessageRepository extends MongoRepository<TrafficMessage, String> {
    //@Query(value = "{}", fields = "{}")
    //public List<TrafficMessage> findTrafficMessages(Pageable pageable);
}
