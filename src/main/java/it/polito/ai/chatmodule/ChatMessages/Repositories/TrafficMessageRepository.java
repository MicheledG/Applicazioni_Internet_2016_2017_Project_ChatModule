package it.polito.ai.chatmodule.ChatMessages.Repositories;

import it.polito.ai.chatmodule.ChatMessages.Model.TrafficMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by france193 on 24/06/2017.
 */
public interface TrafficMessageRepository extends MongoRepository<TrafficMessage, String> {
    //@Query(value = "{}", fields = "{}")
    //public List<TrafficMessage> findTrafficMessages(Pageable pageable);
}
